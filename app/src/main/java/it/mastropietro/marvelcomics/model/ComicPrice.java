package it.mastropietro.marvelcomics.model;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public final class ComicPrice {

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
}
