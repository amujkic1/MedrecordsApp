package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        DoctorsDao doc = new DoctorsDaoImpl();
        Doctors newdoc = new Doctors(11, "Steven", "Bennet", "2376 Alpha Avenue",
                "sbennet@gmail.com", "270-415-4762", 40, "m", "pediatrist",
                "acfe345wdr", "StevenBennet");


        //doc.add(newdoc);
        //doc.delete(11);

        /*Properties database_prop = new Properties();
        InputStream resource = null;
        try {
            resource = App.class.getResource("/database.properties").openStream();
            database_prop.load(resource);
            System.out.println(database_prop.getProperty("username"));
            System.out.println(database_prop.getProperty("password"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}
