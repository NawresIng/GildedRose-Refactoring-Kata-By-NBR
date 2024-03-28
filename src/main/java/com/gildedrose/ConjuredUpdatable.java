package com.gildedrose;

public class ConjuredUpdatable extends DefaultItemUpdatable {

    @Override
    protected int getQualityDecrement(int sellIn) {
        return super.getQualityDecrement(sellIn) * 2;
    }
}
