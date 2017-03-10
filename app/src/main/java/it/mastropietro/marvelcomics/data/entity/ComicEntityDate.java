package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicEntityDate {

    @SerializedName("type") private String type;
    @SerializedName("date") private Date date;

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
