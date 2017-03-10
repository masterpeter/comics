package it.mastropietro.marvelcomics.model;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public final class CharacterSummary {

    private final String name;
    private final String role;

    public CharacterSummary(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterSummary that = (CharacterSummary) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return role != null ? role.equals(that.role) : that.role == null;

    }

    @Override public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
