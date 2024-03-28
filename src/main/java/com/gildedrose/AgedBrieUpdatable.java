package com.gildedrose;

public class AgedBrieUpdatable extends DefaultItemUpdatable {

    public static final int MAX_QUALITY = 50;

    @Override
    public void updateQuality(Item item) {
        if(item.quality < 50) {
            item.quality++;
        }
        item.sellIn--;
    }

}
