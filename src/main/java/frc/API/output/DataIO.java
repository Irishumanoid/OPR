package frc.API.output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import frc.API.type.Match;

/**
 * A group of methods for interacting with serialized data.
 */
public class DataIO {

   public static void writeMatchData(ArrayList<Match> matches, String filepath) {

      try {
         FileOutputStream fileOut =
         new FileOutputStream(filepath);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(matches);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in " + filepath);
      } catch (IOException i) {
         i.printStackTrace();
      }

   }

   public static ArrayList<Match> readMatchData(String filepath) {

      ArrayList<Match> out = null;

      try {

         FileInputStream fileIn = new FileInputStream(filepath);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         Object obj = in.readObject();

         if (obj instanceof ArrayList<?>) {

            ArrayList<?> objArr = (ArrayList<?>) obj;

            if (objArr.size() > 0 && objArr.getClass().getComponentType().isAssignableFrom(Match.class)) {
               out = (ArrayList<Match>)  objArr; // Literally a checked cast smh
            } else {
               System.out.println("File does not contain a match dataset! (ArrayList does not have a component type of Match or is empty)");
            }

         } else {
            System.out.println("File does not contain a match dataset! (File does not contain an ArrayList)");
         }

         in.close();
         fileIn.close();

      } catch (IOException i) {
         i.printStackTrace();
      } catch (ClassNotFoundException c) {
         c.printStackTrace();
      }

      return out;
   }
   
   /**
    * 
    * @param string
    * @param filepath
    */
   public static void writeRaw(String string, String filepath) {
      try {
         FileOutputStream fileOut =
         new FileOutputStream(filepath);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(string);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in " + filepath);
      } catch (IOException i) {
         i.printStackTrace();
      }
   }

   public static Object readRaw(String filepath) {
      Object out = null;
      try {
         FileInputStream fileIn = new FileInputStream(filepath);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         out = in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return null;
      } catch (ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
      }
      return out;
   }

   public static InputStreamReader stream(String filepath) {
      InputStreamReader out = null;
      try {
         FileInputStream fileIn = new FileInputStream(filepath);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         out = new InputStreamReader(in);
      } catch (IOException i) {
         i.printStackTrace();
      }
      return out;
   }
   
}
