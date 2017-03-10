package it.mastropietro.marvelcomics.data.repository;

import it.mastropietro.marvelcomics.data.ApiResponse;
import it.mastropietro.marvelcomics.data.entity.ComicDataEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

interface ComicService {

    @GET("/v1/public/characters/{characterId}/comics")
    Call<ApiResponse<ComicDataEntity>> getComicList(@Path("characterId") int characterId);
}
