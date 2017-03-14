package it.mastropietro.marvelcomics.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.List;

import it.mastropietro.marvelcomics.ui.detail.ImageAdapter;

/**
 * Created by Angelo Mastropietro on 14/03/17.
 */

public class ComicGalleryPager extends ViewPager {

    public static final int OFFSCREEN_PAGE_LIMIT = 3;

    private ImageAdapter imageAdapter;

    public ComicGalleryPager(Context context) {
        this(context, null);
    }

    public ComicGalleryPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        imageAdapter = new ImageAdapter();
        setAdapter(imageAdapter);
        setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    }

    public void bindImages(List<String> images) {
        imageAdapter.setImages(images);
        imageAdapter.notifyDataSetChanged();
    }
}
