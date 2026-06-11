package com.example.travel_plan_crud.service;

import com.example.travel_plan_crud.entity.TravelPlan;
import com.example.travel_plan_crud.repository.TravelPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelPlanService {

    private final TravelPlanRepository travelPlanRepository;

    public TravelPlanService(TravelPlanRepository travelPlanRepository) {
        this.travelPlanRepository = travelPlanRepository;
    }

    // 전체 목록 조회
    public Iterable<TravelPlan> findAll() {
        return travelPlanRepository.findAll();
    }

    // 단건 조회
    public TravelPlan findById(Long id) {
        return travelPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 여행 일정이 없습니다. id=" + id));
    }

    // 등록
    public TravelPlan save(TravelPlan travelPlan) {
        return travelPlanRepository.save(travelPlan);
    }

    // 수정
    public TravelPlan update(Long id, TravelPlan updated) {
        TravelPlan target = findById(id);
        target.setTitle(updated.getTitle());
        target.setDestination(updated.getDestination());
        target.setStartDate(updated.getStartDate());
        target.setEndDate(updated.getEndDate());
        target.setMemo(updated.getMemo());
        return travelPlanRepository.save(target);
    }

    // 삭제
    public void delete(Long id) {
        TravelPlan target = findById(id);
        travelPlanRepository.delete(target);
    }
}