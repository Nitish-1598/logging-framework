package org.framework.logging.formatter.impl;

import org.framework.logging.core.LogMessage;
import org.framework.logging.formatter.LogFormatter;

import java.time.format.DateTimeFormatter;

public class SimpleFormatter implements LogFormatter {
    private String pattern;
    private String dateFormat;
    // Business use can be implemented later
    private DateTimeFormatter dateTimeFormatter;

    public SimpleFormatter() {
        this("[%LEVEL] %TIMESTAMP - %MESSAGE");
    }

    public SimpleFormatter(String pattern) {
        this.pattern = pattern;
        this.dateFormat = "yyyy-MM-dd HH:mm:ss";
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }
    @Override
    public String format(LogMessage message) {
        if (pattern == null || pattern.isEmpty()) {
            return String.format("[%s] %s - %s",
                    message.getLogLevel(),
                    message.getTimestamp().toString(),
                    message.getMessage());
        }

        String formatted = pattern
                .replace("%LEVEL", message.getLogLevel().toString())
                .replace("%TIMESTAMP", message.getTimestamp().toString())
                .replace("%MESSAGE", message.getMessage())
                .replace("%SOURCE", message.getSource() != null ? message.getSource() : "");

        return formatted;
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }
}
