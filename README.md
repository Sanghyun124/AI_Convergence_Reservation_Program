<div align="center">
  <h2>AI Convergence Seminar room Reservation</h2>
  숭실대학교 AI융합학부 세미나실 예약을 위한 웹 어플리케이션입니다.<br>
  현재 아직 개발 중에 있습니다.
</div>
<p align="center"><img src="https://img.freepik.com/free-vector/appointment-booking-with-calendar_52683-39831.jpg?w=1060&t=st=1696960629~exp=1696961229~hmac=f7b47ddf5aed11fa5f4591190bef3d3c042b0b90e5534fadf7311921817a1d77" width="462" height="313"></p>


## | 🔍 개발 배경
기존 세미나실 예약 방식은 학사 조교님께 구글 캘린더 공유 요청을 한 후 구글 캘린더에 사용 일정을 등록하여 예약하는 방식입니다.<br><br>

예약 규칙에는 주 2회, 회당 2시간 한정이라는 규칙과 매달 1일 기준 다음 달까지만 예약이 가능하다는 규칙이 있습니다. 다만 이 규칙들이 잘 지켜지는 확인하기 위해서는 조교님께서 일일이 확인해야 한다는 한계점이 존재했습니다.
또한 재학생이라도 세미나실 예약을 하기 위해서는 조교님께 구글 캘린더 공유 요청을 해야한다는 번거로움이 있었습니다.<br><br>

이에 재학생이 구글 캘린더 공유 요청을 하지 않아도 되고 이 규칙들을 시스템적으로 검증할 수 있는 웹 어플리케이션을 만들고자 하여 이 프로젝트를 기획하였습니다.


## | ⏱️ 개발 기간
- 2023.08.29 ~


## | 👬🏻 프로젝트 멤버
- Back-end
  - [박상현](https://github.com/Sanghyun124) : 기획, 예약 관리
  - [김창훈](https://github.com/C-H-Kim) : 기획, 회원 관리
- Front-end
  - [한수호](https://github.com/unbroken2650) : 프론트 전반


## | ⚙️ 개발 환경
- Programming Language : `java 11`
- IDE : `Intellij`
- Framework : `Springboot 2.7.15 gradle`
- DB : `MySQL`
- ORM : `JPA`


## | 💡 주요 기능
- 로그인
  - JWT를 이용한 로그인 기능
  - 비밀번호 찾기
- 마이 페이지
  - 내 예약 확인 및 삭제
  - 비밀번호 변경
- 세미나실 예약
  - 해당 날짜의 예약 내역 확인
  - 새로운 예약 생성
- 관리자
  - 현재 예약 내역 확인 및 삭제
  - 학생 명단 확인
  - 신규 학생 추가
  - 기존 학생 정보 변경
  - 명단에서 학생 삭제
  - 학생 명단 등록
