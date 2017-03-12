package it.mastropietro.marvelcomics.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public final class ComicDate implements Parcelable {

    private final String type;
    private final String date;

    public ComicDate(String type, String date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComicDate comicDate = (ComicDate) o;

        if (type != null ? !type.equals(comicDate.type) : comicDate.type != null) return false;
        return date != null ? date.equals(comicDate.date) : comicDate.date == null;

    }

    @Override public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.date);
    }

    protected ComicDate(Parcel in) {
        this.type = in.readString();
        this.date = in.readString();
    }

    public static final Creator<ComicDate> CREATOR = new Creator<ComicDate>() {
        @Override public ComicDate createFromParcel(Parcel source) {
            return new ComicDate(source);
        }

        @Override public ComicDate[] newArray(int size) {
            return new ComicDate[size];
        }
    };
}
