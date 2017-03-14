package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class DetailCharactersView extends CardView {

    @BindView(R.id.characters_container) LinearLayout charactersContainer;

    public DetailCharactersView(Context context) {
        this(context, null);
    }

    public DetailCharactersView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailCharactersView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.detail_view_characters, this, true);
        ButterKnife.bind(this);
    }

    public void bindData(Comic comic) {
        charactersContainer.removeAllViews();
        List<String> characters = comic.getCharacters();
        for (String character : characters) {
            TextView textView = new TextView(getContext());
            textView.setText(character);
            charactersContainer.addView(textView);
        }
    }
}
