package it.mastropietro.marvelcomics;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class Utils {
    public static <T> boolean isEmpty(T t) {
        return t == null || (t instanceof String && ((String) t).isEmpty());
    }
}
