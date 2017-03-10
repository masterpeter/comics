package it.mastropietro.marvelcomics.model;

import java.util.Date;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public final class ComicDate {

    private final String type;
    private final Date date;

    public ComicDate(String type, Date date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComicDate comicDate = (ComicDate) o;

        if (type != null ? !type.equals(comicDate.type) : comicDate.type != null) return false;
        if (date != null ? !date.equals(comicDate.date) : comicDate.date != null) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
