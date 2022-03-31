// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.OPRContent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import frc.data.output.CSVofOPRs;

public final class Main {


  public static void main(String[] args) throws IOException {
    CSVofOPRs csv = new CSVofOPRs();
    System.out.println(csv); //gets file of team numbers, oprs, win percentages
    File directory = new File(System.getProperty("C:\\Windows\\Downloads"));
    OutputStream out = new FileOutputStream(directory);
    out.close();
  }
}
