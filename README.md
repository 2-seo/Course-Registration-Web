# 명지대학교 수강신청

## 개요

- 명지대학교 2021년 1학기 절차적사고와 프로그래밍 기말 프로젝트로 제출한 프로젝트입니다.

## 개발환경

|분류|항목|
|:---:|---|
| Language | `Java` |
| Framework | `SpringBoot`|
| DB | `MySql` |
| Library | `Lombok`, `Spring Data Jpa`, `Spring Security`, `Validation`, `Security Taglibs`, `jasper`, `jstl`, `Java Mail Sender` |
|Build | `Maven` |

## 주요 기능

### 회원가입

- 이메일 인증을 통해 회원가입을 할 수 있습니다.
- 가입 신청한 메일에서 인증 확인 버튼을 클릭하면 회원가입이 완료됩니다.

### 보안관련

- `Spring Security`를 이용하여 인증과 인가을 구현했습니다.
- 패스워드는 `BCrypt`로 암호화하여 저장했습니다.

### 장바구니

- 유저는 선택한 강의를 장바구니에 담을 수 있습니다.
- 중복된 강의는 에러 메시지가 나타나며, 장바구니에 담을 수 없습니다.

### 수강신청

- 유저는 선택한 강의를 수강신청할 수 있습니다.
- 중복된 강의 또는 수강 시간이 겹칠 경우 에러 메시지가 나타나며, 수강신청할 수 없습니다.

### 강의검색

- 전체, 강좌명 그리고 교수명으로 검색이 가능합니다.

## 회고

- 패키지 구성은 도메인형으로 제작에 임했다.
    - 계층형으로 개발했을 때는 관련 없는 여러 다른 클래스들도 같은 패키지에 있었기 때문에 불편함을 겪었었다.
    - 계층형으로 개발했을 때와 다르게 관련 코드들이 응집해있어 개발하기 훨씬 편했다.
- 스프링 부트에서는 `JSP`를 지원하지 않는 다는 것을 알게되었다.
    - 그럼에도 `JSP`를 사용한 이유는 단지 유명해서 사용해보고 싶었기 때문이다.(타임리프와 머스타치는 사용해보기도 했고..)
    - 스프링 부트에서 지원을 해주지 않아서 그런지 변수나 html 매핑(힌트)을 지원해주지 않아 불편함이 있었다.(내가 방법을 못 찾은 걸 수도..)
- `서버 사이드 랜더링`과 `클라이언트 사이드 랜더링`을 결합해서 구현해보았다.
    - 강의 담기와 삭제 알림 같은 경우는 순수 자바스크립트를 이용해 동적으로 나타날 수 있도록 했다.
        - 순수 자바스크립트를 이용해서 요소를 동적으로 표현하다보니 리액트의 소중함을 더욱 느낄 수 있었다.
    - 서버 사이드로 구현한 곳은 강의 검색이다.
        - 굳이 서버 사이드로 구현해본 이유는 `JSP`를 활용한 부분은 Layout(Header, Footer)이 전부였기 때문에 좀 더 `JSP`를 활용해보고 싶어서였다.
    - 두 랜더링 방식을 결합해서 사용한 이유는 두 방식 모두 공부해보고 싶었기 때문이다.
        - 서버 사이드로 구현한다면 개발이 좀 더 편하다고 느꼈다.
        - 하지만 불필요한 재랜더링이 이루어지기 때문에 효율면에서는 떨어졌다.
        - 스프링의 `@RestController`와 `@Controller`를 공부할 수 있는 좋은 경험이었다.
- 더 많은 기능을 추가할 수 있었지만 프론트 앤드 개발 속도가 따라주지 못했다.
    - 리액트를 활용하면 좀 더 개발 속도를 높일 수 있을 거 같다.
    - 역시 이쁘게 꾸미는 게 가장 어려운 거 같다. (`CSS`는 절대 무시할 수 없는 존재다..)

## 데모

### 메인

![메인페이지](https://user-images.githubusercontent.com/67419004/128127453-5ad60c5d-6bdd-414c-9363-2b09a4e3da0e.png)

### 로그인

![로그인](https://user-images.githubusercontent.com/67419004/128129592-9a46737c-6e7a-46d9-97f5-2c2956501c12.png)

- 로그인 후 메뉴
  ![로그인 후 메뉴](https://user-images.githubusercontent.com/67419004/128127501-249b0f0b-d653-4c91-87ad-f80857b33c93.png)

### 회원가입 인증 메일

![메일](https://user-images.githubusercontent.com/67419004/128127581-af71be79-7899-46dc-b2d7-3b9b3dfef81d.png)

### 회원가입 성공

![회원가입성공](https://user-images.githubusercontent.com/67419004/128127797-f8f02bf4-2fb1-4fce-be93-82972f578c27.png)

### 강의 검색

![강의검색](https://user-images.githubusercontent.com/67419004/128127831-bd8c3a5d-3e1c-4290-a1d0-56d61304da88.png)

### 강의 검색에서 알림 메시지

<p align="center">

![수강신청알림](https://user-images.githubusercontent.com/67419004/128127907-c061e163-49e3-40ed-a0c2-b708dd6be976.png)
![장바구니알림](https://user-images.githubusercontent.com/67419004/128127911-4af81401-9691-4ddb-84e1-3687cdf03096.png)
![수강신청 겹칠 때](https://user-images.githubusercontent.com/67419004/128127899-ae07a4da-32ed-465a-8402-3f0b07caecc3.png)

</p>

### 장바구니

![장바구니](https://user-images.githubusercontent.com/67419004/128128289-297473e4-ef48-4908-8c16-a2f5b1250a52.png)

### 장바구니에서 수강신청

![장바구니에서수강신청안내](https://user-images.githubusercontent.com/67419004/128129322-c4d6a5e1-78d5-4992-a9ea-f26d7c3270b0.png)

- 이미 수강신청한 강의인 경우
  ![장바구니에서이미수강신청강의](https://user-images.githubusercontent.com/67419004/128128669-13496bfc-cc0c-4fef-a02b-6328d1829af4.png)

### 장바구니에서 수강신청 취소

![수강신청취소알림](https://user-images.githubusercontent.com/67419004/128129452-16c9532b-ccb2-46fc-8a0d-aadef0ef6260.png)
![수강신청취소완료](https://user-images.githubusercontent.com/67419004/128128459-58a4eeb5-6d81-4b34-8907-c5fdec919b5d.png)
