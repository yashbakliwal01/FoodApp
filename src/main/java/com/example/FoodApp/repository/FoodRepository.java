package com.example.FoodApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.FoodApp.entity.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long>{

}
