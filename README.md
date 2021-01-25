# The spring-idea repo is a very simple introduction to Spring
Purpose of this repo is to build up the very basics of Spring and Java.  This is used for educational purposes.

## Instruction for IntelliJ IDEA beginner
To get started, you can spend time with these links:

[Jetbrains on beginning your own project](https://www.jetbrains.com/help/idea/your-first-spring-application.html)

[Understanding Spring and Springboot Annotations](https://dzone.com/articles/the-springbootapplication-annotation-example-in-ja#)

[Spring Homesite](https://spring.io/)

## Or start with this project and try to understand it.
<OL> 
<LI>Using IntelliJ clone Repo</LI>
<LI>Run main method in src/main/java/com.example.demo/Hello.java</LI>
<LI>In browser type: localhost:8080</LI>
</OL>
Notes:
<OL>
<LI>The src/resources/templates/index.html is the default HTML page. Not technically interesting until you examine ThymeLeaf fragments (headfile.html, header.html, footer.html).</LI>
<LI> Find Main.java and MainContoller.java to understand routes. This will help you find code behind HTML</LI>
</OL>

# How to deploy Spring on Raspberry Pi
Basic ides is to run a JAR.  A Java program runs servlets, aka the Java-enabled web server.  Servlets work on the server-side. Servlets are capable of handling complex requests obtained from web server.
![Visual of Web Service](https://github.com/nighthawkcoders/spring-idea/blob/master/assets/javaservlets.png)

First you need to install Java on your Raspberry Pi.  The default as of this writing is OpenJDK 11.

    pi@raspberrypi:~ $ sudo apt update; sudo apt upgrade
    pi@raspberrypi:~ $ sudo apt install default-jdk

To run and start the JAR automatically it will require a .service file that executes the JAR file. In this service file we are providing details the service: it should start after “network.target” has been started, ExecStart is the command that executes the service, currently this is mimicking what IntelliJ produces. Create a file like the one below and place it in: /etc/systemd/system/<your_service_file>.service

    [Unit]
    Description=Java
    After=network.target

    [Service]
    User=pi
    Restart=always
    ExecStart=/usr/lib/jvm/java-1.11.0-openjdk-armhf/bin/java -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -javaagent:/home/pi/idea/lib/idea_rt.jar=37035:/home/pi/idea/bin -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -Dfile.encoding=UTF-8 -classpath /home/pi/IdeaProjects/spring-idea/target/classes:/home/pi/.m2/repository/org/springframework/boot/spring-boot-starter-thymeleaf/2.4.1/spring-boot-starter-thymeleaf-2.4.1.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot-starter/2.4.1/spring-boot-starter-2.4.1.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.4.1/spring-boot-starter-logging-2.4.1.jar:/home/pi/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/home/pi/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/home/pi/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.13.3/log4j-to-slf4j-2.13.3.jar:/home/pi/.m2/repository/org/apache/logging/log4j/log4j-api/2.13.3/log4j-api-2.13.3.jar:/home/pi/.m2/repository/org/slf4j/jul-to-slf4j/1.7.30/jul-to-slf4j-1.7.30.jar:/home/pi/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/home/pi/.m2/repository/org/yaml/snakeyaml/1.27/snakeyaml-1.27.jar:/home/pi/.m2/repository/org/thymeleaf/thymeleaf-spring5/3.0.11.RELEASE/thymeleaf-spring5-3.0.11.RELEASE.jar:/home/pi/.m2/repository/org/thymeleaf/thymeleaf/3.0.11.RELEASE/thymeleaf-3.0.11.RELEASE.jar:/home/pi/.m2/repository/org/attoparser/attoparser/2.0.5.RELEASE/attoparser-2.0.5.RELEASE.jar:/home/pi/.m2/repository/org/unbescape/unbescape/1.1.6.RELEASE/unbescape-1.1.6.RELEASE.jar:/home/pi/.m2/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar:/home/pi/.m2/repository/org/thymeleaf/extras/thymeleaf-extras-java8time/3.0.4.RELEASE/thymeleaf-extras-java8time-3.0.4.RELEASE.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot-starter-web/2.4.1/spring-boot-starter-web-2.4.1.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot-starter-json/2.4.1/spring-boot-starter-json-2.4.1.jar:/home/pi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.11.3/jackson-databind-2.11.3.jar:/home/pi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.11.3/jackson-annotations-2.11.3.jar:/home/pi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.11.3/jackson-core-2.11.3.jar:/home/pi/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.11.3/jackson-datatype-jdk8-2.11.3.jar:/home/pi/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.11.3/jackson-datatype-jsr310-2.11.3.jar:/home/pi/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.11.3/jackson-module-parameter-names-2.11.3.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/2.4.1/spring-boot-starter-tomcat-2.4.1.jar:/home/pi/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/9.0.41/tomcat-embed-core-9.0.41.jar:/home/pi/.m2/repository/org/glassfish/jakarta.el/3.0.3/jakarta.el-3.0.3.jar:/home/pi/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/9.0.41/tomcat-embed-websocket-9.0.41.jar:/home/pi/.m2/repository/org/springframework/spring-web/5.3.2/spring-web-5.3.2.jar:/home/pi/.m2/repository/org/springframework/spring-beans/5.3.2/spring-beans-5.3.2.jar:/home/pi/.m2/repository/org/springframework/spring-webmvc/5.3.2/spring-webmvc-5.3.2.jar:/home/pi/.m2/repository/org/springframework/spring-aop/5.3.2/spring-aop-5.3.2.jar:/home/pi/.m2/repository/org/springframework/spring-context/5.3.2/spring-context-5.3.2.jar:/home/pi/.m2/repository/org/springframework/spring-expression/5.3.2/spring-expression-5.3.2.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot-devtools/2.4.1/spring-boot-devtools-2.4.1.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot/2.4.1/spring-boot-2.4.1.jar:/home/pi/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.4.1/spring-boot-autoconfigure-2.4.1.jar:/home/pi/.m2/repository/org/springframework/spring-core/5.3.2/spring-core-5.3.2.jar:/home/pi/.m2/repository/org/springframework/spring-jcl/5.3.2/spring-jcl-5.3.2.jar com.example.lessons.Main
    [Install]
    WantedBy=multi-user.target 
 
Running these commands will test your service:
 
    pi@raspberrypi:~ $ sudo systemctl start <my_service_file>.service
    pi@raspberrypi:~ $ sudo systemctl stop <my_service_file>.service

Running these commands will enable or disable service at a reboot:

    pi@raspberrypi:~ $ sudo systemctl enable <my_service_file>.service
    pi@raspberrypi:~ $ sudo systemctl disable <my_service_file>.service

Now that the Spring application is running as a service, an NGINX proxy allows opening the application to an unprivileged port and setting up SSL.
Create an NGINX configuration for the reverse proxy, File: /etc/nginx/conf.d/<my_nginx_file>.conf
    
    server {
        listen 80;
        server_name csa.nighthawkcoders.cf 192.168.1.208 70.95.179.231;        
            server_name example.com;
   
            location / {
                 proxy_pass http://localhost:8080/;
                 proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                 proxy_set_header X-Forwarded-Proto $scheme;
                 proxy_set_header X-Forwarded-Port $server_port;
            }
    }


Test the configuration to make sure there are no errors:

    pi@raspberrypi:~ $ sudo nginx -t

If there are no errors, restart NGINX so the changes take effect:

    pi@raspberrypi:~ $ sudo systemctl restart nginx