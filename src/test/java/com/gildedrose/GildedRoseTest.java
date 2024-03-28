package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GildedRoseTest {

    private static final String NAME_VALUE = "foo";
    private static final int SELLIN_VALUE = 15;
    private static final int SELLIN_VALUE_10 = 10;
    private static final int SELLIN_VALUE_5 = 5;
    private static final int SELLIN_VALUE_NULL = 0;
    private static final int QUALITY_VALUE = 10;
    private static final int QUALITY_MAX_VALUE = 50;
    private static final int QUALITY_VALUE_NULL = 0;
    private GildedRose gr;


    /*Fix the unit test on error*/
    @Test
    void nameNeverChangeAfterUpdateQuality_at_the_Eof() {
        gr = createGrWithOneItem(NAME_VALUE, SELLIN_VALUE, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals("foo", gr.items[0].name);
    }

    /*All `items` have a `SellIn` value which denotes the number of days we have to sell the `items`*/
    @Test
    public void item_has_sellIn() {
        gr = createGrWithOneItem(NAME_VALUE, SELLIN_VALUE, QUALITY_VALUE);
        Assertions.assertEquals(SELLIN_VALUE, gr.items[0].sellIn);
    }

    /*All `items` have a `Quality` value which denotes how valuable the item is*/
    @Test
    public void item_has_quality() {
        gr = createGrWithOneItem(NAME_VALUE, SELLIN_VALUE, QUALITY_VALUE);
        Assertions.assertEquals(QUALITY_VALUE, gr.items[0].quality);
    }

    /*- At the end of each day our system lowers both values for every item*/
    @Test
    public void decrease_quality_and_sellIn_values_for_each_item_at_the_Eof() {

        gr = createGrWithOneItem(ItemType.DEFAULT.type, SELLIN_VALUE, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE - 1, getFirstItem().quality);
    }

    /*Once the sell by date has passed, `Quality` degrades twice as fast*/
    @Test
    public void decrease_quality_By1_if_sellIn_date_has_passed_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.DEFAULT.type, SELLIN_VALUE_NULL, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_NULL -1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE - 2, getFirstItem().quality);
    }

    /*The `Quality` of an item is never negative*/
    @Test
    public void quality_should_be_never_negative_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.DEFAULT.type, SELLIN_VALUE, QUALITY_VALUE_NULL);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE_NULL, getFirstItem().quality);
    }

    /*The `Quality` of an item is never negative even sellIn has passed*/
    @Test
    public void quality_should_be_never_negative_even_sellIn__date_has_passed_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.DEFAULT.type, SELLIN_VALUE, QUALITY_VALUE_NULL);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE_NULL, getFirstItem().quality);
    }

    /* "Aged Brie"__ actually increases in `Quality` the older it gets */
    @Test
    public void agedBrie_should_increase_quality_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.AGED_BRIE.type, SELLIN_VALUE, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE + 1, getFirstItem().quality);
    }

    @Test
    public void agedBrie_should_increaseBy2_if_sellIn_values_has_passed_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.AGED_BRIE.type, SELLIN_VALUE_NULL, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_NULL - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE + 2, getFirstItem().quality);
    }

    /* The `Quality` of an item is never more than `50` */
    @Test
    public void quality_should_be_never_more_50_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.AGED_BRIE.type, SELLIN_VALUE, QUALITY_MAX_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_MAX_VALUE, getFirstItem().quality);
    }

    /* The `Quality` of an item is never more than `50` even sellIn date passed */
    @Test
    public void quality_should_be_never_more_50_even_sellin_passed_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.AGED_BRIE.type, SELLIN_VALUE_NULL, QUALITY_MAX_VALUE - 1);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_NULL - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_MAX_VALUE, getFirstItem().quality);
    }
    @Test
    public void quality_should_be_null_if_sellin_date_passed_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.DEFAULT.type, SELLIN_VALUE_NULL , QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_NULL - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE - 2, getFirstItem().quality);
    }

    /* __"Sulfuras"__, being a legendary item, never has to be sold or decreases in `Quality`*/
    @Test
    public void sulfuras_quality_should_be_never_decreases_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.SULFURAS.type, SELLIN_VALUE, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE, getFirstItem().quality);
    }

    /*"Backstage passes"__, like aged brie, increases in `Quality` as its `SellIn` value approaches*/
    @Test
    public void backstage_quality_should_increaseBy1_MoreThan10Days_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, SELLIN_VALUE, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE + 1, getFirstItem().quality);
    }

    /*Backstage	`Quality` increases by `2` when there are `10` days or less */
    @Test
    public void backstage_quality_should_increaseBy2_lessOrEqualThan_10Days_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, SELLIN_VALUE_10, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_10 - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE + 2, getFirstItem().quality);
    }

    /*Backstage	`Quality` increases by  `3` when there are `5` days or less */
    @Test
    public void backstage_quality_should_increaseBy3_lessOrEqualThan_5Days_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, SELLIN_VALUE_5, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_5 - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE + 3, getFirstItem().quality);
    }
    @Test
    public void backstage_quality_should_increaseBy3_lessOrEqualThan_1Days_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, 1, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_NULL, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE + 3, getFirstItem().quality);
    }
    @Test
    public void backstage_quality_should_increaseBy3_lessOrEqualThan_6Days_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, SELLIN_VALUE_5+1, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_5, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE + 2, getFirstItem().quality);
    }

    /*Backstage	`Quality` drops to `0` after the concert */
    @Test
    public void backstage_quality_should_dropToZero_after_concert_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, SELLIN_VALUE_NULL, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(SELLIN_VALUE_NULL - 1, getFirstItem().sellIn);
        Assertions.assertEquals(QUALITY_VALUE_NULL, getFirstItem().quality);
    }
    @Test
    public void backstage_quality_should_Increases_By2_MoreThan_5Days_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, SELLIN_VALUE_5 + 1, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(QUALITY_VALUE + 2, getFirstItem().quality );
    }
    @Test
    public void backstage_quality_should_be_never_more_50_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.BACKSTAGE_PASSES.type, SELLIN_VALUE_5, QUALITY_MAX_VALUE-1);
        gr.updateQuality();
        Assertions.assertEquals(QUALITY_MAX_VALUE, getFirstItem().quality);
    }

   /*
    @Test
    public void conjured_quality_should_be_decreased_by_2_at_the_Eof() {
        gr = createGrWithOneItem(ItemType.CONJURED.type,  SELLIN_VALUE_5, QUALITY_VALUE);
        gr.updateQuality();
        Assertions.assertEquals(QUALITY_VALUE -2, getFirstItem().quality);
    }*/



    @Test
    public void testToString()
    {
        Item item = new Item("foo", SELLIN_VALUE_5, QUALITY_VALUE);
        Assertions.assertEquals("foo, 5, 10", item.toString());
    }

    private GildedRose createGrWithOneItem(String name, int sellIn, int quality) {
        return new GildedRose(new Item[]{new Item(name, sellIn, quality)});
    }

    private Item getFirstItem() {
        return gr.items[0];
    }
}
