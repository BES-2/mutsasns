# 멋쟁이 사자처럼 Final Project `MutsaSNS`
## 구현 기능
- [x] [**회원 가입** (POST)](#1.-회원-가입(join)) 
- [x] [**로그인** (POST)](#2.-로그인(login))
- [x] [**게시글 작성** (POST)](#3.-게시글-작성)
- [x] [**특정 게시글 조회** (GET)](#4.-특정-게시글-조회)
- [x] [**전체 게시글 조회** (GET)](#5.-전체-게시글-조회)
- [x] [**특정 게시글 삭제** (DELETE)](#6.-특정-게시글-삭제)
- [x] [**게시글 수정** (PUT)](#7.-게시글-수정)
- [x] [**마이 피드** (GET)](#8.-마이-피드)
- [x] [**좋아요 추가** (POST)](#9.-좋아요-추가)
- [x] [**좋아요 확인** (GET)](#10.-좋아요-확인)
- [x] [**댓글 추가** (POST)](#11.-댓글-추가)
- [x] [**댓글 확인** (GET)](#12.-댓글-확인)
- [x] [**알림** (POST, GET)](#13.-알림)

## 구현 기능 상세 설명
### 1. 회원 가입(Join)
- endpoint: `/api/v1/users/join`
- JSON 형식으로 데이터를 받아 DB에 저장 후 가입된 회원의 정보 반환


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
- endpoint: `'/api/v1/users/login`
- JSON 형식으로 데이터를 받아 DB에 저장 후 가입된 회원의 정보 반환

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
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6Iuq3pCIsImlhdCI6MTY3MjExODMxMywiZXhwIjoxNjcyMTIxOTEzfQ.KEwDWuppPoiRNHd_w71WTw9wD-yewFGfz_lVMYwDoOE"
  }
}
```
<br>

### 3. 게시글 작성
- endpoint: `/api/v1/posts`
- JSON 형식으로 데이터를 받아 DB에 저장 후 게시글 작성 성공 여부 반환
- Header에 `login` 에서 받은 토큰을 입력
> Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6Iuq3pCIsImlhdCI6MTY3MjExODMxMywiZXhwIjoxNjcyMTIxOTEzfQ.KEwDWuppPoiRNHd_w71WTw9wD-yewFGfz_lVMYwDoOE
- 입력된 토큰으로 `userName` 확인

<br>
입력 데이터

```JSON
{
    "title":"Life is C",
    "body":"Life is C between B and D. Beat. Chill. Dance."
}
```

인가된 토큰 일치 시 반환 데이터

```JSON
{
    "resultCode": "SUCCESS",
    "result": {
        "message": "포스트 등록 완료",
        "postId": 27
    }
}
```
<br>

### 4. 특정 게시글 조회
- endpoint : `/api/v1/posts/{id}`
- `{id}`에 해당하는 게시글 정보를 GET으로 출력

<br>
반환 데이터

```JSON
{
    "resultCode": "SUCCESS",
    "result": {
        "id": 27,
        "title": "Life is C",
        "body": "Life is C between B and D. Beat. Chill Dance.",
        "userName": "귤",
        "createdAt": "2022-12-27 14:26:02",
        "lastModifiedAt": "2022-12-27 14:26:02"
    }
}
```
<br>

### 5. 전체 게시글 조회

- endpoint : `/api/v1/posts`
- 전체 게시글을 페이징처리 후 정보 출력
- 최신 순으로 출력
- 페이지당 20개씩 출력

반환 데이터
```JSON
{
    "resultCode": "SUCCESS",
    "result": {
        "content": [
            {
                "id": 27,
                "title": "Life is C",
                "body": "Life is C between B and D. Beat. Chill Dance.",
                "userName": "귤",
                "createdAt": "2022-12-27 14:26:02",
                "lastModifiedAt": "2022-12-27 14:26:02"
            },
            {
                "id": 36,
                "title": "hello-title",
                "body": "hello-body",
                "userName": "kyeongrok22",
                "createdAt": "2022-12-27 05:58:00",
                "lastModifiedAt": "2022-12-27 05:58:00"
            }
        ],
        "pageable": "INSTANCE",
        "last": true,
        "totalElements": 35,
        "totalPages": 2,
        "size": 20,
        "number": 0,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": true,
        "numberOfElements": 20,
        "empty": false
    }
}
```

### 6. 특정 게시글 삭제
- endpoint : `/api/v1/posts/{id}`
- `{id}`에 해당하는 게시글 삭제
- Header에 게시글을 작성한 `user`의 토큰을 입력 후 게시글 접근 권한 확인

반환 데이터
```JSON
{
    "resultCode": "SUCCESS",
    "result": {
        "message": "포스트 삭제 완료",
        "postId": 38
    }
}
```

### 7. 게시글 수정
- endpoint : `/api/v1/posts/{id}`
- `{id}`에 해당하는 게시글 내용 수정
- Header에 게시글을 작성한 `user`의 토큰을 입력 후 게시글 접근 권한 확인
- Body에 수정할 게시글의 `title`, `body` 입력

입력 데이터
```JSON
{
    "title":"자꾸 마법에 걸려",
    "body":"밤을 새워도 안졸려. 다른 생각 지워져"
}
```

반환 데이터
```JSON
{
    "resultCode": "SUCCESS",
    "result": {
        "message": "포스트 수정 완료",
        "postId": 37
    }
}
```

### 8. 마이 피드
- endpoint : `/api/v1/posts/my`
- token으로 받은 `userName`의 게시글 반환

```json
{
    "resultCode": "SUCCESS",
    "result": {
        "content": [
            {
                "id": 2,
                "title": "방어와 공격 무엇이 중요한가",
                "body": "신선도가 중요하다.",
                "userName": "cat",
                "createdAt": "2023-01-10 17:05:52",
                "lastModifiedAt": "2023-01-10 17:05:52"
            },
            {
                "id": 1,
                "title": "life is C between B and D",
                "body": "Beat Chill Dance",
                "userName": "cat",
                "createdAt": "2023-01-10 17:04:56",
                "lastModifiedAt": "2023-01-10 17:04:56"
            }
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "pageSize": 20,
            "pageNumber": 0,
            "paged": true,
            "unpaged": false
        },
        "first": null,
        "numberOfElements": 2,
        "empty": false
    }
}
```

### 9. 좋아요 추가
- endpoint : `/api/v1/posts/{postId}/likes`
- token을 통해 `User` 를 확인하고 해당 Post에 좋아요 추가
- 좋아요를 다시 누를경우, deletedAt 추가

```json
{
    "resultCode": "SUCCESS",
    "result": "좋아요를 눌렀습니다."
}
```

### 10. 좋아요 확인
- endpoint : `/api/v1/posts/{postId}/likes`
- `{postId}`에 해당하는 좋아요 수 반환
- `deletedAt`이 `null`인 경우만 갯수에 적용

```json
{
    "resultCode": "SUCCESS",
    "result": 2
}
```

### 11. 댓글 확인
- endpoint : `/api/v1/posts/{postId}/comments`
- `{postId}`에 해당하는 댓글 pageable로 반환

```json
{
  "resultCode": "SUCCESS",
  "result": {
    "content": [
      {
        "id": 1,
        "comment": "최선의 방어는 대방어. 뱃살 많이주세요.",
        "userName": "namsee",
        "postId": 1,
        "createdAt": "2023-01-10 17:06:38"
      }
    ],
    "pageable": "INSTANCE",
    "last": false,
    "totalElements": 1,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
  }
}
```

### 12. 댓글 작성
- endpoint : `/api/v1/posts/{postId}/comments`
- `{postId}`에 해당하는 게시글에 댓글 추가

input
```json
{
	"comment" : "최선의 공격은 방어회. 뱃살 많이주세요."
}
```

output
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "id": 3,
    "comment": "최선의 공격은 방어회. 뱃살 많이주세요.",
    "userName": "cat",
    "postId": 1,
    "createdAt": "2023-01-10 17:52:03"
  }
}
```

### 13. 알림
- endpoint : `/api/v1/alarms`
- token으로 받은 User의 게시글에 해당하는 좋아요, 댓글 확인


output
```json
{
  "resultCode": "SUCCESS",
  "result": [
    {
      "id": 3,
      "alarmType": "NEW_COMMENT_ON_POST",
      "fromUserId": 1,
      "targetId": 2,
      "text": "new comment!",
      "createdAt": "2023-01-10T17:07:15.598141"
    },
    {
      "id": 2,
      "alarmType": "NEW_LIKE_ON_POST",
      "fromUserId": 1,
      "targetId": 1,
      "text": "new like!",
      "createdAt": "2023-01-10T17:06:53.682583"
    },
    {
      "id": 1,
      "alarmType": "NEW_COMMENT_ON_POST",
      "fromUserId": 1,
      "targetId": 1,
      "text": "new comment!",
      "createdAt": "2023-01-10T17:06:38.127019"
    }
  ]
}
```

