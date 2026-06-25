package org.framework.logging.formatter;

import org.framework.logging.core.LogMessage;
import org.framework.logging.core.constants.LogLevel;

public interface LogFormatter {
    String format(LogMessage logMessage);
    void setPattern(String pattern);
    String getPattern();
    void setDateFormat(String dateFormat);
}
