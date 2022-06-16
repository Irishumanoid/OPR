// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.OPRContent;

import java.io.IOException;
import frc.data.CSVofOPRs;

public final class Main {


  public static void main(String[] args) throws IOException {
    CSVofOPRs csv = new CSVofOPRs();
    System.out.println(csv); //gets file of team numbers, oprs, win percentages
    /*
    File csv = new File(System.getProperty("C:\\Windows\\Downloads"));
    OutputStream out = new FileOutputStream(csv);
    out.close();*/
  }
}
