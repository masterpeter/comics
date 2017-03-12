package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.model.ComicDate;
import it.mastropietro.marvelcomics.model.ComicPrice;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class DetailDatesPricesView extends CardView {

    @BindView(R.id.dates_container) LinearLayout datesContainer;
    @BindView(R.id.prices_container) LinearLayout pricesContainer;

    public DetailDatesPricesView(Context context) {
        this(context, null);
    }

    public DetailDatesPricesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailDatesPricesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.detail_view_dates_prices, this, true);
        ButterKnife.bind(this);
    }

    public void bindData(Comic comic) {
        setDates(comic.getComicDates());
        setPrices(comic.getComicPrices());
    }

    private void setDates(List<ComicDate> comicDates) {
        for (ComicDate comicDate : comicDates) {
            String date = comicDate.getType() + ": " + comicDate.getDate();
            datesContainer.addView(buildTextView(date));
        }
    }

    private void setPrices(List<ComicPrice> comicPrices) {
        for (ComicPrice comicPrice : comicPrices) {
            String price = comicPrice.getType() + ": " + comicPrice.getPrice();
            pricesContainer.addView(buildTextView(price));
        }
    }

    private View buildTextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        return textView;
    }
}
