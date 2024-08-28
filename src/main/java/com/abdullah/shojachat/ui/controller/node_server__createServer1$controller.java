package com.abdullah.shojachat.ui.controller;

import com.abdullah.shojachat.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class node_server__createServer1$controller
{

    @FXML
    private ToggleGroup TG_UniqueIdentity;
    @FXML
    private CheckBox CB_AllowStatistics;
    @FXML
    private TextField TF_ServerCacheSize;
    @FXML
    private TextField TF_ServerName;
    @FXML
    private CheckBox CB_AllowImages;
    @FXML
    private TextField TF_ServerMaxOnlineUsers;
    @FXML
    private ComboBox<String> CB_HashType;
    @FXML
    private TextField TF_ServerMaxUsers;
    @FXML
    private CheckBox CB_AllowFilesharing;

    @FXML
    public void initialize()
    {
        // togglegroup done in fxml
        TF_ServerCacheSize.setText("512");
    }

    /**
     * Function that is called whenever TF_ServerCacheSize is modified by the user. Ensure you retrieve value of TF_ServerCacheSize using this function.
     * (Because manually extracting value from String is stupid, do it once here).
     *
     */



    private int update_SCS_text()
    {
        // read current value in TF, append MB
        String old_value = TF_ServerCacheSize.getText();
        if (!old_value.matches("^\\d+\\s*(MB|mb)$"))
        {
            SceneSwitcher.raiseAlert_GenericError("Wrong Input", "Please enter a valid integer", "");
        }

        return 0;
    }

    @FXML
    public void click_Cancel(ActionEvent actionEvent)
    {
        // just go away
        SceneSwitcher.getStageFromEvent(actionEvent).close();
    }

    @FXML
    public void click_Next(ActionEvent actionEvent) {
    }

    @FXML
    public void click_TG_Email(ActionEvent actionEvent) {
        SceneSwitcher.raiseAlert_NotImplemented();
        // this autoselects the username as the only mode of authentication
        for (Toggle t : TG_UniqueIdentity.getToggles())
            t.setSelected(((RadioButton) t).getText().equals("Username"));

    }

    @FXML
    public void callback_TF_SCS(Event actionEvent)
    {
        System.out.println(actionEvent);

    }
}