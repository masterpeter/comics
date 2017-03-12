package it.mastropietro.marvelcomics.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public final class ComicPrice implements Parcelable {

    private final String type;
    private final String price;

    public ComicPrice(String type, String price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComicPrice that = (ComicPrice) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.price);
    }

    protected ComicPrice(Parcel in) {
        this.type = in.readString();
        this.price = in.readString();
    }

    public static final Creator<ComicPrice> CREATOR = new Creator<ComicPrice>() {
        @Override public ComicPrice createFromParcel(Parcel source) {
            return new ComicPrice(source);
        }

        @Override public ComicPrice[] newArray(int size) {
            return new ComicPrice[size];
        }
    };
}
