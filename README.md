# 객체지향의 사실과 오해를 읽고 구현해보는 커피숍
1. POJO 로 먼저 구현해보기
2. REST API 방식으로 구현해보기

## 구조

<div align="left">
   <img src="resource/processAndDomain.png">
</div>

## 도메인 기능
1. 커피 주문
   - 비회원으로 주문
   - 회원으로 주문
2. 구매 내역 조회
3. 판매 내역 조회


#### POJO 구현에 대한 포스팅

[https://ssangsu.tistory.com/304](https://ssangsu.tistory.com/304) 


#### 향후 개선 포인트
1. 돈을 나타내는 변수(ex : price, totalPrice, paymentPrice 등)을 값 객체로 바꾸어서 '돈'과 관련된 책임을 해당 객체에게 부여하기