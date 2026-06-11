package com.example.travel_plan_crud.controller;

import com.example.travel_plan_crud.entity.TravelPlan;
import com.example.travel_plan_crud.service.TravelPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TravelPlanController {

    private final TravelPlanService travelPlanService;

    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    // 목록 조회 GET /travel-plans
    @GetMapping("/travel-plans")
    public String index(Model model) {
        Iterable<TravelPlan> travelPlanList = travelPlanService.findAll();
        model.addAttribute("travelPlans", travelPlanList);
        return "travelplans/index";
    }

    // 상세 조회 GET /travel-plans/{id}
    @GetMapping("/travel-plans/{id}")
    public String show(@PathVariable Long id, Model model) {
        TravelPlan travelPlan = travelPlanService.findById(id);
        model.addAttribute("travelPlan", travelPlan);
        return "travelplans/show";
    }

    // 등록 폼 GET /travel-plans/new
    @GetMapping("/travel-plans/new")
    public String newForm(Model model) {
        model.addAttribute("travelPlan", new TravelPlan());
        return "travelplans/new";
    }

    // 등록 처리 POST /travel-plans/create
    @PostMapping("/travel-plans/create")
    public String create(TravelPlan travelPlan) {
        travelPlanService.save(travelPlan);
        return "redirect:/travel-plans";
    }

    // 수정 폼 GET /travel-plans/{id}/edit
    @GetMapping("/travel-plans/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        TravelPlan travelPlan = travelPlanService.findById(id);
        model.addAttribute("travelPlan", travelPlan);
        return "travelplans/edit";
    }

    // 수정 처리 POST /travel-plans/update
    @PostMapping("/travel-plans/update")
    public String update(@RequestParam Long id, TravelPlan updated) {
        travelPlanService.update(id, updated);
        return "redirect:/travel-plans";
    }

    // 삭제 처리 GET /travel-plans/{id}/delete
    @GetMapping("/travel-plans/{id}/delete")
    public String delete(@PathVariable Long id) {
        travelPlanService.delete(id);
        return "redirect:/travel-plans";
    }
}