package com.abdullah.shojachat;

import com.abdullah.shojachat.util.CommonInstancesClass;
import com.abdullah.shojachat.util.SceneSwitcher;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

import static com.abdullah.shojachat.util.Identifiers.Scenes.node_server__loadServer;
import static com.abdullah.shojachat.util.Identifiers.String_CwdPath_CIC;
import static com.abdullah.shojachat.util.SceneSwitcher.raiseAlert_GenericWarning;

public class ServerApplicationClass
{
    // Server settings
    private static final Logger logger = LoggerFactory.getLogger(ServerApplicationClass.class.getName());
    static Properties serverSettings_prop;

    private static String safelyGetProperty(String propertyKey)
    {
        String returnval = serverSettings_prop.getProperty(propertyKey);
        if (returnval == null)
        {
            logger.warn("The requested property {} does not exist in the serverSettings file! The file may be corrupt!", propertyKey);
            raiseAlert_GenericWarning("",
                    "Corrupt settings file!",
                    "Your server settings file is corrupted. A new one will be generated. \n\n" +
                            "PS: Do not worry, this does not mean your actual server's datafile is corrupted. Please select the existing server datafile again.");
        }

        return returnval;
    }

    // JavaFX Subsystem must be initialized
    public static void server_main(Stage stage)  // implement arguments passing later, arguments should be parsed by main() and passed here as some abstract object
    {
        // load server settings here
        serverSettings_prop = loadServerSettings();
        boolean noPreviousServersOpened = false;
        String serverfile_path = safelyGetProperty("lastOpenedServer");

        logger.info("Opening server datafile: {}", serverfile_path);
        // load server if one was previously opened before
        if (serverfile_path.isEmpty())
        {
            // one was not opened previously.
            SceneSwitcher.switchToScene(stage, node_server__loadServer);
        }
    }

    private static Properties loadServerSettings()
    {
        Properties prop = new Properties();
        Path serverSettings_file = Path.of((String)CommonInstancesClass.getObject(String_CwdPath_CIC), "serverSettings.dat");

        if (!serverSettings_file.toFile().exists())
        {
            logger.info("Server Setting file does not exist! Making a new one...");
            try (FileOutputStream newSettings = new FileOutputStream(serverSettings_file.toFile())) {
                defaultSettings(prop);

                prop.store(newSettings, "Server Settings");
            }
            catch (Exception e) {
                logger.warn("Failed to write properties to Server Settings file!");
                logger.warn("Cause: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        else
        {
            // file exists, read it
            try (FileInputStream is = new FileInputStream(serverSettings_file.toFile())) {
                prop.load(is);
            } catch (Exception e) {
                logger.warn("Failed to read properties from Server Settings file!");
                logger.warn("Cause: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }

        logger.trace("Successfully read Server Settings at {}", serverSettings_file.toAbsolutePath());
        return prop;
    }

    private static void defaultSettings(Properties p)
    {
        p.setProperty("lastOpenedServer", "");              // Contains location of the last place where a server was opened.
    }
}
