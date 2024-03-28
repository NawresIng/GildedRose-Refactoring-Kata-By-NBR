package com.gildedrose;


public class ItemUpdatableFactory {
    public static ItemUpdatable creatItemUpdatableFor(String name) {

        /* ToDo: Need to change values with enums ..*/
        switch(name) {
            case "Conjured":
                return new ConjuredUpdatable();

            case "Aged Brie":
                return new AgedBrieUpdatable();

            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassesUpdatable();

            case "Sulfuras, Hand of Ragnaros":
                return new LegendaryUpdatable();

            default:
                return new DefaultItemUpdatable();

        }

    }
}


