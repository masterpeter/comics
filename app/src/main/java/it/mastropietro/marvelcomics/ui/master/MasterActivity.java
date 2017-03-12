package it.mastropietro.marvelcomics.ui.master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.ui.detail.DetailActivity;
import it.mastropietro.marvelcomics.ui.di.DaggerMasterComponent;
import it.mastropietro.marvelcomics.ui.di.MasterModule;

public class MasterActivity
        extends AppCompatActivity
        implements MasterContract.View, ComicListAdapter.OnComicClickListener, ComicListAdapter.OnLastItemReachedListener {

    private static final String TAG = MasterActivity.class.getName();

    @BindView(R.id.master_comic_list) RecyclerView comicList;
    @Inject MasterPresenter presenter;
    @Inject ComicListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        ButterKnife.bind(this);
        init();
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.start();
    }

    private void init() {
        initInject();
        initRecyclerView();
    }

    private void initInject() {
        DaggerMasterComponent.builder().masterModule(new MasterModule(this)).build().inject(this);
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        listAdapter.setOnComicClickListener(this);
        listAdapter.setOnLastItemReachedListener(this);
        comicList.setAdapter(listAdapter);
        comicList.setLayoutManager(layoutManager);
    }

    @Override public void onComicClick(Comic comic) {
        startActivity(DetailActivity.getCallingIntent(this, comic));
    }

    @Override public void hideLoading() {

    }

    @Override public void showComicList(List<Comic> comicList) {
        listAdapter.setComicList(comicList);
    }

    @Override public void showError() {

    }

    @Override public void onLastItemReached() {
        presenter.getMoreComics();
    }
}
