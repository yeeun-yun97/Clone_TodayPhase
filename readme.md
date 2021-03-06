# Clone_TodayPhase
### SharedPreference를 사용하여 여러 개의 격언을 저장하고(CRUD 가능), <br>그 중 한 개를 랜덤으로 보여주는 안드로이드 애플리케이션

## 스택
<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=Android&logoColor=black"/> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=Kotlin&logoColor=black"/> 

## 클론 출처
위키북스.     
만들면서 배우는 코틀린 & 안드로이드 프로그래밍. 유병석 지음.   
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

DataBinding을 활용하여 선언적으로 뷰에 값 표시하기. (22/04/07).       
-> 길능님께 받은 조언에 따라 DataBinding을 적용해 보았다.        
-> 이번에는 Data Class로 만든 모델을 연동해서 사용했지만, 다음에는 ViewModel로 하는 법을 배우면 좋을 것 같다.        
    
간단한 부분은 DataBinding 대신 ViewBinding으로 대체하기. (22/04/16).    
-> List를 보는 활동은 하는 일이 별로 없어, 사실 데이터 바인딩이 아니라 뷰 바인딩으로 대체할 수 있다는 것을 알게 되었다.    
-> 뷰 바인딩이 훨씬 가볍기 때문에 효율적으로 뷰 바인딩으로 대체해 보기로 하였다.

### 겪었던 곤란함, 어려웠던 것들

- 책의 예제대로면, **저장한 격언의 순서를 저장**할 방법이 없어서 고민하였다.
    - 1부터 20까지의 빈 슬롯에 저장하다 보니, 따로 날짜도 저장하지 않는 한, 격언의 순서를 알 방법이 없었다.
    - 1부터 20까지의 범위를 두는 대신, Set으로 저장한 격언들의 인덱스를 모으고, 인덱스는 무조건 1씩 증가하게 만들어, 인덱스 숫자의 크기가 곧 생성된 순서가 되도록 수정하였다.
    - 이 수정사항을 통해, 새로 만들어진 격언이 recyclerView 최상단에 추가되도록 만들 수 있었다. (성공!)
- recyclerView에서 클릭했을 때 엉뚱한 것이 삭제되는 일이 발생하였다.
    - recyclerView에서의 위치와 격언의 인덱스가 동시에 필요한 것을 간과하고 있었다.

## 기능 소개

### 랜덤으로 격언 하나 보기
![Apr-02-2022 19-13-14](https://user-images.githubusercontent.com/60867063/161378841-e3d5b611-a6c8-410b-971a-2faf7096d3c7.gif)

### 격언 목록 보기
![Apr-02-2022 19-12-40](https://user-images.githubusercontent.com/60867063/161378693-ee51f422-1048-4bf9-94e9-1711f67fed43.gif)

### 격언 추가하기
![Apr-02-2022 19-16-28](https://user-images.githubusercontent.com/60867063/161378688-c74234c1-97a6-4bc8-95a6-26e5ff811e1c.gif)

### 격언 수정하기
![Apr-02-2022 19-20-07](https://user-images.githubusercontent.com/60867063/161378803-ffef8b8f-7df4-4c43-92e1-f0009225368d.gif)

### 격언 삭제하기
![Apr-02-2022 19-20-44](https://user-images.githubusercontent.com/60867063/161378823-6f87fc1f-abeb-48a9-8ebd-b067a848bc6e.gif)

### 격언 공유하기
![Apr-02-2022 19-51-46](https://user-images.githubusercontent.com/60867063/161379962-6468c7ea-b155-4e50-a0d3-f2bb754330ce.gif)

