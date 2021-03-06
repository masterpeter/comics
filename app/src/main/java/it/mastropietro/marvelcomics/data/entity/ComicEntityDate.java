package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicEntityDate {

    @SerializedName("type") private String type;
    @SerializedName("date") private String date;

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
