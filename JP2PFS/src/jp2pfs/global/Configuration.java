/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author z0034sez
 */
public class Configuration extends HashMap<String, Object> {

    private String pathname = "";

    public Configuration(String pathname) throws Exception {
        this.pathname = pathname;
        this.loadConfiguration(this.pathname);
    }

    private void loadConfiguration(String pathname) throws Exception {
        File file = new File(pathname);
        if (file.canRead()) {
            Scanner reader = null;
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.startsWith("#")) {
                    System.out.println(line);
                } else if (line.contains(": ")) {
                    String[] components = line.split(": ");
                    this.put(components[0], ConfigurationFactory.deSerialize(components[0], components[1]));
                }
            }
            reader.close();
        }
    }

    public void saveConfiguration() throws Exception {
        File file = new File(pathname);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        String linesep = System.getProperty("line.separator");
        if (file.canWrite()) {
            FileWriter writer = null;
            writer = new FileWriter(file);
            writer.append("# This config file was created by" + linesep);
            writer.append("# JavaPeerToPeerFileSharing Application" + linesep);
            writer.append("# For further information google it!" + linesep);
            for (Entry<String, Object> entry : this.entrySet()) {
                writer.append(entry.getKey() + ": " + entry.getValue() + linesep);
            }
            writer.close();
        }
    }
}
