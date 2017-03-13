package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class DetailView extends NestedScrollView {

    @BindView(R.id.detail_general_info) DetailGeneralInfoView detailGeneralInfoView;
    @BindView(R.id.detail_characters) DetailCharactersView detailCharacters;
    @BindView(R.id.detail_dates_prices) DetailDatesPricesView detailDatesPrices;
    @BindDimen(R.dimen.base_spacing) int baseSpacing;

    public DetailView(Context context) {
        this(context, null);
    }

    public DetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInjects();
        init();
    }

    private void init() {
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_grey));
    }

    private void initInjects() {
        LayoutInflater.from(getContext()).inflate(R.layout.detail_view, this);
        ButterKnife.bind(this);
    }

    public void bindData(Comic comic) {
        setCardsInfo(comic);
    }

    private void setCardsInfo(Comic comic) {
        detailGeneralInfoView.bindData(comic);
        detailCharacters.bindData(comic);
        detailDatesPrices.bindData(comic);
    }
}
