package com.gildedrose;


public class ItemUpdatableFactory {
    public static ItemUpdatable creatItemUpdatableFor(String name) {

        switch(name) {
            case "CONJURED":
                return new ConjuredUpdatable();

            case "AGED_BRIE":
                return new AgedBrieUpdatable();

            default:
                return new DefaultItemUpdatable();

        }

    }
}


