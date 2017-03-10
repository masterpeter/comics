package it.mastropietro.marvelcomics.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class TestUtils {

    public static String readFile(String fileName) {
        BufferedReader reader = null;
        try {
            URI uri = ClassLoader.getSystemResource(fileName).toURI();
            reader = new BufferedReader(new FileReader(new File(uri)));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
