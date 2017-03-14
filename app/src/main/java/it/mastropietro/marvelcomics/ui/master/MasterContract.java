package it.mastropietro.marvelcomics.ui.master;

import java.util.List;

import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public interface MasterContract {

    interface Presenter {
        void setViewModel(View viewModel);

        void start();

        void stop();

        void getMoreComics();

        void onComicClick(Comic comic);
    }

    interface View {
        void showLoading();

        void showComicList(List<Comic> comicList);

        void showError();

        void hideLoading();

        void navigateToDetailActivity(Comic comic);

        void updateDetailView(Comic comic);

        boolean isTablet();
    }
}
