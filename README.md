# Reliable-Chatting-Platform

FCM 푸쉬 솔루션과 STOMP 프로토콜을 기반으로 신뢰성을 높인 자체 메신저 서버를 구축하여 기존의 메신저 App 서비스에서 제공하는 기능뿐만 아니라 O2O 서비스와 같은 App에서 활용성을 높일 수 있는 메신저 기능을 구현하였습니다.

<hr/>

# System Diagram

![image](https://user-images.githubusercontent.com/57346393/128634304-0e0d914c-c29b-4d96-a227-227d622fdbdd.png)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fnowwater%2FReliable-Chatting-Platform.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fnowwater%2FReliable-Chatting-Platform?ref=badge_shield)

<hr/>

# 기능 설명

## 온라인 및 오프라인 유저에 따른 메시지 전송 및 수신

### 메시지 전송

- Websocket의 상위 프로토콜인 Stomp 프로토콜을 사용합니다.

- 텍스트 메시지의 경우 StompClient의 전송 기능을 통해 Stomp 서버로 전송합니다.

- 이미지 파일은 바이트 배열로 변환하여 데이터를 `Base64 문자열`로 인코딩합니다. 결과적으로 JSON 형태로 StompClient의 전송 기능을 통해 Stomp 서버로 전송합니다.

### 온라인 유저의 메시지 수신

- Websocket의 상위 프로토콜인 Stomp 프로토콜을 사용합니다.

- Stomp 서버로부터 데이터 전송 받습니다.

- 이미지 파일은 마찬가지로 `Base64 문자열` 을 디코딩하여 바이트 배열로 변환 후 이미지 파일 쓰기를 통해 복원합니다.

- 메시지 수신의 경우, 수신 성공 여부를 서버에게 전송합니다.

### 오프라인 유저의 메시지 수신

- 푸시서버의 종류 중 하나인 Google의 FCM 서버로부터 메시지 알림을 수신합니다.

- 실제 메시지 내용은 온라인으로 연결되었을 때, 수신받을 수 있습니다.

- 오프라인 상태에서 온라인 상태로 전환하였을 때, StompServer로부터 Subscribe한 Topic의 변경 여부를 요청 및 응답 받습니다.

- 변경된 Topic의 내용을 StompServer에 요청하여 안드로이드 내의 Local DB와 Sync를 맞춥니다.

<hr/>

## StompServer의 메시지 핸들링 및 데이터 보관

### 메시지 전송자의 메시지 핸들링

- Websocket의 상위 프로토콜인 Stomp 프로토콜을 사용합니다.

- 메시지를 파싱하여 메시지를 전송할 토픽을 선정합니다.

- 브로커 채널을 통해 해당 토픽 채널에 메시지를 전송합니다.

### 메시지 수신자에게 전송

- 수신자(토픽 구독자)에게 Stomp 프로토콜로 메시지를 전송합니다.

- 수신자로부터 수신 성공 여부를 받아옵니다.

- 오랜기간 수신자로부터 응답이 없을 경우, FCM에 알림 메시지를 전송합니다.

- 데이터 손실을 우려하여 FCM의 전송 실패 응답을 받으면 FCM에 해당 메시지를 재전송합니다.

### 데이터 저장

- 단순 Text의 경우 Mysql에 저장합니다.

- StompServer와 Mysql 사이에 Redis를 두어 텍스트를 캐싱합니다.

- 이미지의 경우, 파일 저장용 스토리지 사용합니다.

- 정해놓은 정책에 따라 저장되는 데이터 저장기한 설정합니다.

<hr/>

# 기대효과

- 본 과제를 통해 적은 인프라와 인력으로 자체 메신저 서비스를 개발할 수 있고 실제로 기업에서 활용함에 따라 메신저 개발 장벽을 낮출 수 있을 거라 예상합니다.

- FCM 솔루션과 STOMP 프로토콜을 이용함으로써 신뢰성을 강조한 기업 자체 메신저 서버를 구축할 수 있으리라 기대합니다.

- App 서비스 분야에 적합한 메신저 기능을 추가적으로 개발함으로써 맞춤 메신저 서비스 형태를 갖출 수 있을 것이라 예상합니다.


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fnowwater%2FReliable-Chatting-Platform.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fnowwater%2FReliable-Chatting-Platform?ref=badge_large)