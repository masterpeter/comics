package it.mastropietro.marvelcomics.ui.master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.data.di.RepositoryModule;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.ui.ComicGalleryPager;
import it.mastropietro.marvelcomics.ui.detail.DetailActivity;
import it.mastropietro.marvelcomics.ui.detail.DetailView;
import it.mastropietro.marvelcomics.ui.di.DaggerMasterComponent;

import static it.mastropietro.marvelcomics.ui.master.ComicListAdapter.OnComicClickListener;
import static it.mastropietro.marvelcomics.ui.master.ComicListAdapter.OnLastItemReachedListener;

public class MasterActivity
        extends AppCompatActivity
        implements MasterContract.View, OnComicClickListener, OnLastItemReachedListener {

    @BindView(R.id.master_comic_list) RecyclerView comicList;
    @BindView(R.id.master_toolbar) Toolbar masterToolbar;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @Nullable @BindView(R.id.detail_view) DetailView detailView;
    @Nullable @BindView(R.id.detail_view_pager) ComicGalleryPager viewPager;
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
        presenter.setViewModel(this);
        presenter.start();
    }

    private void init() {
        initInject();
        initToolbar();
        initRecyclerView();
    }

    private void initInject() {
        DaggerMasterComponent.builder()
                .repositoryModule(new RepositoryModule(getApplicationContext()))
                .build()
                .inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    private void initToolbar() {
        setSupportActionBar(masterToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        listAdapter.setOnComicClickListener(this);
        listAdapter.setOnLastItemReachedListener(this);
        comicList.setAdapter(listAdapter);
        comicList.setLayoutManager(layoutManager);
    }

    @Override public void onComicClick(Comic comic) {
        presenter.onComicClick(comic);
    }

    @Override
    public void navigateToDetailActivity(Comic comic) {
        startActivity(DetailActivity.getCallingIntent(this, comic));
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void updateDetailView(Comic comic) {
        detailView.bindData(comic);
        viewPager.bindImages(comic.getImages());
    }

    @Override public boolean isTablet() {
        return detailView != null;
    }

    @Override public void showLoading() {
        Toast.makeText(this, R.string.loading_data, Toast.LENGTH_SHORT).show();
    }

    @Override public void showComicList(List<Comic> comicList) {
        listAdapter.setComicList(comicList);
    }

    @Override public void showError() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.generic_error_title)
                .setMessage(R.string.generic_error_message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void onLastItemReached() {
        presenter.getMoreComics();
    }
}