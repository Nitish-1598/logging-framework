package org.framework.logging.formatter.impl;

import org.framework.logging.core.LogMessage;
import org.framework.logging.formatter.LogFormatter;

public class SimpleFormatter implements LogFormatter {
    @Override
    public String format(LogMessage logMessage) {
        return "";
    }

    @Override
    public void setPattern(String pattern) {

    }

    @Override
    public String getPattern() {
        return "";
    }

    @Override
    public void setDateFormat(String dateFormat) {

    }
}
