package com.abdullah.shojachat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class MainApplicationClass extends Application
{
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

    private File createLogFile()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateNow = sdf.format(new Date());
        System.out.println(dateNow);
        File logFile = new File("/logs/" + dateNow + ".log");
        //if (logFile.exists())
        return logFile;

        /* TODO: LEFT OFF HERE @last

            trying to make a log file to write everything into
            things that should be in the log file include what algorithm is being used to hash passwords and network packets
            etc.

            eventually we wanna log every freaking thing btw


            After logging is done, implement the algorithm for hashing passwords, check that it works
            we'll worry about other things later.
        *
        */
    }

    public static void main(String[] args)
    {
        // Init logger
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        //Handler fh = new FileHandler("/logs/");

        // The program is simple enough that we don't need an argparser.... yet...
        // But just in case you do, lookup "Apache Commons CLI" or "JCommander"
        boolean server_mode = false;
        if (args.length == 0)
            server_mode = false;
        else if (args[0].equals("--server"))
            server_mode = true;



        System.out.println("# args: " + Integer.toString(args.length));

        for (String s: args)
            System.out.println(s);

        launch();
    }
}