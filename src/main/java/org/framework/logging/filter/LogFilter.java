package org.framework.logging.filter;

import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;

public interface LogFilter {
    boolean shouldLog(LogMessage message);
    void setLevel(LogLevel level);
    LogLevel getLevel();
}
