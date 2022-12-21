# 멋쟁이 사자처럼 Final Project `MutsaSNS`

## 구현 기능
### 1. 회원 가입(Join)
- endpoint: `'api/v1/users/join`<br>

- JSON 형식으로 데이터를 받아 DB에 저장 후 가입된 회원의 정보 반환

<br><br>
입력 데이터
```JSON
{
  "userName": "John Snow",
  "password": "winter"
}
```
반환 데이터
```JSON
{
  "resultCode": "SUCCESS",
  "result": {
    "userId": 22,
    "userName": "John Snow"
  }
}
```
<br>

### 2. 로그인(Login)
- endpoint: `'api/v1/users/login`<br>
- JSON 형식으로 데이터를 받아 DB에 저장 후 가입된 회원의 정보 반환

<br><br>
입력 데이터
```JSON
{
  "userName": "John Snow",
  "password": "winter"
}
```
반환 데이터
```JSON
{
  "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImNodXUiLCJpYXQiOjE2NzE2MzMzNTcsImV4cCI6MTY3MTYzNjk1N30.rIjdu7_ST6rS53ABprqTDEyqnC2unoVhmoS6mgJ0AJg"
}
```
### 3. 게시글 작성