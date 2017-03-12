package it.mastropietro.marvelcomics.ui.master;

import java.util.List;

import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public interface MasterContract {

    interface Presenter {
        void start();

        void stop();

        void getMoreComics();
    }

    interface View {
        void hideLoading();

        void showComicList(List<Comic> comicList);

        void showError();
    }
}
