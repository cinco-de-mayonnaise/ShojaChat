package com.abdullah.shojachat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainWindow.fxml"));
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

    public static void main(String[] args) {
        // The program is simple enough that we don't need an argparser.... yet...
        // But just in case you do, lookup "Apache Commons CLI" or "JCommander"
        boolean server_mode = false;
        if (args.length == 0)
            server_mode = false;

        for (String s: args)
            System.out.println(s);

        //launch();
    }
}