package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.ui.ComicGalleryPager;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_comic_title) Toolbar toolbar;
    @BindView(R.id.detail_comic_images) ComicGalleryPager viewPager;
    @BindView(R.id.detail_comic_view) DetailView detailComicView;

    private static final String COMIC_DETAIL = "comic_detail";

    public static Intent getCallingIntent(Context context, Comic comic) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(COMIC_DETAIL, comic);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
    }

    @SuppressWarnings("ConstantConditions")
    private void init() {
        Comic comic = getIntent().getParcelableExtra(COMIC_DETAIL);
        getSupportActionBar().setTitle(comic.getTitle());
        detailComicView.bindData(comic);
        viewPager.bindImages(comic.getImages());
    }
}
