package org.framework.logging.appender;

import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.formatter.LogFormatter;

public interface LogAppender {
    void append(LogMessage logMessage);
    void setLevel(LogLevel level);
    LogLevel getLevel();
    boolean isEnabled(LogLevel level);
    void setFormatter(LogFormatter formatter);
    LogFormatter getFormatter();
}
