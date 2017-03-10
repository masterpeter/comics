package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicEntityPrice {

    @SerializedName("type") private String type;
    @SerializedName("price") private float price;

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }
}
