package it.mastropietro.marvelcomics.ui.detail;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class ImageAdapter extends PagerAdapter {

    public static final int OFFSCREEN_PAGE_LIMIT = 3;

    private List<String> images;

    public ImageAdapter(List<String> images) {
        this.images = images;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(imageView.getContext()).load(images.get(position)).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override public int getCount() {
        return images.size();
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
