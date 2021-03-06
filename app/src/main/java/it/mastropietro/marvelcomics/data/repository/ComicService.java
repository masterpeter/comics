package it.mastropietro.marvelcomics.data.repository;

import java.util.Map;

import it.mastropietro.marvelcomics.data.ApiResponse;
import it.mastropietro.marvelcomics.data.entity.ComicDataEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public interface ComicService {

    @GET("/v1/public/characters/{characterId}/comics")
    Call<ApiResponse<ComicDataEntity>> getComicList(@Path("characterId") int characterId,
                                                    @Query("offset") int offset,
                                                    @QueryMap Map<String, String> apiKeyQuery);
}
