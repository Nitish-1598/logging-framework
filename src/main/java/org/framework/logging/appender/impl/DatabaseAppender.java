package org.framework.logging.appender.impl;

import org.framework.logging.appender.LogAppender;
import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.formatter.LogFormatter;
import org.framework.logging.formatter.impl.SimpleFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Appender that writes log messages to a database table.
 * Note: This is a simplified implementation. In a real scenario, you'd use a proper connection pool.
 */
public class DatabaseAppender implements LogAppender {
    private LogLevel level;
    private LogFormatter formatter;
    private Connection connection;
    private String tableName;
    private PreparedStatement insertStatement;

    public DatabaseAppender(String tableName) {
        this(tableName, LogLevel.TRACE);
    }
    public DatabaseAppender(String tableName, LogLevel level) {
        this.tableName = tableName;
        this.level = level;
        this.formatter = new SimpleFormatter();
        //TODO: If you connect to a database, initialize the connection.
        this.connection = null;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
        initializeStatement();
    }

    private void initializeStatement() {
        if (connection != null) {
            try {
                String sql = "INSERT INTO " + tableName + " (timestamp, level, message, source) VALUES (?, ?, ?, ?)";
                insertStatement = connection.prepareStatement(sql);
            } catch (SQLException e) {
                System.err.println("Failed to initialize DatabaseAppender: " + e.getMessage());
            }
        }
    }

    @Override
    public void append(LogMessage message) {
        if (!isEnabled(message.getLogLevel()) || connection == null || insertStatement == null) {
            return;
        }

        try {
            insertStatement.setTimestamp(1, Timestamp.valueOf(message.getTimestamp()));
            insertStatement.setString(2, message.getLogLevel().toString());
            insertStatement.setString(3, message.getMessage());
            insertStatement.setString(4, message.getSource());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to write to database: " + e.getMessage());
        }
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return this.level;
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return level.isGreaterOrEqual(this.level);
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return this.formatter;
    }

    public void close() {
        if (insertStatement != null) {
            try {
                insertStatement.close();
            } catch (SQLException e) {
                System.err.println("Failed to close statement: " + e.getMessage());
            }
        }
    }
}
