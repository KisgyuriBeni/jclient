package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
    Properties prop;
    public Prop(){
        prop = new Properties();
        this.readConfig();
    }

    public void readConfig(){
        try {
            tryReadConfig();
        } catch (FileNotFoundException e) {
            System.err.println("Hiba! A fájl nem található!");
            System.err.println(e.getMessage());
        }catch(IOException e ){
            System.err.println("Hiba! A beállítások átvétele a folyamból sikertelen!");
            System.err.println(e.getMessage()); 
        }    
    }
    private void tryReadConfig() throws IOException{   
        InputStream stream = new FileInputStream("jclient.config");
        prop.load(stream);

    }
    public Properties getProp(){
        return this.prop;
    }
}
