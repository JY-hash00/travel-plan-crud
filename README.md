# 여행 일정 기록 프로그램

사용자가 여행 일정을 등록하고 목록 조회 / 상세 조회 / 수정 / 삭제할 수 있는 Spring Boot CRUD 웹 프로그램입니다.

---

## 주요 기능

| 기능 | 설명 |
|------|------|
| Create | 새 여행 일정 등록 폼 작성 및 DB 저장 |
| Read | 여행 일정 전체 목록 조회 및 단일 상세 조회 |
| Update | 기존 여행 일정 수정 폼 작성 및 DB 갱신 |
| Delete | 여행 일정 삭제 후 목록 페이지로 리다이렉트 |

- Bootstrap 기반 공통 헤더/푸터, 테이블, 버튼, 입력 폼 디자인 적용

---

## 기술 스택

- **Backend**: Java, Spring Boot
- **ORM**: Spring Data JPA
- **DB**: H2 (인메모리)
- **Template Engine**: Mustache
- **Frontend**: Bootstrap
- **Build**: Maven

---

## 데이터 모델

| 필드명 | 타입 | 설명 |
|--------|------|------|
| id | Long | 여행 일정 고유 번호 (자동 증가) |
| title | String | 여행 일정 제목 |
| destination | String | 여행지 또는 도시명 |
| startDate | LocalDate | 여행 시작일 |
| endDate | LocalDate | 여행 종료일 |
| memo | String | 여행 메모, 계획, 준비물 등 상세 내용 |

---

## 프로젝트 구조

```
src/main/java/
├── controller/
│   └── TravelPlanController.java
├── entity/
│   └── TravelPlan.java
└── repository/
    └── TravelPlanRepository.java

src/main/resources/
├── templates/
│   ├── travelplans/
│   │   ├── index.mustache    # 목록 화면
│   │   ├── show.mustache     # 상세 화면
│   │   ├── new.mustache      # 등록 화면
│   │   └── edit.mustache     # 수정 화면
│   └── layouts/
│       ├── header.mustache   # 공통 헤더
│       └── footer.mustache   # 공통 푸터
└── application.properties
```

---

## API 엔드포인트

| 기능 | 방식 | URL |
|------|------|-----|
| 목록 조회 | GET | `/travel-plans` |
| 상세 조회 | GET | `/travel-plans/{id}` |
| 등록 폼 | GET | `/travel-plans/new` |
| 등록 처리 | POST | `/travel-plans/create` |
| 수정 폼 | GET | `/travel-plans/{id}/edit` |
| 수정 처리 | POST | `/travel-plans/update` |
| 삭제 처리 | GET | `/travel-plans/{id}/delete` |

---

## MVC 흐름

```
사용자 요청
    ↓
Controller  (요청 수신, 비즈니스 로직 호출)
    ↓
Repository  (DB 저장 / 조회 / 수정 / 삭제)
    ↓
DB
    ↓
View (Mustache 템플릿으로 결과 출력)
```

---

## 팀원 역할분담

| 팀원 | 담당 | 맡은 파일 |
|------|------|-----------|
| 1번 | 프로젝트 총괄 + Entity/Repository | `TravelPlan.java`, `TravelPlanRepository.java`, `application.properties` |
| 2번 | Controller 담당 | `TravelPlanController.java` |
| 3번 | View 화면 담당 | `index.mustache`, `show.mustache`, `new.mustache`, `edit.mustache` |
| 4번 | Layout + 보고서/MVC 담당 | `header.mustache`, `footer.mustache`, 보고서 PDF |

---

## 실행 방법

1. 저장소를 클론합니다.
   ```bash
   git clone https://github.com/JY-hash00/travel-plan-crud.git
   ```
2. IntelliJ IDEA에서 프로젝트를 열고 `mvn spring-boot:run` 또는 메인 클래스를 실행합니다.
3. 브라우저에서 `http://localhost:8080/travel-plans` 에 접속합니다.

---

## 작업 순서

1. 1번 팀원이 Spring Boot 프로젝트를 생성하고 GitHub에 업로드
2. 2~4번 팀원이 clone 후 각자 담당 파일만 작성
3. 각자 `commit` / `push`로 GitHub에 업로드
4. 1번 팀원이 최종 `pull` 후 병합 및 실행 테스트
5. CRUD 전체 기능 확인 후 오류 내용을 보고서에 정리

---

## 주요 오류 및 해결 사례

- **404 오류**: View 링크 주소와 Controller `@GetMapping` 경로가 달라 발생 → 두 경로를 동일하게 수정하여 해결
- **수정 데이터 미반영**: form의 `name` 값과 Entity 필드명이 달라 바인딩 실패 → `name`을 `title`, `destination`, `startDate` 등 필드명과 일치시켜 해결
- **DB 저장 오류**: Entity에 기본 생성자 또는 어노테이션 누락 → `@Entity`, `@Id`, `@GeneratedValue` 및 기본 생성자 추가로 해결
