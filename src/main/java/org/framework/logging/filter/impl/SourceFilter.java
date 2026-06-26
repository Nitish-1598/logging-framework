package org.framework.logging.filter.impl;

import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.filter.LogFilter;

/**
 * Filter that filters messages based on source/class name.
 * Can include or exclude specific packages/classes.
 */
public class SourceFilter implements LogFilter {
    private String sourcePattern;
    private boolean include; // true to include matching sources, false to exclude
    private LogLevel level;
    @Override
    public boolean shouldLog(LogMessage message) {
        if(message.getSource() == null) {
            return false;
        }
        boolean matches = message.getSource().matches(sourcePattern);
        return include == matches;
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return this.level;
    }

    public String getSourcePattern() {
        return sourcePattern;
    }

    public void setSourcePattern(String sourcePattern) {
        this.sourcePattern = sourcePattern;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }
}
