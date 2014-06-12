package uebung9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialisierung {

    static Auto a = new Auto();

    public static void main(final String[] args) {
        saveObject(Serialisierung.a);
        try {
            Auto b = readObject("d:/test.txt");
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
            return resultObject;
        } catch (IOException e) {
            // TODO Auto-generated catchblock
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catchblock
        }
        return null;
    }
}
