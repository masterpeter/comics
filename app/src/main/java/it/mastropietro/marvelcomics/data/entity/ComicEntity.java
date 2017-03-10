package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

class ComicEntity {

    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("isbn") private String isbn;
    @SerializedName("format") private String format;
    @SerializedName("pageCount") private int pageCount;
    @SerializedName("seriesName") private ComicSeriesEntity seriesName;
    @SerializedName("dates") private List<ComicEntityDate> comicDates;
    @SerializedName("prices") private List<ComicEntityPrice> comicPrices;
    @SerializedName("images") private List<ComicThumbnailEntity> thumbnails;
    @SerializedName("characters") private List<CharacterSummaryEntity> characterList;
}
