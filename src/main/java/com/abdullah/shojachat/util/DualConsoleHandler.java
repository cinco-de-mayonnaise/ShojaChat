package com.abdullah.shojachat.util;

import java.util.logging.*;

/**
 *
 * @author ocarlsen
 */
public class DualConsoleHandler extends StreamHandler
{
    private final ConsoleHandler stderrHandler = new ConsoleHandler();
    Level stderrAboveThis = Level.INFO;

    public DualConsoleHandler(Level stderr_above_this) {
        super(System.out, new SimpleFormatter());
        stderrAboveThis = stderr_above_this;
    }

    public DualConsoleHandler() {
        super(System.out, new SimpleFormatter());
    }

    @Override
    public void publish(LogRecord record) {
        if (record.getLevel().intValue() <= stderrAboveThis.intValue()) {
            super.publish(record);
            super.flush();
        } else {
            stderrHandler.publish(record);
            stderrHandler.flush();
        }
    }
}
