package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;

import it.mastropietro.marvelcomics.data.ApiResponse;
import it.mastropietro.marvelcomics.data.TestUtils;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
public class ApiResponseTest {

    private static final Gson gson = new Gson();

    @Test
    public void whenApiResponseIsRetrieved_returnCorrectMappingWithGSON() throws Exception {
        ApiResponse<ComicDataEntity> entity = getComicResponse();

        assertNotNull(entity);
    }

    @Test
    public void whenApiResponseIsMapped_checkFieldsAreFilled() throws Exception {
        ApiResponse<ComicDataEntity> entity = getComicResponse();

        assertThat(entity.getCode(), is(200));
        assertThat(entity.getData(), is(notNullValue()));
        assertThat(entity.getData().getComicEntities(), is(notNullValue()));

        ComicEntity firstComic = entity.getData().getComicEntities().get(0);

        assertThat(firstComic, is(notNullValue()));
        assertThat(firstComic.getId(), is(62725));

        CharacterEntity firstComicCharacter = firstComic.getCharacterSummary().getCharacters().get(1);

        assertThat(firstComicCharacter.getName(), is("Hydra"));
    }

    private ApiResponse<ComicDataEntity> getComicResponse() {
        String comicResponseAsString = TestUtils.readFile("comic_response.json");
        Type comicDataType = new TypeToken<ApiResponse<ComicDataEntity>>() {
        }.getType();
        return gson.fromJson(comicResponseAsString, comicDataType);
    }
}