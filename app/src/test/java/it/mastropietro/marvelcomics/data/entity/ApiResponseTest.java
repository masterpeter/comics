package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;

import it.mastropietro.marvelcomics.data.ApiResponse;
import it.mastropietro.marvelcomics.data.TestUtils;

import static org.junit.Assert.*;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
public class ApiResponseTest {

    private static final Gson gson = new Gson();

    @Test
    public void whenApiResponseIsRetrieved_returnCorrectMappingWithGSON() throws Exception {
        String comicResponseAsString = TestUtils.readFile("comic_response.json");
        Type comicDataType = new TypeToken<ApiResponse<ComicDataEntity>>(){}.getType();
        ApiResponse<ComicDataEntity> entity = gson.fromJson(comicResponseAsString, comicDataType);

        assertNotNull(entity);
    }
}