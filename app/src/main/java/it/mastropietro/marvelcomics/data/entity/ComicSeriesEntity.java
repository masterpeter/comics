package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicSeriesEntity {

    @SerializedName("name") private String name;

    public String getName() {
        return name;
    }
}
