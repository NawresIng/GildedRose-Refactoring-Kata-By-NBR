package com.gildedrose;

import java.util.Arrays;
import static com.gildedrose.ItemUpdatableFactory.creatItemUpdatableFor;
public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(item -> creatItemUpdatableFor(item.name).updateQuality(item));
    }

}
