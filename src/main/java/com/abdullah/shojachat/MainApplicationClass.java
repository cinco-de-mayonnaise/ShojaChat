package com.abdullah.shojachat;

import com.abdullah.shojachat.util.CommonInstancesClass;
import com.abdullah.shojachat.util.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

import java.security.Security;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSocketFactory;

import static com.abdullah.shojachat.util.Identifiers.String_CwdPath_CIC;


public class MainApplicationClass extends Application
{
    private static final Logger logger = LoggerFactory.getLogger(MainApplicationClass.class.getName());
    static boolean server_mode = false;

    @Override
    public void start(Stage stage) throws IOException
    {
        //System.out.println(Thread.currentThread().getUncaughtExceptionHandler().getClass());
        Thread.currentThread().setUncaughtExceptionHandler(new ShojaChatUncaughtExceptionHandler());
        /* it is not possible to set a custom UncaughtExceptionHandler while you are */

        /* do all setup that requires main here */
        SceneSwitcher.global_class_handle = MainApplicationClass.class;
        SceneSwitcher.mainstage = stage;

        CommonInstancesClass.putObject(String_CwdPath_CIC, System.getProperty("user.dir"));
        logger.info("Current Working Directory: {}", (String)CommonInstancesClass.getObject(String_CwdPath_CIC));

        if (server_mode)   // launch server side app here
        {
            // this is basically a main function that should live as long as the program does...
            // we must make a new thread and have it run this function....
            new Thread("Server Application Main Thread"){
                @Override
                public void run() {
                    ServerApplicationClass.server_main(stage);
                }
            }.start();
        }
        else
        {

        }



//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationClass.class.getResource("mainWindow.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
    }

    private void cmdShowUsage()
    {
        System.out.println("Usage: shojachat <args>");
        System.out.println("\t--server = Launch a server instance of ShojaChat. Server files will be created/used in the current working directory.");
    }

    public static void main(String[] args)
    {logger.info("<-- Application started at");


        Thread.setDefaultUncaughtExceptionHandler(new ShojaChatUncaughtExceptionHandler());
        Thread.currentThread().setUncaughtExceptionHandler(new ShojaChatUncaughtExceptionHandler());
        //System.out.println(Thread.currentThread().getUncaughtExceptionHandler().getClass());

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

                /* RUNTIME INFORMATION*/
                logger.info("Available SecretKeyFactory's: {}", String.join("  ", Security.getAlgorithms("SecretKeyFactory")));
                //logger.info("Available KeyGenerator's: {}", String.join("  ", Security.getAlgorithms("KeyGenerator")));
                logger.info("Available Ciphers for TLS: {}", String.join(" ", ((SSLSocketFactory)SSLSocketFactory.getDefault()).getSupportedCipherSuites()));

        // check all running threads
        //for (Thread t: Thread.getAllStackTraces().keySet())
        //    System.out.println(t);

        logger.trace("Starting the JavaFX subsystem...");
        launch();
        logger.trace("JavaFX subsystem terminated. Graceful exit.");
    }
}