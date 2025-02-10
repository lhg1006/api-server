# Spring Boot API Server 🚀

## 📌 프로젝트 소개
이 프로젝트는 포트폴리오 웹사이트를 위한 Spring Boot 기반의 백엔드 API 서버입니다.

### 🔗 관련 링크
- [프론트엔드 Repository](https://github.com/lhg1006/lhg-portfolio)

## 🛠 기술 스택

### Backend
- Spring Boot 2.4.2
- Java
- MySQL
- MyBatis

### 주요 의존성
- Spring Boot Starter Web
- Spring Boot Starter Web Services
- MySQL Connector Java
- MyBatis Spring Boot Starter
- Log4jdbc-log4j2-jdbc4.1

## 📊 프로젝트 구성
- Java: 97.5%
- Dockerfile: 2.5%

## 🌟 주요 기능
- RESTful API 제공
- MySQL 데이터베이스 연동
- MyBatis를 통한 데이터 접근
- 로깅 시스템

## 🚀 시작하기

### 1. Repository Fork
- 우측 상단의 'Fork' 버튼을 클릭하여 이 Repository를 자신의 GitHub 계정으로 Fork합니다.
- Fork한 Repository를 로컬 환경으로 클론합니다
- bash
git clone https://github.com/[your-username]/api-server.git

### 2. 데이터베이스 설정
- MySQL 데이터베이스 생성
- `application.properties` 또는 `application.yml` 파일에서 데이터베이스 연결 정보 설정

### 3. 애플리케이션 실행
bash
./gradlew bootRun

### 4. 빌드
bash
./gradlew build

## 🐳 Docker 지원
bash
이미지 빌드
docker build -t api-server .
컨테이너 실행
docker run -p 8080:8080 api-server

## 📂 프로젝트 구조
<p>api-server/</p>
<p>├── src/</p>
<p>│ ├── main/</p>
<p>│ │ ├── java/</p>
<p>│ │ └── resources/</p>
<p>│ └── test/</p>
<p>├── gradle/</p>
<p>├── Dockerfile</p>
<p>└── build.gradle</p>

## 🔒 라이센스
이 프로젝트는 개인 포트폴리오용으로 제작되었습니다.

## 📫 연락처
- Email: lhg961006@gmail.com
- Portfolio: https://lhg1006.github.io/
