package uebung9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialisierung {

    /*
     * In der Autoklasse wird von Serializable implementiert. Damit müssen die Methoden writeObject und readObject implementiert werden.
     * Jedes Objekt muss also die Methoden überscheiben/implementiern, damit es serialisiert werden kann..
     * In dem Methoden muss vorkommen, wie man die daten serialisieren möchte. Die einfachste möglichkeit ist in {@link Auto}.java festgehalten.
     * In dieser Klasse wird die datei nur ganz normal geschrieben und nur ganz normal gelesen (bzw. die ausnahme bildet das Alter, welches nicht
     * Serialisiert wird und deswegen nun nochmal initialisiert werden muss.
     */
    static Auto a = new Auto();

    public static void main(final String[] args) {
        saveObject(Serialisierung.a);
        System.out.println("Alter des alten AutoObjektes: " + Serialisierung.a.age);
        try {
            Auto b = readObject("d:/test.txt");
            // die zahl, die man bei der readObject methode in {@link Auto} implementiert hat. In diesesm Fall hardcoded implementiert, da keine Serialisierung erfolgt.
            System.out.println("Alter des neuen AutoObjektes: " + b.age);
        } catch (FileNotFoundException e) {
            System.out.println("Konnte die Datei nicht finden.. Bitte geben Sie eine andere ein.");
        }
    }

    private static <T extends Serializable> void saveObject(final Object  T){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/test.txt"))){
            oos.writeObject(T);
            System.out.println("Objekt serialisiert");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catchblock
        } catch (IOException e) {
            // TODO Auto-generated catchblock
        }
    }

    private static <T extends Object & Serializable> T readObject(final String filename) throws FileNotFoundException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            T resultObject = (T) ois.readObject();
            System.out.println("Objekt deserialisiert.");
            return resultObject;
        } catch (IOException e) {
            // TODO Auto-generated catchblock
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catchblock
        }
        return null;
    }
}
