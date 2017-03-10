package it.mastropietro.marvelcomics.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;

import it.mastropietro.marvelcomics.data.entity.ComicDataEntity;
import it.mastropietro.marvelcomics.data.entity.ComicEntity;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class TestUtils {

    private static final Gson gson = new Gson();
    private static final String responseString = readFile("comic_response.json");

    public static ComicEntity buildComicEntity(int comicIndex) {
        Type comicEntityType = new TypeToken<ApiResponse<ComicDataEntity>>() {}.getType();
        ApiResponse<ComicDataEntity> response = gson.fromJson(responseString, comicEntityType);
        return response.getData().getComicEntities().get(comicIndex);
    }

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
