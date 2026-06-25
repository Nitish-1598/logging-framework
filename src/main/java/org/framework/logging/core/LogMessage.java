package org.framework.logging.core;

import org.framework.logging.core.constants.LogLevel;

import java.time.LocalDateTime;

public class LogMessage {
    private final LocalDateTime timestamp;
    private final LogLevel logLevel;
    private final String message;
    private final String source;

    private LogMessage(Builder builder) {
        this.timestamp = builder.timestamp;
        this.logLevel = builder.logLevel;
        this.message = builder.message;
        this.source = builder.source;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getMessage() {
        return message;
    }

    public String getSource() {
        return source;
    }
    @Override
    public String toString() {
        return String.format("LogMessage{timestamp=%s, level=%s, message='%s', source='%s'}",
                timestamp, logLevel, message, source);
    }

    /**
     * Builder class for LogMessage construction.
     */
    public static class Builder{
        private LocalDateTime timestamp = LocalDateTime.now();//default
        private LogLevel logLevel =  LogLevel.INFO;
        private String message;
        private String source;

        public Builder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setLogLevel(LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public LogMessage build() {
            if (logLevel == null) {
                throw new IllegalStateException("LogLevel is required");
            }
            if (message == null || message.trim().isEmpty()) {
                throw new IllegalStateException("Message is required");
            }
            return new LogMessage(this);
        }
    }
}
