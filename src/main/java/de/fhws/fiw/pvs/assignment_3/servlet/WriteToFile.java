package de.fhws.fiw.pvs.assignment_3.servlet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class WriteToFile {

    public static void writeToFile(String fileName, String text) throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(text);
        writer.close();
    }

}
