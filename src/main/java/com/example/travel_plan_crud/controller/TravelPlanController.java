package com.example.travel_plan_crud.controller;

import com.example.travel_plan_crud.entity.TravelPlan;
import com.example.travel_plan_crud.entity.TravelPlanItem;
import com.example.travel_plan_crud.service.TravelPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;

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

    // 등 록 폼 GET /travel-plans/new
    @GetMapping("/travel-plans/new")
    public String newForm(Model model) {
        model.addAttribute("travelPlan", new TravelPlan());
        return "travelplans/new";
    }

    // 등록 처리 POST /travel-plans/create
    @PostMapping("/travel-plans/create")
    public String create(TravelPlan travelPlan,
                         @RequestParam(required = false) List<String> dayDates,
                         @RequestParam(required = false) List<String> dayMemos) {
    //일차별 메모 띄우기
        TravelPlan saved = travelPlanService.save(travelPlan);

        if (dayDates != null && dayMemos != null) {
            for (int i = 0; i < dayDates.size() && i < dayMemos.size(); i++) {
                String date = dayDates.get(i);
                String memo = dayMemos.get(i);

                if (date != null && !date.isBlank() && memo != null && !memo.isBlank()) {
                    TravelPlanItem item = new TravelPlanItem();
                    item.setVisitDate(LocalDate.parse(date));
                    item.setPlace((i + 1) + "일차");
                    item.setMemo(memo);
                    travelPlanService.addItem(saved.getId(), item);
                }
            }
        }

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

    // 세부 일정 등록 POST /travel-plans/{id}/items/create
    @PostMapping("/travel-plans/{id}/items/create")
    public String addItem(@PathVariable Long id,
                          @RequestParam String visitDate,
                          @RequestParam(required = false) String visitTime,
                          @RequestParam String place,
                          @RequestParam(required = false) String memo) {

        TravelPlanItem item = new TravelPlanItem();
        item.setVisitDate(LocalDate.parse(visitDate));

        if (visitTime != null && !visitTime.isBlank()) {
            item.setVisitTime(LocalTime.parse(visitTime));
        }

        item.setPlace(place);
        item.setMemo(memo);

        travelPlanService.addItem(id, item);
        return "redirect:/travel-plans/" + id;
    }

    // 세부 일정 삭제 GET /travel-plans/{planId}/items/{itemId}/delete
    @GetMapping("/travel-plans/{planId}/items/{itemId}/delete")
    public String deleteItem(@PathVariable Long planId, @PathVariable Long itemId) {
        travelPlanService.deleteItem(itemId);
        return "redirect:/travel-plans/" + planId;
    }
}