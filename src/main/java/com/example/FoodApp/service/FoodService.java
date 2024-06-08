package com.example.FoodApp.service;

import java.util.Optional;

import com.example.FoodApp.entity.Food;

public interface FoodService {

	Iterable<Food> getAllFoods();
	Optional<Food> getFoodById(Long id);
	Food createFood(Food food);
	Food updateFood(Long id, Food foodDetails);
	void deleteFood(Long id);
}
