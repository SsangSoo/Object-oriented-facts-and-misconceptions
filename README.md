# 객체지향의 사실과 오해를 읽고 구현해보는 커피숍
1. POJO 로 먼저 구현해보기
2. REST API 방식으로 구현해보기

## 협력

#### 손님
1. 손님은 앱(=키오스크)을 통해 커피를 주문한다.
    - 회원으로 주문여부를 결정할 수 있다.
      - 없는 회원이라면, 회원으로 저장이 된 후 결제 프로세스가 진행된다.
    - 어떤 커피를, 몇 잔, 누구에게 주문할지 앱(=키오스크)에 넘겨준다.
    - 쿠폰이 있으면 한 잔을 무료로 제공받을 수 있다.
      - 단 쿠폰이 없는데, 사용하려고 하면 예외가 발생한다.
2. 손님은 앱(=키오스크)을 통해 쿠폰이 몇 개인지 확인할 수 있다.
    - 단, 회원으로 등록 되어있어야 한다.
3. 손님은 앱(=키오스크)을 통해 구매 내역을 일 / 월 / 년 별로 확인할 수 있다. 
    - 단, 회원으로 등록 되어있어야 한다. 
    - 언제, 어떤 커피, 수량, 주문 별 금액, 총계
        - 일 : 시간, 커피/수량, 주문 총계(가격)
        - 월 : 몇월 몇일, 커피/수량, 일별 주문 합계(가격), 월 총계(가격)
        - 년 : 몇월 커피/수량, 월별 주문합계(가격) / 년 총계(가격)

#### 바리스타
1. 커피를 제조할 수 있다.
   - 주문 정보에 따라 커피를 제조한다.
2. 바리스타는 앱(=키오스크)을 통해 판매 내역을 일 / 월 / 년 별로 확인할 수 있다.
    - 언제 어떤 커피, 수량, 총 매출
        - 일 : 시간, 커피/ 수량, 매출 총계(가격)
        - 월 : 일별, 커피/수량 일별 매출 합계(가격), 월 총계(가격)
        - 년 : 월별 커피/수량, 월별 매출 합계(가격), 년 총계(가격)

#### 키오스크
1. 앱(=키오스크)은 손님에게 커피 주문을 받을 수 있다.
   - 회원으로 주문여부를 받을 수 있다.
     - 회원으로 주문했는데 회원이 아니라면, 회원으로 저장이 되게 한다.
     - 회원으로 주문할시 쿠폰과 스탬프 계산을 알아서 한다.
   - 어떤 커피를, 몇 잔, 누구에게 주문할지에 대한 정보를 받을 수 있다.
   - 쿠폰사용 여부를 받을 수 있다.
     - 단 쿠폰이 없는데, 사용하려고 하면 예외가 발생시킨다.
     - 10개에 하나의 음료가 무료
2. 고객이 주문내역을 보여달라고 하면 보여줄 책임이 있다.
   - 언제 어떤 커피, 수량, 총 매출
3. 바리스타가 판매내역을 보여달라고 하면 보여줄 책임이 있다.
   - 언제 어떤 커피, 수량, 총 매출

#### 쿠폰 
1. 쿠폰은 스탬프 개수를 받으면 스탬프 개수를 계산해서 쿠폰, 스탬프 계산한 결과를 얻을 수 있다.


