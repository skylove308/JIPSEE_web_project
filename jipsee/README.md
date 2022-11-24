# ZIPSEE

```
ZIPSEE는 맞춤형 부동산 플랫폼으로 사용자에게 매물 정보 제공 및 추천 서비스를 제공합니다.
```

<br>

# 프로젝트 개요

```
ZIPSEE는 집을 구하는 사람들을 위한 부동산 플랫폼입니다.

집(ZIP) + 보다(SEE)
압축파일 확장자(ZIP) + 보다(SEE)

의 복합적인 뜻을 가진 이름으로 부동산 매물을 사용자에게 맞추어
압축하여 제공합니다.

사용자가 등록한 관심 사항에 따라 추천 매물이 변경되어 사용자가 필요한 매물을 쉽게 찾을 수 있게 하였습니다.
```

<br>

# 프로젝트 설치 및 실행

```
npm install

npm run serve
```

# 역할 분담

# 개발 일정

# 개발 환경

<img src="https://img.shields.io/badge/Spring boot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/Vue.js-4FC08D?style=for-the-badge&logo=Vue.js&logoColor=white">
<img src="https://img.shields.io/badge/TailwindCSS-06B6D4?style=for-the-badge&logo=TailwindCSS&logoColor=white">

<br>

# 개발 화면

## 메인 화면

- 메인 화면

