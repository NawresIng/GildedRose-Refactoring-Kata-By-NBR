package com.gildedrose;

public enum ItemType {
    BRIE ("Aged Brie"),
    BACKSTAGE ("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    CONJURED("Conjured "),
    DEFAULT("Others");

    public final String type;

    ItemType(String type) {
        this.type = type;
    }
}
