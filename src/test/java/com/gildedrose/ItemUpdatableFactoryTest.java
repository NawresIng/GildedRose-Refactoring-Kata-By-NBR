package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ItemUpdatableFactoryTest {

    ItemUpdatableFactory itemUpdatableFactory;
    @BeforeEach
    void setUp() {
      itemUpdatableFactory = new ItemUpdatableFactory();
    }
    @Test
    public void createsDefaultUpdaterForUnknownItem()  {

    }


}