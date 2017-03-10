package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicImageEntity {

    @SerializedName("path") private String path;
    @SerializedName("extension") private String fileExt;

    public String getPath() {
        return path;
    }

    public String getFileExt() {
        return fileExt;
    }
}
