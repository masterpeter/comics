package it.mastropietro.marvelcomics.data.entity.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import it.mastropietro.marvelcomics.data.entity.CharacterEntity;
import it.mastropietro.marvelcomics.data.entity.CharacterSummaryEntity;
import it.mastropietro.marvelcomics.data.entity.ComicEntity;
import it.mastropietro.marvelcomics.data.entity.ComicEntityDate;
import it.mastropietro.marvelcomics.data.entity.ComicEntityPrice;
import it.mastropietro.marvelcomics.data.entity.ComicImageEntity;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.model.ComicDate;
import it.mastropietro.marvelcomics.model.ComicPrice;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
@Singleton
public class ComicMapper implements Mapper<ComicEntity, Comic> {

    // Is there something that is not optional in Marvel APIs docs? :D
    private static final String NOT_AVAILABLE = "N/A";

    @Inject
    public ComicMapper() {
    }

    @Override public Comic map(ComicEntity comicEntity) {
        return new Comic.Builder()
                .id(comicEntity.getId())
                .title(mapTitle(comicEntity))
                .description(mapDescription(comicEntity))
                .isbn(mapISBN(comicEntity))
                .format(mapFormat(comicEntity))
                .pageCount(mapPageCount(comicEntity))
                .seriesName(mapSeriesName(comicEntity))
                .comicDates(mapDates(comicEntity.getComicDates()))
                .comicPrices(mapPrices(comicEntity.getComicPrices()))
                .thumbnail(mapImage(comicEntity.getComicThumbnail()))
                .images(mapImages(comicEntity.getComicImages()))
                .characterList(mapCharactersSummaries(comicEntity.getCharacterSummary()))
                .build();
    }

    private String mapPageCount(ComicEntity comicEntity) {
        return comicEntity.getPageCount() == 0 ? NOT_AVAILABLE : String.valueOf(comicEntity.getPageCount());
    }

    private String mapTitle(ComicEntity comicEntity) {
        return comicEntity.getTitle().isEmpty() ? NOT_AVAILABLE : comicEntity.getTitle();
    }

    private String mapDescription(ComicEntity comicEntity) {
        return comicEntity.getDescription().isEmpty() ? NOT_AVAILABLE : comicEntity.getDescription();
    }

    private String mapISBN(ComicEntity comicEntity) {
        return comicEntity.getIsbn().isEmpty() ? NOT_AVAILABLE : comicEntity.getIsbn();
    }

    private String mapFormat(ComicEntity comicEntity) {
        return comicEntity.getFormat().isEmpty() ? NOT_AVAILABLE : comicEntity.getFormat();
    }

    private String mapSeriesName(ComicEntity comicEntity) {
        return comicEntity.getSeriesName() == null ? NOT_AVAILABLE : comicEntity.getSeriesName().getName().isEmpty() ? NOT_AVAILABLE : comicEntity.getSeriesName().getName();
    }

    private List<ComicDate> mapDates(List<ComicEntityDate> comicDates) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        List<ComicDate> dates = new ArrayList<>();
        if (comicDates != null) {
            for (ComicEntityDate comicEntityDate : comicDates) {
                String type = comicEntityDate.getType();
                String date = simpleDateFormat.format(comicEntityDate.getDate());
                if (type != null && date != null) {
                    dates.add(new ComicDate(type, date));
                }
            }
        }
        return dates;
    }

    private List<ComicPrice> mapPrices(List<ComicEntityPrice> comicPrices) {
        List<ComicPrice> prices = new ArrayList<>();
        if (comicPrices != null) {
            for (ComicEntityPrice comicPrice : comicPrices) {
                String type = comicPrice.getType();
                String price = comicPrice.getPrice() == 0f ? NOT_AVAILABLE : String.valueOf(comicPrice.getPrice());
                if (type != null) {
                    prices.add(new ComicPrice(type, price));
                }
            }
        }
        return prices;
    }

    private List<String> mapImages(List<ComicImageEntity> comicThumbnails) {
        List<String> thumbnails = new ArrayList<>();
        if (comicThumbnails != null) {
            for (ComicImageEntity comicThumbnail : comicThumbnails) {
                String image = mapImage(comicThumbnail);
                if (!image.isEmpty()) {
                    thumbnails.add(image);
                }
            }
        }
        return thumbnails;
    }

    private String mapImage(ComicImageEntity image) {
        String path = image.getPath();
        String fileExt = image.getFileExt();
        if (path != null && fileExt != null) {
            return path + "." + fileExt;
        }
        return "";
    }

    private List<String> mapCharactersSummaries(CharacterSummaryEntity summaries) {
        List<String> characterSummaries = new ArrayList<>();
        if (summaries != null) {
            List<CharacterEntity> characters = summaries.getCharacters();
            if (characters != null) {
                for (CharacterEntity character : characters) {
                    String name = character.getName();
                    if (name != null) {
                        characterSummaries.add(name);
                    }
                }
            }
        }
        return characterSummaries;
    }
}