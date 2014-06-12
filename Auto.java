package uebung9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Auto implements Serializable{

    private static final long serialVersionUID = 1L;

    protected String name = "Hallo, Welt!";
    // Wird nicht mit Serialisiert (gespeichert), da transient.
    protected transient int age = 200;

    private void writeObject( final ObjectOutputStream oos ) throws IOException
    {
        oos.defaultWriteObject();
    }

    private void readObject( final ObjectInputStream ois ) throws IOException
    {
        try
        {
            ois.defaultReadObject(); // Lies Name, aber ohne Alter
            // Alter wird nochmal hinzugeschrieben, damit es keine Fehler gibt. Anwendung zB bei Daten, die man nutzen neu laden muss aus dem Internet oder von der Festplatte.
            this.age = 100;
        }
        catch ( ClassNotFoundException e )
        {
            throw new IOException( "No class found. HELP!!" );
        }
    }

}
