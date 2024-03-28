package com.gildedrose;

public class ConjuredUpdatable extends DefaultItemUpdatable {

    @Override
    public void updateQuality(Item item) {
        item.quality -= 2;
    }
}
