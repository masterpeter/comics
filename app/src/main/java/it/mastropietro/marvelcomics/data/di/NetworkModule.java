package it.mastropietro.marvelcomics.data.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.mastropietro.marvelcomics.data.repository.cache.CacheFactory;
import it.mastropietro.marvelcomics.data.repository.cache.CacheFactoryImp;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */
@Module
public class NetworkModule {

    private static final String baseUrl = "http://gateway.marvel.com/";

    @Singleton @Provides
    OkHttpClient httpClient(CacheFactory cache) {
        return new OkHttpClient.Builder()
                .cache(cache.createCache())
                .build();
    }

    @Singleton @Provides
    Retrofit retrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton @Provides
    CacheFactory cacheFactory(CacheFactoryImp cacheFactoryImp) {
        return cacheFactoryImp;
    }
}
