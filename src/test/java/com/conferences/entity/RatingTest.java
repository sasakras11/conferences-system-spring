package com.conferences.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RatingTest {

   private  Rating rating;


   @Before
   public void init() {
       rating = new Rating();
       rating.setRating(9);


   }
    @Test
   public void getBonuses() {
        Assert.assertEquals(81,rating.getBonuses());

    }
}