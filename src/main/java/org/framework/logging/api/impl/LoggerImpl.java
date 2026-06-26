package org.framework.logging.api.impl;

import org.framework.logging.api.Logger;
import org.framework.logging.appender.LogAppender;
import org.framework.logging.appender.impl.ConsoleAppender;
import org.framework.logging.core.LogConfiguration;
import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;
import org.framework.logging.filter.LogFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoggerImpl implements Logger {
    private final String name;
    private LogLevel level;
    private final List<LogAppender> appenders;
    private final List<LogFilter> filters;

    public LoggerImpl() {
        this("DeafaultLogger");
    }

    public LoggerImpl(String name) {
        this(name, true); // Default to adding console appender
    }

    public LoggerImpl(String name, boolean enableDefaulterAppender) {
        this.name = name;
        this.level = LogLevel.TRACE;//Default level :: to show all messages
        //Collections.synchronizedList() to handle multi-threaded environment
        this.appenders = Collections.synchronizedList(new ArrayList<>());
        this.filters = Collections.synchronizedList(new ArrayList<>());
        if (enableDefaulterAppender) {
            addAppender(new ConsoleAppender());
        }
    }

    public LoggerImpl(String name, LogConfiguration logConfig) {
        this(name);
        this.level = logConfig.getRootLevel();
    }

    /*
    - Handle logging for each logger type
    - Added Synchronized keyword for RACE/Multi-Threaded Environment
     */

    @Override
    public synchronized void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    @Override
    public synchronized void info(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public synchronized void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    @Override
    public synchronized void error(String message) {
        log(LogLevel.ERROR, message);
    }

    @Override
    public synchronized void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    @Override
    public synchronized void trace(String message) {
        log(LogLevel.TRACE, message);
    }

    //Main Logging Logic

    @Override
    public synchronized void log(LogLevel level, String message) {
        //LogLevel Validation
        // Do not Log :: Return early
        if(!level.isGreaterOrEqual(this.level)) {
            return;
        }

        //Create Log Message
        LogMessage logMessage = new LogMessage.Builder()
                .setLogLevel(level)
                .setMessage(message)
                .setSource(this.getSourceDetails())
                .build();
        //Apply filters
        for(LogFilter filter : this.filters) {
            if(!filter.shouldLog(logMessage)) {
                return;//Filtering out the message
            }
        }
        //Send to all configured Appenders
        for(LogAppender appender : this.appenders) {
            if(appender.isEnabled(level)){
                appender.append(logMessage);
            }
        }
    }
    /**
     * Gets the calling class name for source information.
     * This is a simplified implementation.
     */
    private String getSourceDetails() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length > 3) {
                String className = stackTrace[3].getClassName();
                String methodName = stackTrace[3].getMethodName();
                return className + "." + methodName;
            }
        } catch (Exception e) {
            // Ignore exceptions in source detection
        }
        return "Unknown";
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    @Override
    public void addFilter(LogFilter filter) {
        filters.add(filter);
    }

    @Override
    public void removeFilter(LogFilter filter) {
        filters.remove(filter);
    }

    public String getName() {
        return name;
    }

    public LogLevel getLevel() {
        return level;
    }

    public List<LogAppender> getAppenders() {
        return new ArrayList<>(appenders);
    }

    public List<LogFilter> getFilters() {
        return new ArrayList<>(filters);
    }
}
