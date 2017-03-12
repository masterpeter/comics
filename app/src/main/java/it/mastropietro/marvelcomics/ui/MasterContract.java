package it.mastropietro.marvelcomics.ui;

import java.util.List;

import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public interface MasterContract {

    interface Presenter {
        void start();

        void stop();
    }

    interface View {
        void hideLoading();

        void showComicList(List<Comic> comicList);

        void showError();
    }
}
