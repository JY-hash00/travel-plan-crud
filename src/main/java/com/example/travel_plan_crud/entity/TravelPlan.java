package com.example.travel_plan_crud.entity;

//어노테이션 import
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity //엔티티 선언
@Getter
@Setter
public class TravelPlan{
    @Id //엔티티 대푯값 지정
    @GeneratedValue //대푯값 자동 생성 기능(숫자 자동 생성)

    //필드 선언
    private Long id; //자동 증가(pk)
    private String title; //제목
    private String destination; //여행지
    private LocalDate startDate; //시작일
    private LocalDate endDate; //종료일
    private String memo;

    //빈 생성자 추가
    public TravelPlan(){
        
    }
}