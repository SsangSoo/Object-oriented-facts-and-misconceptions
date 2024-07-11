객.사.오를 읽고 구현해보는 커피숍

1. POJO 로 먼저 구현해보기
2. REST API 방식으로 구현해보기


## 각 객체에 할당한 책임 

#### 손님
1. 메뉴를 확인할 수 있다.
2. 커피를 주문할 수 있다.
3 주문 내역을 확인할 수 있다.

#### 메뉴
1. 메뉴판을 보여줄 수 있다.
    - 메뉴 이름 / 가격

#### 주문
1. 주문 정보를 담는다. 
   - 메뉴, 개수, 총 가격

#### 커피
1. 바리스타에 의해 제조된다.

#### 바리스타
1. 주문받은 커피를 제조하여 제공한다.
    - 커피를 제조하여 제공하면서,
    - 판매 내역에 저장한다.

#### 주문 내역
1. 고객은 자신이 주문한 내역을 날짜별로 확인할 수 있다.
   - 날짜/주문 내역(메뉴, 메뉴 개수, 총가격)

#### 판매 내역
1. 바리스타별 어떤 고객에게 판매 내역을 날짜별로 제공할 수 있다.<br>
-> 바리스타가 어떤 고객에게 어떤것을 판매했는지 과연 알아야할까? <br>
-> 날짜 별로 어떤 메뉴를 얼마나 판매했는지, 총 매출은 얼마인지 알 수 있다.
    - 매출 확인시 일별, 월별, 년별로 확인할 수 있다.

