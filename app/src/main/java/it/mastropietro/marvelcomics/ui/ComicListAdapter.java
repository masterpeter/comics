package it.mastropietro.marvelcomics.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

class ComicListAdapter extends RecyclerView.Adapter<ComicListAdapter.ComicViewHolder> {

    private OnComicClickListener onComicClickListener;
    private List<Comic> comicList;

    public interface OnComicClickListener {
        void onComicClick(Comic comic);
    }

    @Inject
    public ComicListAdapter() {
        comicList = new ArrayList<>();
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.comic_card, parent, false);
        return new ComicViewHolder(view);
    }

    @Override public void onBindViewHolder(ComicViewHolder holder, final int position) {
        Comic comic = comicList.get(position);
        holder.comicTile.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (onComicClickListener != null) {
                    onComicClickListener.onComicClick(comicList.get(position));
                }
            }
        });
        holder.comicTitle.setText(comic.getTitle());
        Picasso.with(holder.itemView.getContext()).load(comic.getThumbnail()).into(holder.comicThumb);
    }

    @Override public int getItemCount() {
        return comicList.size();
    }

    public void setOnComicClickListener(OnComicClickListener onComicClickListener) {
        this.onComicClickListener = onComicClickListener;
    }

    public void setComicList(List<Comic> comicList) {
        this.comicList.addAll(comicList);
        notifyDataSetChanged();
    }

    static class ComicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comic) CardView comicTile;
        @BindView(R.id.comic_thumb) ImageView comicThumb;
        @BindView(R.id.comic_title) TextView comicTitle;

        public ComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
