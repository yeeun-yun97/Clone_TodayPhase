# Clone_TodayPhase
### SharedPreference를 사용하여 여러 개의 격언을 저장하고(CRUD 가능), <br>그 중 한 개를 랜덤을 보여주는 안드로이드 애플리케이션

## 스택
<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=Android&logoColor=black"/> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=Kotlin&logoColor=black"/> 

## 클론 출처
위키북스
만들면서 배우는 코틀린 & 안드로이드 프로그래밍
유병석
http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158392529&orderClick=LAG&Kc=

### 따라하면서 배운 것
SharedPreference 사용하여 데이터 저장하기 및 불러오기.    
-> 따로 조사하여 이들이 기기 내부에 xml 형태로 저장된다는 것을 알았고, 내부 파일도 확인해 보았다.    
Kotlin이 무서웠는데 간단한 예제를 따라하며 자신감을 붙일 수 있었다.    
-> 함수를 인자로 받아서 사용해보기도 하고, nullable과 관련해서 적응하는 시간을 가졌다.    
-> data 클래스와 companion object를 사용하여 함수 만드는 방법을 배우게 되었다.

### 새로 추가해본 것
Button을 중요한 버튼 (primary), 덜 중요한 버튼 (secondary)로 두 가지 스타일을 만들고, 적용해 보았다.

책에서 사용한 Linear 대신 constraint 레이아웃 컨테이너를 사용해 주었다.

ui 및 ux를 책보다 직관적이고 깔끔하게 개선해보고자 하였다.     
->수정, 삭제 등의 버튼을 글자로 하지 않고, 아이콘화하였다.     
->딱 한 개만 골라서 수정하고, 저장하도록 만들었다.     
->새로 격언을 만들기 위한 빈 슬롯을 임의로 넣는 대신 추가하는 버튼을 따로 만들어주었다.     

컬러 테마를 적용하기 위해 테마를 수정해보았다.     
->파스텔 톤 보라색과 민트색 회색으로 이루어진 색 코드들을 color파일에 추가하고, theme에 설정해주었다.     

SharedPreference로 저장하는 방법과 불러오는 방법을 약간 수정하였다.      
(책에서는 인덱스를 지정하여 저장하며, 0부터 19까지 총 20개의 격언을 저장할 수 있었다.)    
->인덱스 없이 내부에서 숫자를 증가시키며 저장하며, 격언의 개수에 제한이 없도록 바꾸었다.     



### 기능 소개
----

#### 랜덤으로 격언 하나 보기
<img src = "https://user-images.githubusercontent.com/60867063/161300961-018cad5c-3d93-4d2e-a703-b391aaa601e6.gif" width="40%" height="40%">

#### 격언 추가하기

#### 격언 목록 보기

#### 격언 수정하기

#### 격언 삭제하기
