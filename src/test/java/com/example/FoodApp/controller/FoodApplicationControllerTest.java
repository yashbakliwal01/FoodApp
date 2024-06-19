package com.example.FoodApp.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.example.FoodApp.entity.Food;
import com.example.FoodApp.exception.ResourceNotFoundException;
import com.example.FoodApp.service.FoodService;

public class FoodApplicationControllerTest {

	@InjectMocks
    private FoodApplicationController foodApplicationController;

    @Mock
    private FoodService foodService;

    @Mock
    private Authentication authentication;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testLogin() {
    	Mockito.when(authentication.isAuthenticated()).thenReturn(true);
    	
    	ResponseEntity<String> response = foodApplicationController.login(authentication);
    	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assertions.assertEquals("Login Successful", response.getBody());
    }
    
    @Test
    public void testGetAllFoods() throws Exception {
        List<Food> foods = new ArrayList<>();
        Food food1 = new Food();
        food1.setName("Food 1");
        food1.setDescription("Description 1");
        Food food2 = new Food();
        food2.setName("Food 2");
        food2.setDescription("Description 2");
        foods.add(food1);
        foods.add(food2);
        when(foodService.getAllFoods()).thenReturn(foods);

        ResponseEntity<Iterable<Food>> response = foodApplicationController.getAllFoods();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(foods, response.getBody());
    }
    
    @Test
    public void testGetFoodById() {
        Food food = new Food();
        food.setId(4L);
        food.setName("FrenchFries");
        food.setDescription("Peri Peri one are too spicy and crispy");
        when(foodService.getFoodById(4L)).thenReturn(Optional.of(food));

        ResponseEntity<Food> response = foodApplicationController.getFoodById(4L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGetFoodByIdNotFound() {
        when(foodService.getFoodById(10L)).thenReturn(Optional.empty());

        try {
        	foodApplicationController.getFoodById(10L);
        } catch (ResourceNotFoundException e) {
        	 Assertions.assertEquals("Food item not found", e.getMessage());
        }
    }
    
//    @Test
//    public void testCreateFood1() throws Exception {
//    	Food food = new Food();
//        food.setDescription("Paneer Onion Spicy");
//        food.setName("Pizza");
//        
//        Food savedFood = new Food();
//        savedFood.setId(12L);
//        savedFood.setDescription("Paneer Onion Spicy");
//        savedFood.setName("Pizza");
//        
//        ResponseEntity<Food> response = foodApplicationController.createFood(food);
//        
//        when(foodService.createFood(Mockito.any(Food.class))).thenReturn(savedFood);
//        
//        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    }
    
    
    @Test
    public void testCreateFood2() throws Exception {
    	Food food1 = new Food();
        food1.setName("ssss");
        food1.setDescription("good");
        
        Food food2 = new Food();
        food2.setName("ppp");
        food2.setDescription("bad");
        
        ResponseEntity<Food> response = foodApplicationController.createFood(food1);
        
        when(foodService.createFood(food1)).thenReturn(food2);
        
        Assertions. assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(food2, response.getBody());
    }

    @Test
    public void testUpdateFood() {
        Food foodDetails = new Food();
        foodDetails.setId(10L);
        foodDetails.setName("recent Food");
        foodDetails.setDescription("Description");

        Food updatedFood = new Food();
        updatedFood.setId(11L);
        updatedFood.setName("Updated Food");
        updatedFood.setDescription("Updated Description");

        when(foodService.updateFood(10L, foodDetails)).thenReturn(updatedFood);

        ResponseEntity<Food> responseEntity = foodApplicationController.updateFood(10L, foodDetails);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(updatedFood, responseEntity.getBody());
    }
}