![메인페이지](https://user-images.githubusercontent.com/28649890/203671990-580b042b-5f82-4b7f-ae14-e1167bbacb6d.PNG)

<br>

## 로그인

- 로그인 화면

![로그인](https://user-images.githubusercontent.com/28649890/203672945-b3b42557-1d76-42fe-a648-dfbbfbce1d57.PNG)

<br>

## 회원가입

- 관심사, 관심지역 다중 선택 가능
- 관심지역의 경우 추가하기 버튼을 눌러 동을 선택할 수 있으며 선택시 자동으로 관심지역 라벨에 추가되며 다시 지역이름 뱃지를 누를 경우 관심지역에서 제거된다.
- 필수 입력 정보가 입력되지 않았을 경우 회원가입 실패 안내 창이 출력되고 입력되지 않은 빈칸이 붉게 바뀐다.

![회원가입](https://user-images.githubusercontent.com/28649890/203672933-13a0a20c-682d-4cff-a571-61159b528fb2.PNG)

<br>

## 마이페이지 - 회원 정보 수정 및 탈퇴

- 회원 정보를 수정할 수 있고 탈퇴 또한 가능하다.

![마이페이지 - 정보수정](https://user-images.githubusercontent.com/28649890/203672947-040f12d4-ba1e-40d3-976c-0b4c73e9fdc3.PNG)

<br>

## 공지사항

- 공지사항 제목을 누르면 바로 밑에 게시글의 내용이 보인다.

![공지사항-공지사항](https://user-images.githubusercontent.com/28649890/203672940-8455ba6a-26b0-4577-95cc-3f0e39b4efa6.PNG)

<br>

## 공지사항 - 관리자 화면

- 관리자 계정으로 접속한 경우에만 글쓰기 버튼이 활성화되어 공지사항을 작성할 수 있다.

![공지사항-admin공지사항](https://user-images.githubusercontent.com/28649890/203672938-2b575fe0-ff02-4391-91b9-f8aea5a76ec4.PNG)

<br>

## 자주 묻는 질문

- 자주 묻는 질문을 모아놓은 탭이다.

![공지사항-자주묻는질문](https://user-images.githubusercontent.com/28649890/203672944-b6ae0618-f2f7-4f2b-b5df-b0ba41aebdb6.PNG)

<br>

## 1:1 문의

- 사용자가 1:1문의글을 작성하면 관리자 계정에서 확인할 수 있다.

![공지사항-문의하기](https://user-images.githubusercontent.com/28649890/203672942-cd0b5fdb-511a-4148-b8c9-37aa925ef563.PNG)

<br>

## 마이페이지 - 1:1 문의 답변 확인

- 사용자는 마이페이지에서 관리자가 답변한 1:1문의 답변 게시글을 확인할 수 있다.

![마이페이지-문의답변](https://user-images.githubusercontent.com/28649890/203672951-e1f24b33-f677-4353-954b-3fe83b781cde.PNG)

<br>

## 마이페이지 - 1:1 문의 답변 확인 관리자 화면

- 사용자가 1:1문의글을 작성하면 관리자 계정의 마이페이지의 1:1문의 답변 탭에 게시글로 쌓인다. 답변했을 시 '답변됨'으로 표시되며 미답변시 게시글을 클릭하면 밑에 답변할 수 있는 텍스트박스가 나온다.
- 관리자가 답변 시 사용자의 마이페이지의 1:1문의 게시글에 답변된 내용이 달린다.

![마이페이지-admin답변](https://user-images.githubusercontent.com/28649890/203672948-404236b4-4460-4760-b60c-2aebc59251ae.PNG)

<br>

## 매물탐색

- Kakao map API를 사용하여 선택한 매물을 지도에 표시한다.
- 매물은 지도 오른쪽에 표시되며 하트 버튼 클릭시 마이페이지의 관심매물로 등록된다. 하트 버튼을 재 클릭하면 관심매물에서 해제된다.
- 보러가기 버튼 클릭시 매물 상세페이지로 이동한다.

![매물탐색-메인](https://user-images.githubusercontent.com/28649890/203672953-7e4ce4f0-eb53-44a4-a513-4d323567349e.PNG)

<br>

## 마이페이지 - 관심매물

- 사용자가 찜한 관심매물을 한눈에 볼 수 있다.
- 매물 클릭시 상세페이지로 이동한다.
  ![마이페이지-관심매물](https://user-images.githubusercontent.com/28649890/203672949-a62eb7f6-a511-4044-9280-397fa038093e.PNG)

<br>

## 매물탐색 - 필터

- 필터기능을 이용하여 사용자가 원하는 기준의 매물을 골라 검색 할 수 있다.

![매물탐색-필터](https://user-images.githubusercontent.com/28649890/203672922-5ed27a7b-e5a1-4fc3-bbd6-97b4a32cc476.PNG)

<br>

## 매물탐색 - 카테고리

- 카테고리별로 주변시설이 매물 주변에 어떻게 위치해 있는지 지도에서 확인할 수 있다.

![매물탐색-카테고리](https://user-images.githubusercontent.com/28649890/203672914-9e403b26-268a-402d-b1b0-2f025727bf8d.PNG)

<br>

## 상세페이지

- 매물의 상세정보를 볼 수 있다.

![상세페이지-메인](https://user-images.githubusercontent.com/28649890/203672931-3e253182-c7a9-4da0-80fb-1251207ce008.PNG)

<br>

## 상세페이지 - VR

- 상세페이지의 360VR 버튼 클릭시 매물의 내부 방 구조를 360도 VR버전으로 확인 할 수 있다.

![상세페이지-360VR](https://user-images.githubusercontent.com/28649890/203672928-ede5de7e-8325-4721-a40d-2e81a81b4005.PNG)

<br>

## 메인페이지 - 추천 매물

- 사용자가 가입시 입력한 관심정보를 토대로 매물들을 추천하여 슬라이드 형식으로 메인페이지에서 보여준다.
- 매물 클릭시 상세페이지로 이동한다.

![메인 추천](https://user-images.githubusercontent.com/28649890/203672925-3d4cb8fc-58ea-4712-956c-5d68b997b77d.PNG)

<br>

## 검색

- 지역이나 장소, 상호명 등 원하는 장소를 입력하면 주변 매물을 보여주는 검색 기능이다.

![검색](https://user-images.githubusercontent.com/28649890/203672937-027ffdfe-43f7-4109-b286-0452faa26a0f.PNG)

<br>

## 검색 후 매물탐색 화면

- 검색 후 주변 지역 매물 리스트가 출력된다.

![검색 후 화면](https://user-images.githubusercontent.com/28649890/203672934-e678dd55-452d-4560-bfc3-38edee773257.PNG)

<br>
