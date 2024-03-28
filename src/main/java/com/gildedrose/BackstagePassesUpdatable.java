package com.gildedrose;

public class BackstagePassesUpdatable extends DefaultItemUpdatable {

    public void updateQuality(Item item) {
        if(sellHasPassed(item.sellIn)) {
            item.quality = 0;
        }
        else if(item.quality < 50) {
            Integer qualityIncrease = qualityIncreaseWith(item.sellIn);
            item.quality += qualityIncrease;
        }
        item.sellIn--;
    }

    private Integer qualityIncreaseWith(Integer sellIn) {

        // To be replaced by a switch
        if(sellIn <= 5) {
            return 3;
        }
        else if (sellIn <= 10) {
            return 2;
        }
        else {
            return 1;
        }
    }



}
