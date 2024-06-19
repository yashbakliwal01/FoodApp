package com.example.FoodApp.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodTest {

	@Test
	public void testEntity() {
		Long id = 10L;
		String name = "Maggie";
		String description = "Prepared in 2 minutes";
		
		Food food = new Food();
		food.setId(id);
		food.setName(name);
		food.setDescription(description);
		
		Assertions.assertEquals(id, food.getId());
        Assertions.assertEquals(name, food.getName());
        Assertions.assertEquals(description, food.getDescription());
	}
	
}
