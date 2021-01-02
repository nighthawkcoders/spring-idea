# The spring-idea repo is a very simple introduction to Spring
Purpose of this repo is to build up the very basics of Spring and Java.

## Instruction for IntelliJ IDEA beginner
Jetbrains has good Tutorial.  To get to get setup to my point, you can spend time with listed tutorials, including editing POM files.  Any simple HTML change requires full compile, a lot file transfer happens to target directory.

[Beginning your own project](https://www.jetbrains.com/help/idea/your-first-spring-application.html)

[Understanding Spring and Springboot](https://dzone.com/articles/the-springbootapplication-annotation-example-in-ja#)

[Spring MVC](https://spring.io/guides/gs/serving-web-con)

## Or start with this project
<OL> 
<LI>Using IntelliJ clone Repo</LI>
<LI>Run main method in src/main/java/com.example.demo/Hello.java</LI>
<LI>In browser type: localhost:8080</LI>
</OL>
Note: The src/resources/templates/index.html is the default page. This page has a form and actions.  The results of these actions map to routes in Hello.java and HelloContoller.

# How to deploy Spring on Raspberry Pi
Basic ides is to run a JAR.  A Java program runs servlets, aka the Java-enabled web server.  Servlets work on the server-side. Servlets are capable of handling complex requests obtained from web server.
![Visual of Web Service](https://github.com/nighthawkcoders/spring-idea/blob/master/assets/javaservlets.png)

To start the JAR automatically it will require a .service file that executes the JAR file. In this service file we are providing details the service: it should start after “network.target” has been started, ExecStart is the command that executes the service, etc. Create a file like the one below and place it in: /etc/systemd/system/<your_service_file>.service

    [Unit]
    Description=Java Service
    After=network.target

    [Service]
    User=pi
    Restart=always
    ExecStart=java -jar /home/pi/Documents/spring/<jar_name_here>.jar

    [Install]
    WantedBy=multi-user.target 
 
Running these commands will test your service:
 
     sudo systemctl start <my_service_file>.service
     sudo systemctl stop <my_service_file>.service

Running these commands will enable or disable service at a reboot:

    sudo systemctl enable <my_service_file>.service
    sudo systemctl disable <my_service_file>.service

