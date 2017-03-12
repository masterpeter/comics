package it.mastropietro.marvelcomics.ui;

import java.util.List;

import javax.inject.Named;

import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.UseCase;
import rx.Subscriber;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

class MasterPresenter implements MasterContract.Presenter {

    private UseCase getComicsFromCharacterId;
    private MasterContract.View viewModel;

    public MasterPresenter(@Named("getComics") UseCase getComicsFromCharacterId,
                           MasterContract.View viewModel) {
        this.getComicsFromCharacterId = getComicsFromCharacterId;
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        getComicsFromCharacterId.execute(new ComicListSubscriber());
    }

    @Override
    public void stop() {
        if (getComicsFromCharacterId != null) {
            getComicsFromCharacterId.unsubscribe();
        }
    }

    private final class ComicListSubscriber extends Subscriber<List<Comic>> {
        @Override public void onCompleted() {
            viewModel.hideLoading();
        }

        @Override public void onError(Throwable e) {
            viewModel.showError();
        }

        @Override public void onNext(List<Comic> comics) {
            viewModel.showComicList(comics);
        }
    }
}
