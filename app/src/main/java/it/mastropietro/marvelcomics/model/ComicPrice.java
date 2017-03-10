package it.mastropietro.marvelcomics.model;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public final class ComicPrice {

    private final String type;
    private final float price;

    public ComicPrice(String type, float price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComicPrice that = (ComicPrice) o;

        if (Float.compare(that.price, price) != 0) return false;
        return type != null ? type.equals(that.type) : that.type == null;

    }

    @Override public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }
}
