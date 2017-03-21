package it.mastropietro.marvelcomics.data.repository.cache;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import it.mastropietro.marvelcomics.data.repository.clock.Clock;
import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */

public class CacheApiImp implements CacheApi<List<Comic>> {

    static final String CACHE_EXPIRE_KEY = "cache_exp";
    static final long ONE_HOUR_IN_MILLIS = 60 * 60 * 1000;

    private final Gson gson;
    private final SharedPreferences sharedPreferences;
    private final Clock clock;

    public CacheApiImp(Gson gson,
                       SharedPreferences sharedPreferences,
                       Clock clock) {
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
        this.clock = clock;
        initCache();
    }

    private void initCache() {
        if (getLastUpdateTimestamp().isEmpty()) {
            sharedPreferences.edit()
                    .putString(CACHE_EXPIRE_KEY, getCacheCreationTimestamp())
                    .apply();
        }
    }

    @NonNull private String getCacheCreationTimestamp() {
        return String.valueOf(clock.getCurrentTimeInMillis());
    }

    @NonNull private String getLastUpdateTimestamp() {
        return sharedPreferences.getString(CACHE_EXPIRE_KEY, "");
    }

    @Override public List<Comic> get(String key) {
        String comicListString = sharedPreferences.getString(key, "");
        if (comicListString.isEmpty()) {
            return Collections.emptyList();
        }
        return gson.fromJson(comicListString, getComicListTypeToken());
    }

    @Override public boolean isCacheExpired() {
        long toCompare = Long.valueOf(getLastUpdateTimestamp()) + ONE_HOUR_IN_MILLIS;
        return clock.isCurrentTimeAfter(String.valueOf(toCompare));
    }

    @Override public void flush() {
        sharedPreferences.edit()
                .clear()
                .putString(CACHE_EXPIRE_KEY, getCacheCreationTimestamp())
                .apply();
    }

    @Override public void store(String key, List<Comic> comics) {
        String serializedList = gson.toJson(comics, getComicListTypeToken());
        sharedPreferences.edit().putString(key, serializedList).apply();
    }

    private Type getComicListTypeToken() {
        return new TypeToken<List<Comic>>() {
        }.getType();
    }
}
