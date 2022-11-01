# site

CRUD, login/logout, signup, file upload/download     [ + MyPage (~ing) ]

## 개발 환경
* IDE : `Intellij IDEA Ultimate 2022.2.1`
* Language : `Java 17.0.3`
* BuildTool : `Gradle 7.5`
* Framework : `Spring Boot 2.7.4`
* Git GUI : `GitKraken 8.10.1`

## 기술 스택

Spring Boot

`Spring Web` `Spring Data JPA`

`Thymeleaf` `Spring Security` `H2 Database`

`Lombok` `Spring Boot DevTools`

Etc 

`HTML` `CSS` `Bootstrap 5.1.3`

## 실행 GIF

<details>
<summary>▶ [ 메인 페이지 ] 접기/펼치기 ◀</summary><br>

![mainpage](https://user-images.githubusercontent.com/100026743/199162940-a6e505b3-b9b0-4149-9bee-77e3639d22df.gif)

**CSS, HTML 연습 겸 요란한 효과들을 넣었다.**
</details>

<br>
<details>
<summary>▶ [ 로그인,회원가입 ] 접기/펼치기 ◀</summary><br>

![signup_page](https://user-images.githubusercontent.com/100026743/199194174-b835807b-bd44-4a79-b066-3568d5f0841e.gif)

**회원가입 시 비밀번호와 비밀번호확인이 틀리면 아래 경고메세지가 뜬다. 아이디 중복또한 경고 메세지를 생성한다.**<br>
**가입 이후 로그인 페이지로 이동한다.**<br><br>

![login_page](https://user-images.githubusercontent.com/100026743/199194558-a6b22c7b-aa5b-4f98-beeb-14646e67157d.gif)

**로그인 시 아이디 또는 비밀번호가 틀리면 팝업창이 뜬다. 성공하면 게시판 페이지로 이동한다.**
</details>

<br>
<details>
<summary>▶ [ 게시판,마크다운,페이징,검색 ] 접기/펼치기 ◀</summary><br>

![write_comment_rec](https://user-images.githubusercontent.com/100026743/199195062-42df19c4-4b7e-4b64-80d6-b62fec132b42.gif)

**로그인 이후 게시글 작성이 가능하다. 게시글에 댓글,추천수가 증가하면 List에 표시된다.**<br>
**글 작성자와 현재 로그인한 사용자가 같을때만 삭제,수정 버튼이 보이며 사용이 가능하다.**<br><br>

![markdown_test](https://user-images.githubusercontent.com/100026743/199195613-b8727abd-bfd7-431e-8b78-ae6624b05803.gif)

**Content에 마크다운을 적용하여 글 작성시에 사용이 가능하다.**<br><br>

![paging_search](https://user-images.githubusercontent.com/100026743/199195842-e7077163-c590-422f-913d-478ea67331ff.gif)

**페이징과 검색 기능으로 제목,내용,댓글 중 하나라도 검색키워드와 일치하면 검색이 가능하다.**
</details>

<br>
<details>
<summary>▶ [ 사진업로드 및 다운로드 ] 접기/펼치기 ◀</summary><br>

![picture_upload,download](https://user-images.githubusercontent.com/100026743/199197745-91f2d6e6-f2d3-46e6-bca0-805ad64fc16d.gif)

**외부 경로설정으로 글을 작성하며 사진을 업로드하면 바로 보이게된다.**<br><br>
**업로드시 파일은 이름 중복을 방지하기 위해 32자리수의 랜덤한 이름으로 저장하지만, <br> 다운로드시엔 업로드했던 파일의 원래이름으로 다운로드한다.**<br><br>

![otherFile_download](https://user-images.githubusercontent.com/100026743/199198348-6009ca33-7539-483a-8a0a-4c611596491c.gif)

**첨부파일이 존재한다면 다운로드 버튼이 보이게되고, 아니라면 아무것도 뜨지 않는다.**<br>
**사진목록에서 확장자가 이미지관련 파일이라면 사진이 보이지만, 아니라면 "이미지 없음"의 내용이 보이게된다.**<br>
**파일의 업로드 제한은 10MB로 설정하였다.**


</details>

## 문제점

* 사진목록이 게시판 형식으로 썸네일화 하지 않은 이미지를 가져온다.

* 사진게시판 작성시 아무것도 첨부하지 않아도 빈 파일이 생성된다.

* 파일 첨부를 하나밖에 하지 못하며, 이미지 파일이 아니여도 첨부가 가능하다.

* 파일다운로드 경로와 이름을 알면 모두 다운로드가 가능하다. ※취약점※
