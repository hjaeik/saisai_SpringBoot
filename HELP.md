# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/#build-image)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-mongodb)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

# 주의사항

* hjaeik 개인프로젝트 입니다.
* 취업을 위해 공개 하고 있으며, app키 및 개인정보 탈취를 막기위해 별도 설정은 빼두었습니다. 아래사항 확인 부탁드립니다!
* 주의사항
* 보안을위해 미입력 사항
* 1. firebase 세팅을 위해서 resource/firebase-admin-sdk.json 필요합니다. (위치: com.saisai.web.fcm.FCMService)
* 2. Tmap API를 위해 Tmap appKey가 필요합니다. (위치: com.saisai.web.order.OrderService)
* 3. DB정보가 필요합니다. (Application.yml)
* 4. OAuth2기능을 위해 clientId, clientSecret가 필요합니다. (Application.yml)
* 5. OAuth2를 통해 Authorize를 취득한 후 RedirectUrl로 인해 별도의 서버가 하나 더 필요합니다. (현재설정: localhost:3000)
* 6. 개발중이기에 Debugging 을 위해 GlobalException은 임시 주석처리 상태입니다.

