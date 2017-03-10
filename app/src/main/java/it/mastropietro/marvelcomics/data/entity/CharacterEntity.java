package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

class CharacterEntity {

    @SerializedName("name") private String name;
    @SerializedName("role") private String role;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
