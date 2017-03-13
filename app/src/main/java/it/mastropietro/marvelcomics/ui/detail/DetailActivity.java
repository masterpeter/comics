package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_comic_title) Toolbar toolbar;
    @BindView(R.id.detail_comic_images) ViewPager viewPager;
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
        setImages(comic.getImages());
        detailComicView.bindData(comic);
    }

    private void setImages(List<String> images) {
        ImageAdapter imageAdapter = new ImageAdapter(images);
        viewPager.setAdapter(imageAdapter);
        viewPager.setOffscreenPageLimit(ImageAdapter.OFFSCREEN_PAGE_LIMIT);
        imageAdapter.notifyDataSetChanged();
    }
}
