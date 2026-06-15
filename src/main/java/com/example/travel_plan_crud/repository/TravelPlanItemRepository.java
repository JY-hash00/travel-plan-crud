package com.example.travel_plan_crud.repository;

import com.example.travel_plan_crud.entity.TravelPlanItem;
import org.springframework.data.repository.CrudRepository;

public interface TravelPlanItemRepository extends CrudRepository<TravelPlanItem, Long> {

}
