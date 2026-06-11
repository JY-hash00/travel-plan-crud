package com.example.travel_plan_crud.controller;

import com.example.travel_plan_crud.entity.TravelPlan;
import com.example.travel_plan_crud.service.TravelPlanService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TravelPlanApiController {

    private final TravelPlanService travelPlanService;

    public TravelPlanApiController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    // API 전체 목록 조회 GET /api/travel-plans
    @GetMapping("/api/travel-plans")
    public Iterable<TravelPlan> apiIndex() {
        return travelPlanService.findAll();
    }

    // API 단건 상세 조회 GET /api/travel-plans/{id}
    @GetMapping("/api/travel-plans/{id}")
    public TravelPlan apiShow(@PathVariable Long id) {
        return travelPlanService.findById(id);
    }
}