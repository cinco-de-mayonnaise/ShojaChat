package com.abdullah.shojachat.ui.controller;

import com.abdullah.shojachat.actors.ServerImpl;
import com.abdullah.shojachat.actors.ServerImplData;
import com.abdullah.shojachat.util.CommonInstancesClass;
import com.abdullah.shojachat.util.Identifiers;
import com.abdullah.shojachat.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import static com.abdullah.shojachat.util.Identifiers.Scenes.*;

public class server__loadServer$controller
{
    private static final Logger logger = LoggerFactory.getLogger(server__loadServer$controller.class.getName());
    @FXML
    private Button lovelyButton;


    @FXML
    public void click_CreateNewServer(ActionEvent actionEvent)
    {
        UIHelpers.createChildWindowAndSetOwner(server__createServer1, SceneSwitcher.getStageFromEvent(actionEvent)).showAndWait();


    }

    @FXML
    public void initialize()
    {

    }

    private void startServer()
    {
        Object obj = CommonInstancesClass.getObject(Identifiers.CIC_ServerImpl_CurrentRunningServer);
        if (obj != null)
        {
            // move to new server...
            SceneSwitcher.createWindowWithScene(server_MainWindow__main);

            // TODO: close this scene
            SceneSwitcher.getStageFromNode(lovelyButton).close();
        }

    }


    @FXML
    public void click_AddExistingServerData(ActionEvent actionEvent)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
            new ExtensionFilter("ShojaChat Server DataFile", "*.scsd"),
            new ExtensionFilter("All Files", "*.*"));

        File selected = fc.showOpenDialog(SceneSwitcher.getStageFromEvent(actionEvent));
        if (selected == null)
            return;         // nothing selected, do nothing

        // selected is a server file, open it
        ServerImplData serverdata = null;
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selected));
            serverdata = (ServerImplData) ois.readObject();

            if (serverdata == null)
                throw new RuntimeException("wat??? file empty???");
        }
        catch (Exception e)
        {
            logger.warn("Could not open existing server datafile: {}", e.getMessage());
            SceneSwitcher.raiseAlert_GenericError("Failed to open server datafile", "", e.getMessage());
            return;
        }

        // attempt to start this server
        try
        {
            ServerImpl s = new ServerImpl(serverdata);
            CommonInstancesClass.putObject(Identifiers.CIC_ServerImpl_CurrentRunningServer, s);
        }
        catch (Exception e)
        {
            logger.warn("Could not start server: {}", e.getMessage());
            SceneSwitcher.raiseAlert_GenericError("Failed to start the server", "", e.getMessage());
            return;
        }

        startServer();
    }
}