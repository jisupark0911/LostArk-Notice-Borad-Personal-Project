# 로스트아크 종합 정보 & 게시판 프로젝트
로스트아크 게임의 캐릭터 정보, 공지사항 및 이벤트 조회 기능을 제공하는 웹 애플리케이션입니다. 
<br>
자유게시판에서 게시글 작성, 수정, 삭제 및 댓글 기능을 지원하며, AWS EC2와 S3를 활용한 서버 배포를 구현하였습니다.
<br>
주요 기술 스택으로는 Spring Boot, Spring Data JPA, Java, MySQL, AWS EC2, S3 및 GitHub Actions를 사용하였습니다.

## 목차
1. [개발환경](#개발환경)
2. [프로젝트 기능](#프로젝트-기능)
   - 회원가입
   - 로그인/로그아웃
   - 자유게시판(생성, 수정, 삭제, 좋아요)
   - 댓글(생성, 수정, 삭제)
   - 로스트아크 캐릭터 종합 정보 검색
   - 로스트아크 공지사항, 이벤트, 게임컨텐츠 조회
3. [사용된 기술 스택](#사용된-기술-스택)
4. [시스템 아키텍처](#시스템-아키텍처)
5. [ERD](#ERD)

## 개발환경
- IDE: IntelliJ IDEA 
- Spring Boot 3.2.2
- JDK 17
- mysql 8.0.36
- Lombok
- Spring Data JPA Spring Web
- Thymeleaf

## 프로젝트 기능
1. 회원가입
- 이메일, 비밀번호, 닉네임을 입력해 회원가입을 할 수 있다.
- 이메일은 중복이 불가하다.
2. 로그인/로그아웃
- 로그인을 하지 않고 게시글 작성 및 댓글 작성이 불가하다.
- 로그인을 하지 않고 게시글 작성 및 댓글 작성을 시도 할 경우 로그인 페이지로 이동한다.
- 로그아웃 버튼을 누르면 로그아웃이 된다.
3. 자유게시판(생성, 수정, 삭제, 좋아요)
- 자유게시판에서 작성 버튼을 누르면 생성페이지로 이동하여 게시글을 작성 할 수 있다.
- 자유게시판에서 수정 버튼을 누르면 수정페이지로 이동하여 게시글을 수정 할 수 있다. 단, 자신이 작성한 게시글만 해당한다.
- 자유게시판에서 삭제 버튼을 누르면 게시글을 삭제 할 수 있다. 단, 자신이 작성한 게시글만 해당한다.
- 자유게시판에서 작성된 게시글에 좋아요를 누를 수 있다. 단, 좋아요 취소는 자신이 좋아요를 누른 게시글만 가능하다.
4. 댓글(생성, 수정, 삭제)
- 자유게시판에서 댓글 내용란에 댓글을 작성 후 작성 버튼을 누르면 댓글을 작성 할 수 있다.
- 자유게시판에서 댓글에 수정 버튼을 누르면 댓글을 수정 할 수 있다. 단, 자신이 작성한 게시글만 해당한다.
- 자유게시판에서 댓글에 삭제 버튼을 누르면 댓글을 삭제 할 수 있다. 단, 자신이 작성한 게시글만 해당한다.
5. 로스트아크 캐릭터 종합 정보 검색
- 캐릭터 검색란을 통해 로스트아크 캐릭터를 검색 할 수 있다.
- 캐릭터를 검색하면 해당 캐릭터의 종합 정보(장비, 스킬, 아바타 등등)를 확인 할 수 있다.
6. 로스트아크 공지사항, 이벤트, 게임컨텐츠 조회
- 공지사항, 이벤트, 게임컨텐츠 페이지에서 현재 진행중인 공지사항, 이벤트, 게임컨텐츠를 확인 할 수 있다.
   
  
## 사용된 기술 스택
<div align="center">
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=springboot&logoColor=white" height="30" />
  </a>

  <a href="https://spring.io/projects/spring-data-jpa">
    <img src="https://img.shields.io/badge/Spring_Boot_JPA-6DB33F?style=flat&logo=springboot&logoColor=white" height="30" />
  </a>

  <a href="https://www.java.com/">
    <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white" height="30" />
  </a>

  <br>

  <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript">
    <img src="https://img.shields.io/badge/JavaScript-FFCA28?style=flat&logo=javascript&logoColor=black" height="30" />
  </a>

  <a href="https://mustache.github.io/">
    <img src="https://img.shields.io/badge/Mustache-FF9E00?style=flat&logo=mustache&logoColor=black" height="30" />
  </a>

  <a href="https://developer.mozilla.org/en-US/docs/Web/CSS">
    <img src="https://img.shields.io/badge/CSS-1572B6?style=flat&logo=css3&logoColor=white" height="30" />
  </a>

  <a href="https://aws.amazon.com/ec2/">
    <img src="https://img.shields.io/badge/AWS_EC2-FF9900?style=flat&logo=amazon-aws&logoColor=white" height="30" />
  </a>



  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white" height="30" />
  </a>

  <br>

  <a href="https://github.com/features/actions">
    <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=flat&logo=github-actions&logoColor=white" height="30" />
  </a>

  <a href="https://aws.amazon.com/codedeploy/">
    <img src="https://img.shields.io/badge/AWS_CodeDeploy-0073BB?style=flat&logo=amazon-aws&logoColor=white" height="30" />
  </a>

  <a href="https://github.com/">
    <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white" height="30" />
  </a>
</div>

## 시스템 아키텍처
![시스템 아키텍처](https://github.com/user-attachments/assets/a2495ba1-7b0d-4ae5-9329-13078a91e9a3)
## ERD
![erd](https://github.com/user-attachments/assets/0f8b1b30-1d83-4495-9477-b0152074583f)

