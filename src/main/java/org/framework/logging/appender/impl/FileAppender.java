package org.framework.logging.appender.impl;

import org.framework.logging.appender.LogAppender;
import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.formatter.LogFormatter;
import org.framework.logging.formatter.impl.SimpleFormatter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileAppender implements LogAppender {
    private LogLevel level;
    private LogFormatter formatter;
    private String filePath;
    private PrintWriter writer;

    public FileAppender(String filePath) {
        this(filePath, LogLevel.TRACE);
    }

    public FileAppender(String filePath, LogLevel level) {
        this.filePath = filePath;
        this.level = level;
        this.formatter = new SimpleFormatter();
        initializeWriter();
    }

    private void initializeWriter() {
        try {
            this.writer = new PrintWriter(new FileWriter(filePath, true), true);
        } catch (IOException e) {
            System.err.println("Failed to initialize FileAppender for " + filePath + ": " + e.getMessage());
        }
    }

    @Override
    public void append(LogMessage message) {
        if (!isEnabled(message.getLogLevel()) || writer == null) {
            return;
        }

        try {
            String formattedMessage = formatter.format(message);
            writer.println(formattedMessage);
            writer.flush();
        } catch (Exception e) {
            System.err.println("Failed to write to file " + filePath + ": " + e.getMessage());
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
    public String getFilePath() {
        return filePath;
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
