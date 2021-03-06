package it.mastropietro.marvelcomics.ui.master;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import it.mastropietro.marvelcomics.Constants;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.UseCase;
import it.mastropietro.marvelcomics.usecase.UseCaseFactory;
import rx.Subscriber;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

class MasterPresenter implements MasterContract.Presenter {

    int pageNumber;
    boolean canLoadMore;
    UseCaseFactory<Integer> getComicsUseCaseFactory;
    UseCase getComicsFromCharacterId;
    MasterContract.View viewModel;

    @Inject
    public MasterPresenter(@Named("comicsUseCaseFactory") UseCaseFactory<Integer> getComicsUseCaseFactory) {
        this.getComicsUseCaseFactory = getComicsUseCaseFactory;
        this.canLoadMore = true;
    }

    @Override
    public void start() {
        executeGetComicsUseCase();
    }

    private void executeGetComicsUseCase() {
        if (getComicsFromCharacterId != null) {
            getComicsFromCharacterId.unsubscribe();
        }
        getComicsFromCharacterId = getComicsUseCaseFactory.createUseCase(pageNumber);
        getComicsFromCharacterId.execute(new ComicListSubscriber());
    }

    @Override
    public void stop() {
        if (getComicsFromCharacterId != null) {
            getComicsFromCharacterId.unsubscribe();
        }
        viewModel = null;
    }

    @Override public void getMoreComics() {
        if (canLoadMore) {
            viewModel.showLoading();
            executeGetComicsUseCase();
        }
    }

    @Override public void onComicClick(Comic comic) {
        if (viewModel.isTablet()) {
            viewModel.updateDetailView(comic);
        } else {
            viewModel.navigateToDetailActivity(comic);
        }
    }

    @Override
    public void setViewModel(MasterContract.View viewModel) {
        this.viewModel = viewModel;
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
            canLoadMoreComics(comics);
        }
    }

    private void canLoadMoreComics(List<Comic> comics) {
        canLoadMore = !comics.isEmpty() && comics.size() == Constants.COMIC_LIMIT;
    }
}