package com.abdullah.shojachat.util;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Abdullah
 */
public class SceneSwitcher
{
    public static Class<?> global_class_handle;
    public static Stage mainstage;

    // Changes the window(in mainstage) to the Scene(in fxml_url). No other properties are changed.
    public static void switchToScene(String fxml_url)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(global_class_handle.getResource(fxml_url));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            mainstage.setScene(scene);
            mainstage.show();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            if (global_class_handle.getResource(fxml_url) == null)
            {
                System.out.println("\n-----getResource failed! Ensure fxml_url is correct...\nfxml_url: " + fxml_url + "\n");
            }
        }
    }

    public static Stage createWindowWithScene(String fxml_url, boolean show_immediately)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(global_class_handle.getResource(fxml_url));
            Parent root = loader.load();

            Stage s = new Stage();
            Scene scene = new Scene(root);
            s.setScene(scene);
            if (show_immediately)
                s.show();

            return s;
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            if (global_class_handle.getResource(fxml_url) == null)
            {
                System.out.println("\n-----getResource failed! Ensure fxml_url is correct...\nfxml_url: " + fxml_url + "\n");
            }
        }

        return null;
    }

    public static Stage createWindowWithScene(String fxml_url)
    {
        return createWindowWithScene(fxml_url, true);
    }

    public static Parent getRootNodeFromURL(String fxml_url)
    {
        try
        {
            return FXMLLoader.load(global_class_handle.getResource(fxml_url));
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            if (global_class_handle.getResource(fxml_url) == null)
            {
                System.out.println("\n-----getResource failed! Ensure fxml_url is correct...\nfxml_url: " + fxml_url + "\n");
            }
        }
        return null;
    }

    public static Stage getStageFromEvent(Event event)
    {
        return (Stage)((Node)event.getSource()).getScene().getWindow();
    }

    public static Stage getStageFromNode(Node n)
    {
        return (Stage)(n.getScene().getWindow());
    }

    public static void raiseAlert_NotImplemented()
    {
        Alert win = new Alert(AlertType.WARNING);
        win.setContentText("This feature has not been implemented yet.");

        win.showAndWait();
    }

    public static void raiseAlert_BugCheck(String errorstr)
    {
        Alert win = new Alert(AlertType.WARNING);
        win.setHeaderText("Oh no! A bug!");
        win.setContentText("You shouldn't be seeing this dialog box! If you see this, let the developers know.\nHere's some additional information that could help them...\n\n\n" + errorstr);

        win.showAndWait();
    }

    public static void raiseAlert_GenericError(String title, String header, String content)
    {
        Alert win = new Alert(AlertType.ERROR);
        if (title != null && !title.isEmpty())
            win.setTitle(title);

        if (header != null && !header.isEmpty())
            win.setHeaderText(header);

        if (content != null && !content.isEmpty())
            win.setContentText(content);

        win.showAndWait();
    }

    public static void raiseAlert_GenericWarning(String title, String header, String content)
    {
        Alert win = new Alert(AlertType.WARNING);
        if (title != null && !title.isEmpty())
            win.setTitle(title);

        if (header != null && !header.isEmpty())
            win.setHeaderText(header);

        if (content != null && !content.isEmpty())
            win.setContentText(content);

        win.showAndWait();
    }
}
