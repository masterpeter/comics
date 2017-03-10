package it.mastropietro.marvelcomics.data.entity.mapper;

import org.junit.Test;

import it.mastropietro.marvelcomics.data.TestUtils;
import it.mastropietro.marvelcomics.model.Comic;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
public class ComicMapperTest {

    private static final ComicMapper comicMapper = new ComicMapper();

    @Test
    public void whenMapIsCalled_returnNotNullComic() throws Exception {
        Comic comic = comicMapper.map(TestUtils.buildComicEntity(0));

        assertNotNull(comic);
    }

    @Test
    public void whenMapIsCalled_returnComicWithExpectedFieldsHappyPath() throws Exception {
        Comic comic = comicMapper.map(TestUtils.buildComicEntity(0));

        assertThat(comic.getId(), is(62725));
        assertThat(comic.getTitle(), is("Secret Empire (2017)"));
        assertThat(comic.getDescription(), containsString("The Marvel Universe"));
        assertThat(comic.getIsbn(), is("N/A"));
        assertThat(comic.getFormat(), is("Comic"));
        assertThat(comic.getPageCount(), is("N/A"));
        assertThat(comic.getSeriesName(), is("Secret Empire (2017)"));
        assertThat(comic.getComicDates().get(0).getDate(), is("19 apr 2017"));
        assertThat(comic.getComicPrices().get(0).getPrice(), is("N/A"));
        assertThat(comic.getThumbnail(), containsString("58a37f7d66d13.jpg"));
        assertThat(comic.getImages().get(0), containsString("58a37f7d66d13.jpg"));
        assertThat(comic.getCharacters().size(), is(2));
    }
}