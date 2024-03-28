package com.gildedrose;

import static java.lang.Math.max;

public class DefaultItemUpdatable implements ItemUpdatable {

    @Override
    public void updateQuality(Item item) {

        item.quality = item.quality - getQualityDecrement(item.sellIn);
        if(item.quality < 0) {
            item.quality = 0;
        }
        item.sellIn--;
    }

    protected int getQualityDecrement(int sellIn) {
        return sellHasPassed(sellIn) ? 2 : 1;
    }
    protected boolean sellHasPassed(int sellIn) {
        return sellIn <= 0;
    }
}

