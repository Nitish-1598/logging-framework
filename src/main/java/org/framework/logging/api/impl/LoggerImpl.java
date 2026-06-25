package org.framework.logging.api.impl;

import org.framework.logging.api.Logger;
import org.framework.logging.core.constants.LogLevel;

public class LoggerImpl implements Logger {

    @Override
    public void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    @Override
    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    @Override
    public void warning(String message) {
        System.out.println("[WARNING] " + message);
    }

    @Override
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    @Override
    public void fatal(String message) {
        System.out.println("[FATAL] " + message);
    }

    @Override
    public void trace(String message) {
        System.out.println("[TRACE] " + message);
    }

    @Override
    public void log(LogLevel level, String message) {

    }

    @Override
    public void setLevel(LogLevel level) {

    }

    @Override
    public void addAppender(LogAppender appender) {

    }

    @Override
    public void addFilter(LogFilter filter) {

    }

    @Override
    public void removeFilter(LogFilter filter) {

    }

    @Override
    public List<LogAppender> getAppenders() {
        return null;
    }

    @Override
    public List<LogFilter> getFilters() {
        return null;
    }
}
