package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicEntity {

    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("isbn") private String isbn;
    @SerializedName("format") private String format;
    @SerializedName("pageCount") private int pageCount;
    @SerializedName("series") private ComicSeriesEntity seriesName;
    @SerializedName("dates") private List<ComicEntityDate> comicDates;
    @SerializedName("prices") private List<ComicEntityPrice> comicPrices;
    @SerializedName("thumbnail") private ComicImageEntity comicThumbnail;
    @SerializedName("images") private List<ComicImageEntity> comicImages;
    @SerializedName("characters") private CharacterSummaryEntity characterSummary;

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

    public int getPageCount() {
        return pageCount;
    }

    public ComicSeriesEntity getSeriesName() {
        return seriesName;
    }

    public List<ComicEntityDate> getComicDates() {
        return comicDates;
    }

    public List<ComicEntityPrice> getComicPrices() {
        return comicPrices;
    }

    public List<ComicImageEntity> getComicImages() {
        return comicImages;
    }

    public ComicImageEntity getComicThumbnail() {
        return comicThumbnail;
    }

    public CharacterSummaryEntity getCharacterSummary() {
        return characterSummary;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComicEntity that = (ComicEntity) o;

        if (id != that.id) return false;
        if (pageCount != that.pageCount) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (format != null ? !format.equals(that.format) : that.format != null) return false;
        if (seriesName != null ? !seriesName.equals(that.seriesName) : that.seriesName != null)
            return false;
        if (comicDates != null ? !comicDates.equals(that.comicDates) : that.comicDates != null)
            return false;
        if (comicPrices != null ? !comicPrices.equals(that.comicPrices) : that.comicPrices != null)
            return false;
        if (comicThumbnail != null ? !comicThumbnail.equals(that.comicThumbnail) : that.comicThumbnail != null)
            return false;
        if (comicImages != null ? !comicImages.equals(that.comicImages) : that.comicImages != null)
            return false;
        return characterSummary != null ? characterSummary.equals(that.characterSummary) : that.characterSummary == null;

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
        result = 31 * result + (comicThumbnail != null ? comicThumbnail.hashCode() : 0);
        result = 31 * result + (comicImages != null ? comicImages.hashCode() : 0);
        result = 31 * result + (characterSummary != null ? characterSummary.hashCode() : 0);
        return result;
    }
}
