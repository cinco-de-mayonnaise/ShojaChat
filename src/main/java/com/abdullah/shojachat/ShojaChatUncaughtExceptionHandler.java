package com.abdullah.shojachat;

import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class ShojaChatUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(ShojaChatUncaughtExceptionHandler.class.getName());

    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
        //System.out.println("\n\n\nHELLOOOOOOOOOOOOOOOOO AM I GETTING CALLED OR NOT????\n\n\n");
        // turns out you are not called if an exception is raised in main() or javafx.Application.start()   ;-;

        logger.error("FATAL: Unhandled exception by {}", t);

        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        PrintStream out2 = new PrintStream(out1);
        e.printStackTrace(out2);
        logger.error(out1.toString());

        // the program should now exit
        Platform.exit();
        System.exit(1);
    }
}
