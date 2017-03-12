package it.mastropietro.marvelcomics.data.entity.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import it.mastropietro.marvelcomics.Utils;
import it.mastropietro.marvelcomics.data.entity.CharacterEntity;
import it.mastropietro.marvelcomics.data.entity.CharacterSummaryEntity;
import it.mastropietro.marvelcomics.data.entity.ComicEntity;
import it.mastropietro.marvelcomics.data.entity.ComicEntityDate;
import it.mastropietro.marvelcomics.data.entity.ComicEntityPrice;
import it.mastropietro.marvelcomics.data.entity.ComicImageEntity;
import it.mastropietro.marvelcomics.data.entity.ComicSeriesEntity;
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

    private static String mapTitle(ComicEntity comicEntity) {
        return Utils.isEmpty(comicEntity.getTitle()) ? NOT_AVAILABLE : comicEntity.getTitle();
    }

    private static String mapDescription(ComicEntity comicEntity) {
        return Utils.isEmpty(comicEntity.getDescription()) ? NOT_AVAILABLE : comicEntity.getDescription();
    }

    private static String mapISBN(ComicEntity comicEntity) {
        return Utils.isEmpty(comicEntity.getIsbn()) ? NOT_AVAILABLE : comicEntity.getIsbn();
    }

    private static String mapFormat(ComicEntity comicEntity) {
        return Utils.isEmpty(comicEntity.getFormat()) ? NOT_AVAILABLE : comicEntity.getFormat();
    }

    private static String mapSeriesName(ComicEntity comicEntity) {
        ComicSeriesEntity comicSeries = comicEntity.getSeriesName();
        if (comicSeries != null) {
            String comicSeriesName = comicSeries.getName();
            return Utils.isEmpty(comicSeriesName) ? NOT_AVAILABLE : comicSeriesName;
        }
        return NOT_AVAILABLE;
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

    private static List<ComicPrice> mapPrices(List<ComicEntityPrice> comicPrices) {
        List<ComicPrice> prices = new ArrayList<>();
        if (comicPrices != null) {
            for (ComicEntityPrice comicPrice : comicPrices) {
                String type = comicPrice.getType();
                String price = comicPrice.getPrice() == 0f ? NOT_AVAILABLE : String.valueOf(comicPrice.getPrice());
                if (!Utils.isEmpty(type)) {
                    prices.add(new ComicPrice(type, price));
                }
            }
        }
        return prices;
    }

    private List<String> mapImages(List<ComicImageEntity> comicImages) {
        List<String> images = new ArrayList<>();
        if (comicImages != null) {
            for (ComicImageEntity comicThumbnail : comicImages) {
                String image = mapImage(comicThumbnail);
                if (!Utils.isEmpty(image)) {
                    images.add(image);
                }
            }
        }
        return images;
    }

    private static String mapImage(ComicImageEntity image) {
        String path = image.getPath();
        String fileExt = image.getFileExt();
        if (!Utils.isEmpty(path) && !Utils.isEmpty(fileExt)) {
            return path + "." + fileExt;
        }
        return "";
    }

    private static List<String> mapCharactersSummaries(CharacterSummaryEntity summaries) {
        List<String> characterSummaries = new ArrayList<>();
        if (summaries != null) {
            List<CharacterEntity> characters = summaries.getCharacters();
            if (characters != null) {
                for (CharacterEntity character : characters) {
                    String name = character.getName();
                    if (!Utils.isEmpty(name)) {
                        characterSummaries.add(name);
                    }
                }
            }
        }
        return characterSummaries;
    }
}