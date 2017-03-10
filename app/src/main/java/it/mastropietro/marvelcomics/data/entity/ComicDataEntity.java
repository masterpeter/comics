package it.mastropietro.marvelcomics.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

class ComicDataEntity {

    @SerializedName("offset") private int offset;
    @SerializedName("limit") private int limit;
    @SerializedName("total") private int total;
    @SerializedName("count") private int count;
    @SerializedName("results") private List<ComicEntity> comicEntities;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public List<ComicEntity> getComicEntities() {
        return comicEntities;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComicDataEntity that = (ComicDataEntity) o;

        if (offset != that.offset) return false;
        if (limit != that.limit) return false;
        if (total != that.total) return false;
        if (count != that.count) return false;
        return comicEntities != null ? comicEntities.equals(that.comicEntities) : that.comicEntities == null;

    }

    @Override public int hashCode() {
        int result = offset;
        result = 31 * result + limit;
        result = 31 * result + total;
        result = 31 * result + count;
        result = 31 * result + (comicEntities != null ? comicEntities.hashCode() : 0);
        return result;
    }
}
