﻿# RestAPI-Performance-Monitoring-Test

이 프로젝트는 실무에서 당시 적용하지 못했던 아쉬운 점들을 구현한 것입니다.

### 기본 서버
#### - 주소
http://217.142.137.135  
`ID : test@goo.com`, `PS : test1234`  

#### - 서버 스펙
t2.micro (Amazone Cloud)  
CPU : 1 CORE,  RAM : 1 GB  

#### - 사용 기술
JDK 1.17 + MySQL 8.0  
Spring Boot 3.2.2 + Vue 3  

### 모니터링 서버
#### - 주소
http://3.37.157.135:3000/d/X034JGT7Gz/springboot-apm-dashboard  
`ID : admin`,  `PS : test1234`

#### - 서버 스펙
VM.Standard.E2.1.Micro (Oracle Cloud)  
CPU : 1 CORE,  RAM : 1 GB  
  
#### - 사용 기술
Spring Actuator 3.2.2  
Prometheus 2.43 + Grafana 10.3.3  



## 1. 서버 모니터링 시각화
### [문제 상황]
자정이 넘어가기 전 서버가 정상적으로 작동하고 있는지 확인해야 했으며,  
콘솔 창에 출력되는 내용물로는 모니터링에 물리적으로 한계가 있었습니다.

### [해결 방안]
실무에서 사용했던 Flask 웹 프레임워크 대신 Spring 웹 프레임워크를 사용하고,  
스프링 부트 애플리케이션의 상태를 제공하는 스프링 Actuator를 이용했습니다.

상태지표를 과거부터 현재까지 보관하는 Prometheus와 시각화 도구인 Grafana를  
사용하여 모니터링 시스템을 시각화하여 구축했습니다.

http://3.37.157.135:3000/d/X034JGT7Gz/springboot-apm-dashboard
![image](https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/68a5ee9f-a9d9-4ddc-8e06-8c5d95336cba)


## 2. 서버 이상시 메일 알람
### [문제 상황]
자정이 지나고 새벽에 발생한 서버 문제에 대처할 수 없었습니다.

### [해결 방안]
Grafana에서 제공하는 alert 기능을 이용해 CPU 등 특정 자원의 소모량이 일정치 이상 넘어가면 메일이 오게 설정하였습니다.

<img src="https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/80324f1f-aa74-4711-ab1d-be6edbfdc156" width="60%" height="60%" />

<img src="https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/abbe8fe3-035a-4360-ba5f-0909ecec45a4" width="80%" height="80%" />

## 3. 서버 최적화
### [문제 상황]
서버에 순간적으로 평소의 20배에 달하는 요청이 들어와서 서버에 부하가 발생해 큰 손실이 발생했습니다.

### [원인]

#### api 300번 요청시 

##### `CPU 소모량`
![image](https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/713639f6-28aa-4fdd-89c4-966260a34b7c)

##### `응답 속도`
<img src="https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/9e0f1352-ab89-4e9a-8b54-46659805b1e7" width="40%" height="40%" /> <img src="https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/292a87cd-892a-49f7-af93-ddd87c5ea6be" width="40%" height="40%" />

#### api 2000번 요청시

##### `CPU 소모량`
![image](https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/1d4a1d96-1d8f-43c4-9b16-22d306cf8ba3)

##### `응답 속도`
<img src="https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/ebb9f4d8-924c-40f9-a9d3-d64aaff27196" width="40%" height="40%" /> <img src="https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/c8182acf-a1fb-4ee9-8226-3dc527a0b7b1" width="40%" height="40%" />


### [해결 방안]
단순 api 요청의 경우 응답 속도면에서는 큰 차이가 없었으나, CPU 소모량에서 요청이 많을수록 훨씬 높은 수치를 보여줬습니다.


이론적으로는 클라우드 공급사에 요청해 CPU 사양을 높이는 스케일업(Scale-up)이나 서버를 여러대 추가해 요청을 분산시키는 스케일아웃(Scale-out) 방식으로 해결 방안을 낼 수 있습니다.


현재 여유 인스턴스가 없어서 테스트할 수 없었지만, 인스턴스가 존재했다면 Nginx 에서 제공해주는 로드밸런싱 기능을 이용해 서버를 분산시켜 요청을 받아 해결하겠습니다.
