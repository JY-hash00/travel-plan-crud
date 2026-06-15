package com.example.travel_plan_crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TravelPlanItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_plan_id")
    @JsonIgnore
    private TravelPlan travelPlan;

    private LocalDate visitDate; //방문 날짜
    private LocalTime visitTime; //방문 시간
    private String place; //장소
    private String memo; //메모

    public TravelPlanItem() {
    }
}
