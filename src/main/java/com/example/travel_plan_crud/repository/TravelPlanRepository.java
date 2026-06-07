package com.example.travel_plan_crud.repository;

import com.example.travel_plan_crud.entity.TravelPlan;
import org.springframework.data.repository.CrudRepository;

public interface TravelPlanRepository extends CrudRepository<TravelPlan, Long> {
    
}