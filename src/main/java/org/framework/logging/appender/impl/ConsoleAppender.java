package org.framework.logging.appender.impl;

import org.framework.logging.appender.LogAppender;
import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.formatter.LogFormatter;

public class ConsoleAppender implements LogAppender {
    @Override
    public void append(LogMessage logMessage) {

    }

    @Override
    public void setLevel(LogLevel level) {

    }

    @Override
    public LogLevel getLevel() {
        return null;
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return false;
    }

    @Override
    public void setFormatter(LogFormatter formatter) {

    }

    @Override
    public LogFormatter getFormatter() {
        return null;
    }
}
