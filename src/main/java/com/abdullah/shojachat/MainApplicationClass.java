package com.abdullah.shojachat;

import com.abdullah.shojachat.util.CommonInstancesClass;
import com.abdullah.shojachat.util.SceneSwitcher;
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
import java.util.Arrays;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;

import static java.lang.Integer.MAX_VALUE;


public class MainApplicationClass extends Application
{
    private static final Logger logger = LoggerFactory.getLogger(MainApplicationClass.class.getName());
    static boolean server_mode = false;

    @Override
    public void start(Stage stage) throws IOException
    {
        SceneSwitcher.global_class_handle = MainApplicationClass.class;
        SceneSwitcher.mainstage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationClass.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //for (java.security.Provider prov: java.security.Security.getProviders())
        //    System.out.println(prov.toString());

        // goal: try to hash "hello" using PBKDF2WITHHMACSHA384

        for (String alg: java.security.Security.getAlgorithms("KeyGenerator"))
            System.out.println(alg);

        System.out.println("\n\n\n");

        System.out.println(Arrays.toString(new SecretKeySpec("hello".getBytes(), "PBKDF2WITHHMACSHA384").getEncoded()));

    }

    private void cmdShowUsage()
    {
        System.out.println("Usage: shojachat <args>");
        System.out.println("\t--server = Launch a server instance of ShojaChat. Server files will be created/used in the current working directory.");
    }

    public static void main(String[] args)
    {
        logger.info("<-- Application started at");
        /*
        logger.info("Example log from {}", MainApplicationClass.class.getSimpleName());
        logger.debug("Example log from {}", MainApplicationClass.class.getSimpleName());
        logger.error("Example log from {}", MainApplicationClass.class.getSimpleName());
        logger.trace("Example log from {}", MainApplicationClass.class.getSimpleName());
        logger.warn("Example log from {}", MainApplicationClass.class.getSimpleName());
        // The program is simple enough that we don't need an argparser.... yet...
        // But just in case you do, lookup "Apache Commons CLI" or "JCommander"
        */
        if (args.length == 0)
            server_mode = false;
        else if (args[0].equals("--server"))
            server_mode = true;

        if (server_mode)
            logger.info("Server mode is enabled! Running as server...");
        else
            logger.info("Running as client...");

        logger.trace("Arguments passed: {}", String.join(" ", args));

        logger.trace("Starting the JavaFX subsystem...");
        launch();
        logger.trace("JavaFX subsystem terminated");
    }
}