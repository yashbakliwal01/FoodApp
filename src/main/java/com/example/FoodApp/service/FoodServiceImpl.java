package com.example.FoodApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodApp.entity.Food;
import com.example.FoodApp.repository.FoodRepository;

@Service("foodServiceImpl")
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public Iterable<Food> getAllFoods() {
		return foodRepository.findAll();
	}


	@Override
	public Optional<Food> getFoodById(Long id) {
		return foodRepository.findById(id);
	}

	@Override
	public Food createFood(Food food) {
		return foodRepository.save(food);
	}


	@Override
	public Food updateFood(Long id, Food foodDetails) {
		Optional<Food> optionalFood	 = foodRepository.findById(id);
		if(!optionalFood.isPresent()) {
			return null;
		}
		
		Food food = optionalFood.get();
		food.setName(foodDetails.getName());
		food.setDescription(foodDetails.getDescription());
		return foodRepository.save(food);
	}


	@Override
	public void deleteFood(Long id) {
		foodRepository.deleteById(id);
	}


}
