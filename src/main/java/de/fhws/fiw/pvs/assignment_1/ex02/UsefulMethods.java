package de.fhws.fiw.pvs.assignment_1.ex02;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class UsefulMethods {

    public static byte[] toByteArray(InputStream in) throws IOException {
        return IOUtils.toByteArray(in);
    }

    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
