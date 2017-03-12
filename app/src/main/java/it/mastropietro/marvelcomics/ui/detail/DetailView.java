package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class DetailView extends CoordinatorLayout {

    @BindView(R.id.detail_comic_title) Toolbar toolbar;
    @BindView(R.id.detail_comic_images) ViewPager viewPager;
    @BindView(R.id.detail_general_info) DetailGeneralInfoView detailGeneralInfoView;
    @BindView(R.id.detail_characters) DetailCharactersView detailCharacters;
    @BindView(R.id.detail_dates_prices) DetailDatesPricesView detailDatesPrices;

    public DetailView(Context context) {
        this(context, null);
    }

    public DetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initInjects();
        initViews();
    }

    private void initInjects() {
        LayoutInflater.from(getContext()).inflate(R.layout.detail_view, this);
        ButterKnife.bind(this);
    }

    private void initViews() {
        ((AppCompatActivity) getContext()).setSupportActionBar(toolbar);
    }

    public void bindData(Comic comic) {
        setTitle(comic.getTitle());
        setImages();
        setCardsInfo(comic);
    }

    private void setImages() {
        // TODO: 12/03/17 Add viewpager adapter ecc ecc...
    }

    private void setTitle(String comicTitle) {
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(comicTitle);
    }

    private void setCardsInfo(Comic comic) {
        detailGeneralInfoView.bindData(comic);
        detailCharacters.bindData(comic);
        detailDatesPrices.bindData(comic);
    }
}
