package frc.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import frc.OPRContent.Match;


// placeholder class, will probably be refactored
public class DataIO {
   public static void writeMatchData(ArrayList<Match> matches, String filepath) {
      try {
         FileOutputStream fileOut =
         new FileOutputStream(filepath);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(matches);
         out.close();
         fileOut.close();
         System.out.printf(filepath);
      } catch (IOException i) {
         i.printStackTrace();
      }
   }
   public static ArrayList<Match> readMatchData(String filepath) {
      ArrayList<Match> out = new ArrayList<>();
      try {
         FileInputStream fileIn = new FileInputStream(filepath);
         
         ObjectInputStream in = new ObjectInputStream(fileIn);
         out = (ArrayList<Match>) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return null;
      } catch (ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return null;
      }
      return out;
   }

}


