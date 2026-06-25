package org.framework.logging.filter.impl;

import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.filter.LogFilter;

public class LevelFilter implements LogFilter {
    @Override
    public boolean shouldLog(LogMessage message) {
        return false;
    }

    @Override
    public void setLevel(LogLevel level) {

    }

    @Override
    public LogLevel getLevel() {
        return null;
    }
}
