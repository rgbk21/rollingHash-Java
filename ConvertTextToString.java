package com.company;

import java.io.*;
import java.util.Scanner;

public class ConvertTextToString {




    public static String readFile(String fileName) throws IOException {

        File myFile = new File(fileName);
        Scanner scanner = new Scanner( new File("C:\\Users\\rajga\\IdeaProjects\\ProgrammingAssignment1\\src\\com\\company\\trial.txt") );
        //Scanner scanner = new Scanner( new File(fileName) );

        //String text = scanner.useDelimiter("\\A").next();
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        String lettersOnly = text.replaceAll("[\\W]", "");
//        System.out.println("lettersOnly = " + lettersOnly);
//        System.out.println("text:  " + text);
        return lettersOnly;

    }

    public static void main(String[] args) throws IOException {

        readFile("trial.txt");

    }



}