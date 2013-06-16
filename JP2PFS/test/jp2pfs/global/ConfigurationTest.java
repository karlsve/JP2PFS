/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global;

import java.io.File;
import org.junit.Test;

/**
 *
 * @author z0034sez
 */
public class ConfigurationTest {
    
    @Test
    public void Test1() {
        String sep = File.separator;
        Configuration config = new Configuration("D:"+sep+"jp2pfs"+sep+"test.conf");
        config.put("eins", "Test1");
        config.saveConfiguration();
    }
}