# RestAPI-Performance-Monitoring-Test

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

스프링 부트 애플리케이션의 상태를 제공하는 스프링 Actuator를 이용합니다.

상태지표를 과거부터 현재까지 보관하는 Prometheus와 시각화 도구인 Grafana를 

사용하여 모니터링 시스템을 시각화하여 구축했습니다.

http://3.37.157.135:3000/d/X034JGT7Gz/springboot-apm-dashboard
![image](https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/68a5ee9f-a9d9-4ddc-8e06-8c5d95336cba)


## 2. 서버 이상시 메일 알람
### [문제 상황]
자정이 지나고 새벽에 발생한 서버 문제에 대처할 수 없었습니다.

### [해결 방안]


## 3. 서버 최적화
### [문제 상황]
서버에 순간적으로 평소의 20배에 달하는 요청이 들어와서 서버에 

부하가 발생해 큰 손실이 발생했습니다.

### [원인]

api 300번 요청시 

![image](https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/713639f6-28aa-4fdd-89c4-966260a34b7c)

![image](https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/9e0f1352-ab89-4e9a-8b54-46659805b1e7)

![image](https://github.com/kimeunje/RestAPI-Performance-Monitoring-Test/assets/143335772/292a87cd-892a-49f7-af93-ddd87c5ea6be)

### [해결 방안]
