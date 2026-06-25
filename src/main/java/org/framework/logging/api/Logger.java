package org.framework.logging.api;

import org.framework.logging.core.constants.LogLevel;

public interface Logger {
    void debug(String message);
    void info(String message);
    void warning(String message);
    void error(String message);
    void fatal(String message);
    void trace(String message);

    // Generic logging method
    void log(LogLevel level, String message);

    // Configuration methods
    void setLevel(LogLevel level);
    void addAppender(LogAppender appender);
    void addFilter(LogFilter filter);
    void removeFilter(LogFilter filter);

    // Getter methods
    List<LogAppender> getAppenders();
    List<LogFilter> getFilters();
}
