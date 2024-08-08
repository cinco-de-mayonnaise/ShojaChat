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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 *
 * @author Ahnaf Abdullah
 */
final public class SceneSwitcher
{
    private static final Logger logger = LoggerFactory.getLogger(SceneSwitcher.class.getName());
    public static Class<?> global_class_handle;
    public static Stage mainstage;

    /**
     * Changes the window(in <code>stage</code>) to the scene(pointed to by <code>fxml_url</code>). No other properties are changed.
     * Typically, you should call this function from a controller to navigate to another part of the program.
     *
     * @param stage The stage you want to change the view of.
     * @param fxml_url The path to the FXML that represents the scene to switch to.
     */
    public static void switchToScene(Stage stage, String fxml_url)
    {
        try
        {
            Parent root = getRootNodeFromURL(fxml_url);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Throwable t)
        {
            logger.warn("SceneSwitcher failed to switch window {} to scene {}", stage.getTitle(), fxml_url);
            logger.warn("Cause: {}", t.getMessage());
        }
    }

    /**
     * Creates a new window with the scene(pointed to by <code>fxml_url</code>). No other properties are set.
     * Typically, you should call this function from a controller to navigate to another part of the program.
     * Keep in mind you should store the return value of this function somewhere if you want to be able to
     * remove/modify the window in the future.
     *
     * @param fxml_url The path to the FXML that represents the scene to switch to.
     * @param show_immediately Whether to show the stage immediately or not. This allows you to set additional
     *                         properties to the stage before showing it
     * @return the Stage representing the newly created window.
     */
    public static Stage createWindowWithScene(String fxml_url, boolean show_immediately)
    {
        try
        {
            Parent root = getRootNodeFromURL(fxml_url);

            Stage s = new Stage();
            Scene scene = new Scene(root);
            s.setScene(scene);
            if (show_immediately)
                s.show();

            return s;
        }
        catch (Throwable t)
        {
            logger.warn("SceneSwitcher failed to create window for scene {}", fxml_url);
            logger.warn("Cause: {}", t.getMessage());
        }

        return null;
    }

    /**
     * Creates a new window ready to be displayed with the scene(pointed to by <code>fxml_url</code>). No other properties are set.
     * Typically, you should call this function from a controller to navigate to another part of the program.
     * Keep in mind you should store the return value of this function somewhere if you want to be able to
     * remove/modify the window in the future.
     *
     * @param fxml_url The path to the FXML that represents the scene to switch to.
     * @return the Stage representing the newly created window. The stage immediately appears as the function returns.
     */
    public static Stage createWindowWithScene(String fxml_url)
    {
        return createWindowWithScene(fxml_url, true);
    }

    /**
     * Obtain the root node(aka Parent) of a scene graph represented by <code>fxml_url</code>
     *
     * @param fxml_url The path to the FXML that represents the scene to switch to.
     * @return The root node, may return null if
     */
    public static Parent getRootNodeFromURL(String fxml_url)
    {
        try
        {
            return Objects.requireNonNull(FXMLLoader.load(Objects.requireNonNull(global_class_handle.getResource(fxml_url))));
        }
        catch (Throwable t)
        {
            if (global_class_handle == null)
                logger.error("Global Class Handle not set!!!! SceneSwitcher cannot be used!");
            else if (global_class_handle.getResource(fxml_url) == null)
            {
                logger.error("getResource failed! Ensure fxml_url is correct...\nfxml_url: {}", fxml_url);
            }
            logger.warn("Exception raised: {}", t.getMessage());
        }
        return null;
    }

    /**
     * Helper function: obtain the stage(current window) from a given event that occurred in the stage.
     *
     * @param event Any JavaFX GUI event
     * @return A reference to the stage where the event spawned
     */
    public static Stage getStageFromEvent(Event event)
    {
        return (Stage)((Node)event.getSource()).getScene().getWindow();
    }

    /**
     * Helper function: obtain the stage(current window) from a node that is a part of a scene graph which was loaded.
     *
     * @param n Any node belonging a JavaFX Scene that was loaded and set to an active stage.
     * @return A reference to the stage, or <code>null</code> if the scene graph was not set to a stage.
     */
    public static Stage getStageFromNode(Node n)
    {
        return (Stage)(n.getScene().getWindow());
    }

    /**
     * Produces a GUI alert to let the user know some feature that was requested has yet to be implemented
     */
    public static void raiseAlert_NotImplemented()
    {
        raiseAlert_GenericWarning("",
                "Oh no! You caught me!",
                "This feature has not been implemented yet.");
    }
    /**
     * Produces a GUI alert to let the user know some unavoidable error has occured.
     * This is the standard error message format that should be shown if a critical
     * error occurs that requires the program to terminate.
     */
    public static void raiseAlert_BugCheck(String errorstr)
    {
        raiseAlert_GenericError("",
                "Oh no! A bug!",
                "You shouldn't be seeing this dialog box! If you see this, " +
                        "let the developers know.\nHere's some additional information " +
                        "that could help them...\n\n\n" + errorstr);
    }

    public static void raiseAlert_GenericError(String title, String header, String content)
    {
        raiseAlert_GenericImpl(title, header, content, AlertType.ERROR);
    }

    public static void raiseAlert_GenericWarning(String title, String header, String content)
    {
        raiseAlert_GenericImpl(title, header, content, AlertType.WARNING);
    }

    public static void raiseAlert_GenericImpl(String title, String header, String content, AlertType type)
    {
        Alert win = new Alert(type);
        if (title != null && !title.isEmpty())
            win.setTitle(title);

        if (header != null && !header.isEmpty())
            win.setHeaderText(header);

        if (content != null && !content.isEmpty())
            win.setContentText(content);

        win.showAndWait();
    }
}
