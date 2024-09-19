package com.abdullah.shojachat.ui.controller;

import com.abdullah.shojachat.actors.ServerImpl;
import com.abdullah.shojachat.util.CommonInstancesClass;
import com.abdullah.shojachat.util.Identifiers;
import com.abdullah.shojachat.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.function.Function;

public class server__createServer1$controller
{
    private static final Logger logger = LoggerFactory.getLogger(server__createServer1$controller.class.getName());

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
    private ComboBox<String> CB_SCSDatasize;
    @FXML
    private TextField TF_ServerFileLocation;
    @FXML
    private Label Label_fieldvalidation;
    @FXML
    private ImageView IV_Icon_fieldvalidation;

    @FXML
    public void initialize()
    {
        /* TODO: Implement live field validation (later! for now validate by attempting to construct.) */

        // togglegroup done in fxml
        //TF_ServerCacheSize.setText("512");


        // add data prefixes
        CB_SCSDatasize.getItems().addAll("KB", "MB", "GB");
        CB_SCSDatasize.getSelectionModel().select("MB");

        // get all hashtypes and put them in here
        CB_HashType.getItems().addAll(Security.getAlgorithms("SecretKeyFactory"));
        CB_HashType.getSelectionModel().select("PBKDF2WITHHMACSHA384");

        logger.trace("Finished initializing this scene");

        TF_ServerFileLocation.setText(Path.of((String)CommonInstancesClass.getObject(Identifiers.CIC_String_CwdPath), "server").toString());
    }

    @FXML
    public void click_Cancel(ActionEvent actionEvent)
    {
        logger.trace("User clicked Cancel!");
        // just go away
        SceneSwitcher.getStageFromEvent(actionEvent).close();
    }

    @FXML
    public void click_Next(ActionEvent actionEvent)
    {
        logger.trace("User clicked Next!");
        // attempt to construct
        try
        {
            String hashType = CB_HashType.getValue();
            int maxUsers = Integer.parseInt(TF_ServerMaxUsers.getText());
            boolean requireEmail = ((RadioButton)(TG_UniqueIdentity.getSelectedToggle())).getText().equals("Email");         // this is a very bad idea, to find a toggle based on what its text is: because the text will most likely change when i add in language options....
            boolean allowImages = CB_AllowImages.isSelected();
            boolean allowFiles = CB_AllowFilesharing.isSelected();
            boolean enableStatistics = CB_AllowStatistics.isSelected();
            long size_sAb_multiplier = (
                    (Function<String, Long>) (
                            (String e) -> {
                                return switch (e) {
                                    case "KB" -> 1024L;
                                    case "MB" -> 1024L * 1024;
                                    case "GB" -> 1024L * 1024 * 1024;
                                    default -> throw new RuntimeException("ComboBox Value not set!");
                                };
                            }
                    )
            ).apply(CB_SCSDatasize.getValue());

            long size_spaceAllowed_bytes = Long.parseLong(TF_ServerCacheSize.getText()) * size_sAb_multiplier;
            String name = TF_ServerName.getText();
            int maxUsersOnline = Integer.parseInt(TF_ServerMaxOnlineUsers.getText());

            ServerImpl s = new ServerImpl(hashType,
                 requireEmail,
                 allowImages,
                 allowFiles,
                 enableStatistics,
                 size_spaceAllowed_bytes,
                 name,
                 maxUsers,
                 maxUsersOnline
            );
            logger.info("Just created a new server {}", s);

            s.saveServer(Path.of(TF_ServerFileLocation.getText()));
            logger.trace("Saved server at {}", TF_ServerFileLocation.getText());
            CommonInstancesClass.putObject(Identifiers.CIC_ServerImpl_CurrentRunningServer, s);

            // our work here is done, close the scene
            SceneSwitcher.getStageFromEvent(actionEvent).close();

        } catch (NoSuchAlgorithmException e) {
            logger.warn("The algorithm specified by the HashType ComboBox could not be used to construct a Hasher!");
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException ee)
        {
            logger.warn("Constructing the server failed!", ee);
            SceneSwitcher.raiseAlert_GenericError("Error!", "Please check the following issues", ee.getMessage());
        } catch (IOException e) {
            SceneSwitcher.raiseAlert_GenericError("Invalid location!", "Invalid location, could not save server", e.getMessage());
        }

    }

    @FXML
    public void click_TG_Email(ActionEvent actionEvent) {
        SceneSwitcher.raiseAlert_NotImplemented();
        // this autoselects the username as the only mode of authentication
        for (Toggle t : TG_UniqueIdentity.getToggles())
            t.setSelected(((RadioButton) t).getText().equals("Username"));
    }


    @FXML
    public void click_ServerDataFileLocation(ActionEvent actionEvent)
    {
        // this is good enough for now!
        // but i feel like i forgot some dumb edge case

        logger.trace("User clicked Choose Location!");

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("ShojaChat Server DataFile", "*.scsd"),
                new ExtensionFilter("All Files", "*.*"));


        File safe_folder = new File((String)CommonInstancesClass.getObject(Identifiers.CIC_String_CwdPath)); // this folder should be openable no matter what
        Path cur_text_field = null;
        try
        {
            if (TF_ServerFileLocation.getText().length() <= 1)
                throw new InvalidPathException("why the heck does this not trigger from Paths.of() reeeeee", "String too small");

            cur_text_field = Path.of(TF_ServerFileLocation.getText());   // this is entirely dependent on user input and hence should be checked carefully
        }
        catch (InvalidPathException e)
        {
            logger.trace("Invalid path in text field! Setting to fallback!");

            cur_text_field = safe_folder.toPath();
            assert safe_folder.isDirectory();
        }
        logger.trace("Location was interpreted as {}", cur_text_field);

        if (!cur_text_field.toFile().isDirectory())
            try
            {
                if (!cur_text_field.toFile().canWrite())
                {
                    logger.trace("Failed to write at location {}", cur_text_field);
                    //SceneSwitcher.raiseAlert_GenericWarning("", "", "You may not have write permissions in this folder. Please choose another location!");
                    fc.setInitialDirectory(safe_folder);
                }
                else
                {
                    Files.createDirectories(cur_text_field);  // creates the directory if it doesnt exist or else ignore yay
                    fc.setInitialDirectory(cur_text_field.toFile());
                }

                logger.trace("Set FileChooser to open at {}", cur_text_field.toFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        //System.out.println(Path.of(TF_ServerFileLocation.getText()).toFile());
        File selected = null;
        try
        {
            selected = fc.showSaveDialog(SceneSwitcher.getStageFromEvent(actionEvent));
        }
        catch (RuntimeException r)  // provided directory may be incorrect (user wrote something stupid or something), fallback to safe initial directory
        {
            logger.warn("FileChooser failed to open! Retrying with fallback");
            fc.setInitialDirectory(safe_folder);
            selected = fc.showSaveDialog(SceneSwitcher.getStageFromEvent(actionEvent));
        }
        if (selected == null)
            return;      // nothing selected, do nothing

        TF_ServerFileLocation.setText(selected.getAbsolutePath());
    }
}