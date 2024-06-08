package com.example.FoodApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodApp.entity.Food;
import com.example.FoodApp.exception.ResourceNotFoundException;
import com.example.FoodApp.service.FoodService;

@RestController
@RequestMapping("/api/foodApp")
public class FoodApplicationController {

	 @Autowired
	 private FoodService foodService;
	
	@GetMapping("/login")
	public ResponseEntity<String> login(Authentication authentication){
		if(authentication != null && authentication.isAuthenticated()) {
			return ResponseEntity.ok("Login Successful");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed!");
	}
	
	@GetMapping("/getallfoods")
	public ResponseEntity<Iterable<Food>> getAllFoods(){
		Iterable<Food> foods = foodService.getAllFoods();
		return ResponseEntity.ok(foods);
	}

	@GetMapping("/food/{id}")
	public ResponseEntity<Food> getFoodById(@PathVariable Long id){
	
		Food food = foodService.getFoodById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Food item not found with id: "+ id));
	
		return ResponseEntity.ok(food);
	}
	
	
	@PostMapping("/createFood")
	public ResponseEntity<Food> createFood(@RequestBody Food food){
		Food savedFood = foodService.createFood(food);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
	}
	
	
	@PutMapping("/updateFood/{id}")
	public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food foodDetails){
		Food updatedFood = foodService.updateFood(id,  foodDetails);
		if(updatedFood == null) {
			throw new ResourceNotFoundException("Food item not found with id: " + id);
		}
		return ResponseEntity.ok(updatedFood);
	}
	
	@DeleteMapping("/deleteFood/{id}")
	public ResponseEntity<Void> deleteFood(@PathVariable Long id){
		foodService.deleteFood(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}

