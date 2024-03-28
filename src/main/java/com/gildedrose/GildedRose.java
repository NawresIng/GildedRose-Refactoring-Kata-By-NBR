package com.gildedrose;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityItem(item);
        }
    }

    public void updateQualityItem(Item item) {
        if (!item.name.equals(ItemType.AGED_BRIE.type)
                && !item.name.equals(ItemType.BACKSTAGE_PASSES.type)) {
            if (item.quality > 0) {
                if (!item.name.equals(ItemType.SULFURAS.type)) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals(ItemType.BACKSTAGE_PASSES.type)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

       if (!item.name.equals(ItemType.SULFURAS.type)) {
            item.sellIn = item.sellIn - 1;
        }


        if (item.sellIn < 0) {
            if (!item.name.equals(ItemType.AGED_BRIE.type)) {
                if (!item.name.equals(ItemType.BACKSTAGE_PASSES.type)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(ItemType.SULFURAS.type)) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }
}
