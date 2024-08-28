package com.abdullah.shojachat.ui.controller;

import com.abdullah.shojachat.util.SceneSwitcher;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UIHelpers {
    public static void makeLinesDynamic()
    {

    }

    public static Stage createChildWindowAndSetOwner(String fxml_url, Stage owner)
    {
        Stage s = SceneSwitcher.createWindowWithScene(fxml_url, false);
        assert s != null;
        s.initOwner(owner);
        s.initModality(Modality.WINDOW_MODAL);     // makes all ancestor windows frozen until the user closes s.

        return s;
    }
}
