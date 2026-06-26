package org.framework.logging.filter.impl;

import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.filter.LogFilter;

public class LevelFilter implements LogFilter {
    private LogLevel level;

    public LevelFilter() {
        this(LogLevel.TRACE);//Defaulting loglevel to trace
    }

    public LevelFilter(LogLevel level) {
        this.level = level;
    }

    @Override
    public boolean shouldLog(LogMessage message) {
        return message.getLogLevel().isGreaterOrEqual(level);
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return this.level;
    }
}
