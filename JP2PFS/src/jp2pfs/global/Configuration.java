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
public class Configuration extends HashMap<String, String> {

    private String pathname = "";

    public Configuration(String pathname) {
        this.pathname = pathname;
        this.loadConfiguration(this.pathname);
    }

    public void loadConfiguration(String pathname) {
        File file = new File(pathname);
        if (file.canRead()) {
            Scanner reader = null;
            try {
                reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.startsWith("#")) {
                        System.out.println(line);
                    } else if (line.contains(": ")) {
                        String[] components = line.split(": ");
                        this.put(components[0], components[1]);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Could not open file to read.");
            } finally {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.out.println("Could not close file after reading.");
                }
            }
        }
    }

    public void saveConfiguration() {
        File file = new File(pathname);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Could not create file.");
            }
        } else {
            System.out.println("File exists.");
        }
        String linesep = System.getProperty("line.separator");
        if (file.canWrite()) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.append("# This config file was created by" + linesep);
                writer.append("# JavaPeerToPeerFileSharing Application" + linesep);
                writer.append("# For further information google it!" + linesep);
                for (Entry<String, String> entry : this.entrySet()) {
                    writer.append(entry.getKey() + ": " + entry.getValue() + linesep);
                }
            } catch (Exception e) {
                System.out.println("Could not open file to write.");
            } finally {
                try {
                    writer.close();
                } catch (Exception e) {
                    System.out.println("Could not close file after writing.");
                }
            }
        }
    }
}
