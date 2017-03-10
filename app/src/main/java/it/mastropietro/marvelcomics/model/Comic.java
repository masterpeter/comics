package it.mastropietro.marvelcomics.model;

import java.util.List;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class Comic {

    private final int id;
    private final String title;
    private final String description;
    private final String isbn;
    private final String format;
    private final int pageCount;
    private final String seriesName;
    private final List<ComicDate> comicDates;
    private final List<ComicPrice> comicPrices;
    private final List<ComicImage> thumbnails;
    private final CharacterList characterList;

    private Comic(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.isbn = builder.isbn;
        this.format = builder.format;
        this.pageCount = builder.pageCount;
        this.seriesName = builder.seriesName;
        this.comicDates = builder.comicDates;
        this.comicPrices = builder.comicPrices;
        this.thumbnails = builder.thumbnails;
        this.characterList = builder.characterList;
    }

    public static final class Builder {
        private int id;
        private String title;
        private String description;
        private String isbn;
        private String format;
        private int pageCount;
        private String seriesName;
        private List<ComicDate> comicDates;
        private List<ComicPrice> comicPrices;
        private List<ComicImage> thumbnails;
        private CharacterList characterList;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public Builder pageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder seriesName(String seriesName) {
            this.seriesName = seriesName;
            return this;
        }

        public Builder comicDates(List<ComicDate> comicDates) {
            this.comicDates = comicDates;
            return this;
        }

        public Builder comicPrices(List<ComicPrice> comicPrices) {
            this.comicPrices = comicPrices;
            return this;
        }

        public Builder thumbnails(List<ComicImage> thumbnails) {
            this.thumbnails = thumbnails;
            return this;
        }

        public Builder characterList(CharacterList characterList) {
            this.characterList = characterList;
            return this;
        }

        public Comic build() {
            return new Comic(this);
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comic comic = (Comic) o;

        if (id != comic.id) return false;
        if (pageCount != comic.pageCount) return false;
        if (title != null ? !title.equals(comic.title) : comic.title != null) return false;
        if (description != null ? !description.equals(comic.description) : comic.description != null)
            return false;
        if (isbn != null ? !isbn.equals(comic.isbn) : comic.isbn != null) return false;
        if (format != null ? !format.equals(comic.format) : comic.format != null) return false;
        if (seriesName != null ? !seriesName.equals(comic.seriesName) : comic.seriesName != null)
            return false;
        if (comicDates != null ? !comicDates.equals(comic.comicDates) : comic.comicDates != null)
            return false;
        if (comicPrices != null ? !comicPrices.equals(comic.comicPrices) : comic.comicPrices != null)
            return false;
        if (thumbnails != null ? !thumbnails.equals(comic.thumbnails) : comic.thumbnails != null)
            return false;
        return characterList != null ? characterList.equals(comic.characterList) : comic.characterList == null;

    }

    @Override public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + pageCount;
        result = 31 * result + (seriesName != null ? seriesName.hashCode() : 0);
        result = 31 * result + (comicDates != null ? comicDates.hashCode() : 0);
        result = 31 * result + (comicPrices != null ? comicPrices.hashCode() : 0);
        result = 31 * result + (thumbnails != null ? thumbnails.hashCode() : 0);
        result = 31 * result + (characterList != null ? characterList.hashCode() : 0);
        return result;
    }
}
