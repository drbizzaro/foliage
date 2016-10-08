package org.foilage.utils;

import org.pmw.tinylog.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public enum ApplicationConfigHandler {

    INSTANCE;

    /**
     * Loads the config file for the application with the sent in filename
     * @param settingsFileName      The filename of the configuration
     * @return The properties as a java Properties object.
     * */
    public Properties load(String settingsFileName) {

        Logger.trace("Basedir: "+System.getProperty("user.dir"));

        String url = System.getProperty("user.dir")+"/";
        url = url.replaceAll("%20", " ");

        Properties properties = new Properties();

        try {

            properties.loadFromXML(new FileInputStream(url + settingsFileName));

        } catch(Exception ex) {
            try{

                properties.loadFromXML(new FileInputStream(System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+settingsFileName));

            } catch (Exception e) {

                String errorMess = "Problem loading settings file "+settingsFileName+": ";

                Logger.error(errorMess+"\n"+e+"\n"+ex);
                throw new RuntimeException(errorMess+"\n"+e+"\n"+ex);

            }
        }

        return properties;

    }
}
