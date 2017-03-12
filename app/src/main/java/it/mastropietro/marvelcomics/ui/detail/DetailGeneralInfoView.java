package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class DetailGeneralInfoView extends CardView {

    @BindView(R.id.description) TextView description;
    @BindView(R.id.series_name) TextView seriesName;
    @BindView(R.id.isbn) TextView isbn;
    @BindView(R.id.format) TextView format;
    @BindView(R.id.pages) TextView pages;

    public DetailGeneralInfoView(Context context) {
        this(context, null);
    }

    public DetailGeneralInfoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailGeneralInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.detail_view_info, this, true);
        ButterKnife.bind(this);
    }

    public void bindData(Comic comic) {
        description.setText(comic.getDescription());
        seriesName.setText(comic.getSeriesName());
        isbn.setText(comic.getIsbn());
        format.setText(comic.getFormat());
        pages.setText(comic.getPageCount());
    }
}
