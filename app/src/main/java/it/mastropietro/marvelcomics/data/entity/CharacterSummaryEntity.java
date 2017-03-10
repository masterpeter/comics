package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class CharacterSummaryEntity {

    @SerializedName("items") private List<CharacterEntity> characters;

    public List<CharacterEntity> getCharacters() {
        return characters;
    }
}
