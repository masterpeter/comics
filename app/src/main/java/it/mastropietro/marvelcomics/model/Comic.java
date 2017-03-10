package it.mastropietro.marvelcomics.model;

import java.util.List;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public final class Comic {

    private final int id;
    private final String title;
    private final String description;
    private final String isbn;
    private final String format;
    private final String pageCount;
    private final String seriesName;
    private final String thumbnail;
    private final List<ComicDate> comicDates;
    private final List<ComicPrice> comicPrices;
    private final List<String> thumbnails;
    private final List<String> characters;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getFormat() {
        return format;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPageCount() {
        return pageCount;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public List<ComicDate> getComicDates() {
        return comicDates;
    }

    public List<ComicPrice> getComicPrices() {
        return comicPrices;
    }

    public List<String> getImages() {
        return thumbnails;
    }

    public List<String> getCharacters() {
        return characters;
    }

    private Comic(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.isbn = builder.isbn;
        this.format = builder.format;
        this.thumbnail = builder.thumbnail;
        this.pageCount = builder.pageCount;
        this.seriesName = builder.seriesName;
        this.comicDates = builder.comicDates;
        this.comicPrices = builder.comicPrices;
        this.thumbnails = builder.images;
        this.characters = builder.characterList;
    }

    public static final class Builder {
        private int id;
        private String title;
        private String description;
        private String isbn;
        private String format;
        private String pageCount;
        private String seriesName;
        private String thumbnail;
        private List<ComicDate> comicDates;
        private List<ComicPrice> comicPrices;
        private List<String> images;
        private List<String> characterList;

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

        public Builder pageCount(String pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
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

        public Builder images(List<String> images) {
            this.images = images;
            return this;
        }

        public Builder characterList(List<String> characterList) {
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
        if (title != null ? !title.equals(comic.title) : comic.title != null) return false;
        if (description != null ? !description.equals(comic.description) : comic.description != null)
            return false;
        if (isbn != null ? !isbn.equals(comic.isbn) : comic.isbn != null) return false;
        if (format != null ? !format.equals(comic.format) : comic.format != null) return false;
        if (pageCount != null ? !pageCount.equals(comic.pageCount) : comic.pageCount != null)
            return false;
        if (seriesName != null ? !seriesName.equals(comic.seriesName) : comic.seriesName != null)
            return false;
        if (thumbnail != null ? !thumbnail.equals(comic.thumbnail) : comic.thumbnail != null)
            return false;
        if (comicDates != null ? !comicDates.equals(comic.comicDates) : comic.comicDates != null)
            return false;
        if (comicPrices != null ? !comicPrices.equals(comic.comicPrices) : comic.comicPrices != null)
            return false;
        if (thumbnails != null ? !thumbnails.equals(comic.thumbnails) : comic.thumbnails != null)
            return false;
        return characters != null ? characters.equals(comic.characters) : comic.characters == null;

    }

    @Override public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (pageCount != null ? pageCount.hashCode() : 0);
        result = 31 * result + (seriesName != null ? seriesName.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (comicDates != null ? comicDates.hashCode() : 0);
        result = 31 * result + (comicPrices != null ? comicPrices.hashCode() : 0);
        result = 31 * result + (thumbnails != null ? thumbnails.hashCode() : 0);
        result = 31 * result + (characters != null ? characters.hashCode() : 0);
        return result;
    }
}
