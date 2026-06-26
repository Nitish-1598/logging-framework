package org.framework.logging.appender.impl;

import org.framework.logging.appender.LogAppender;
import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.formatter.LogFormatter;
import org.framework.logging.formatter.impl.SimpleFormatter;

import java.io.PrintStream;

public class ConsoleAppender implements LogAppender {
    private LogLevel level;
    private LogFormatter formatter;
    private final PrintStream outputStream;

    public ConsoleAppender(){
        this(LogLevel.TRACE);
    }
    public ConsoleAppender(LogLevel level){
        this.level = level;
        this.formatter = new SimpleFormatter();
        this.outputStream = System.out;
    }
    @Override
    public void append(LogMessage logMessage) {
        if (!isEnabled(logMessage.getLogLevel())) {
            return;
        }

        String formattedMessage = formatter.format(logMessage);

        // Use System.err for ERROR and FATAL levels
        if (logMessage.getLogLevel() == LogLevel.ERROR || logMessage.getLogLevel() == LogLevel.FATAL) {
            System.err.println(formattedMessage);
        } else {
            outputStream.println(formattedMessage);
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
}
