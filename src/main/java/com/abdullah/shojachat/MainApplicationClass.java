package com.abdullah.shojachat;

import com.abdullah.shojachat.util.CommonInstancesClass;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Integer.MAX_VALUE;


public class MainApplicationClass extends Application
{
    private static final Logger logger = LoggerFactory.getLogger(MainApplicationClass.class.getName());
    public static FileHandler logFile;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationClass.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void cmdShowUsage()
    {
        System.out.println("Usage: shojachat <args>");
        System.out.println("\t--server = Launch a server instance of ShojaChat. Server files will be created/used in the current working directory.");
    }

    private static void createLogFile()
    {
        String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        // System.out.println(dateNow);   // ex: "21-02-2008"
        /*    // check for unique file
        String unique_count = "";
        Path logFilename = null;
        for (int i = 1; i < Integer.MAX_VALUE; i++)
        {
            logFilename = logFolder.resolve(dateNow + unique_count + ".log");                   // ("logs/" + dateNow + ".log");
            if (Files.exists(logFilename))
                unique_count = "_" + Integer.toString(i);
            else
                break;
        }
        */

        Path logFolder = Paths.get("").resolve("logs");    // get current working directory and store it
        try
        {
            logFile = new FileHandler(logFolder.toAbsolutePath().toString() + dateNow + "_%u.%g.txt", 10*1024*1024, Integer.MAX_VALUE);
            logger.addHandler(logFile);
            logger.severe("sdf");
        }
        catch (IOException e)
        {
            logger.log(Level.WARNING, "Could not initialize log file!", e);
        }

        /* TODO: LEFT OFF HERE @last

            trying to make a log file to write everything into
            things that should be in the log file include what algorithm is being used to hash passwords and network packets
            etc.

            eventually we wanna log every freaking thing btw


            After logging is done, implement the algorithm for hashing passwords, check that it works
            we'll worry about other things later.
        *
        */
        logger.info("Log file at " + logFolder.toAbsolutePath().toString() + dateNow + ".txt");
    }

    private static void cleanupAndExit()
    {
        logger.info("Closing log files");

        logFile.close();
        Platform.exit();
    }

    public static void main(String[] args)
    {
        // Init logger
        createLogFile();

        // The program is simple enough that we don't need an argparser.... yet...
        // But just in case you do, lookup "Apache Commons CLI" or "JCommander"
        boolean server_mode = false;
        if (args.length == 0)
            server_mode = false;
        else if (args[0].equals("--server"))
            server_mode = true;

        if (server_mode)
            logger.info("Server mode is enabled! Running as server...");

        for (String s: args)
            System.out.println(s);

        launch();
    }
}