package com.abdullah.shojachat.ui.controller;

import com.abdullah.shojachat.util.SceneSwitcher;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.commons.io.input.BoundedInputStream;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class UIHelpers {
    public static void makeLinesDynamic()
    {

    }

    public static Object openFileWithProgressBar(Path p) throws IOException {
        BoundedInputStream cis = BoundedInputStream.builder()
                .setPath(p)
                .get();

        return cis.getCount();
    }

    public static Stage createChildWindowAndSetOwner(String fxml_url, Stage owner)
    {
        Stage s = SceneSwitcher.createWindowWithScene(fxml_url, false);
        assert(s != null);
        s.initOwner(owner);
        s.initModality(Modality.WINDOW_MODAL);     // makes all ancestor windows frozen until the user closes s.

        return s;
    }
}
