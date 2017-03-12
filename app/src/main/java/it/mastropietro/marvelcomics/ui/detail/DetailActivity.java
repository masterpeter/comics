package it.mastropietro.marvelcomics.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

public class DetailActivity extends AppCompatActivity {

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
        Comic comic = getIntent().getParcelableExtra(COMIC_DETAIL);
        ((DetailView) findViewById(R.id.comic_detail_view)).bindData(comic);
    }
}
