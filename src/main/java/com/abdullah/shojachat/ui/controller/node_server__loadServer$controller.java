package com.abdullah.shojachat.ui.controller;

import com.abdullah.shojachat.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

import static com.abdullah.shojachat.util.Identifiers.Scenes.node_server__createServer1;

public class node_server__loadServer$controller
{
    @FXML
    public void click_CreateNewServer(ActionEvent actionEvent)
    {
        UIHelpers.createChildWindowAndSetOwner(node_server__createServer1, SceneSwitcher.getStageFromEvent(actionEvent)).showAndWait();
    }

    @FXML
    public void initialize()
    {

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


    }
}