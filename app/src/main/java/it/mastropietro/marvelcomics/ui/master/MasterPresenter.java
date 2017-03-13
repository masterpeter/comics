package it.mastropietro.marvelcomics.ui.master;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.UseCase;
import it.mastropietro.marvelcomics.usecase.UseCaseFactory;
import rx.Subscriber;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

class MasterPresenter implements MasterContract.Presenter {

    private UseCaseFactory<Integer> getComicsUseCaseFactory;
    private MasterContract.View viewModel;
    private int pageNumber;

    UseCase getComicsFromCharacterId;

    @Inject
    public MasterPresenter(@Named("comicsUseCaseFactory") UseCaseFactory<Integer> getComicsUseCaseFactory,
                           MasterContract.View viewModel) {
        this.getComicsUseCaseFactory = getComicsUseCaseFactory;
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        executeGetComicsUseCase();
    }

    private void executeGetComicsUseCase() {
        if (getComicsFromCharacterId != null) {
            getComicsFromCharacterId.unsubscribe();
        }
        getComicsFromCharacterId = getComicsUseCaseFactory.createUseCase(pageNumber + 1);
        getComicsFromCharacterId.execute(new ComicListSubscriber());
    }

    @Override
    public void stop() {
        if (getComicsFromCharacterId != null) {
            getComicsFromCharacterId.unsubscribe();
        }
    }

    @Override public void getMoreComics() {
        viewModel.showLoading();
        executeGetComicsUseCase();
    }

    private final class ComicListSubscriber extends Subscriber<List<Comic>> {
        @Override public void onCompleted() {
            pageNumber++;
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