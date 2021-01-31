# The spring-idea repo is a very simple introduction to Spring
Purpose of this repo is to build up the very basics of Spring and Java.  This is used for educational purposes, it has no real project purpose.

## Run this project and try to understand some of its parts.  Be sure to upgraded to IntelliJ IDEA Ultimate!!!  Then clone it.
<OL> 
<LI>Using IntelliJ clone Repo</LI>
<LI>Run main method in src/main/java/com.example.demo/Hello.java</LI>
<LI>In browser type: localhost:8080</LI>
</OL>
Notes:
<OL>
<LI>The src/resources/templates/index.html is the default HTML page. It introduces some things in this project.</LI>  
<LI>Examine ThymeLeaf fragments (headfile.html, header.html, footer.html) to get familiar with Templates and see how they are included in other html files.</LI>
<LI>Find Main.java and MainContoller.java to understand annotations and routes. This will help you learn code behind HTML</LI>
</OL>


## Instruction for Spring/Java beginning developer

To get started with development, you should spend time with these links:

Hello World:
[Jetbrains on beginning your own project](https://www.jetbrains.com/help/idea/your-first-spring-application.html)

Everything about Spring from sping.io:
[Spring Homesite](https://spring.io/)

At the end of each development cycle it is a good practice to create a self-contained JAR file for deployment.  IJ can delegate Maven to build Jar that works for deployment (Build,Execution,Depoyment > Build Tools > Maven > Runner)
Select the wrench on Maven screen
![Maven](assets/maven.png)
Navigate to Maven Settings, drill into Maven > Runner and enable "Delegate IDE build/run actions to Maven"
![Delegate IDE](assets/mavenrunner.png)
After enabling use the hammer to build (not the play) to produce VERY USEFUL deployment Jar which is created in target path of project.
![Deployable Jar file](assets/target.png)


# How to deploy Spring on Raspberry Pi
Java is its own server.  A Java program runs servlets, aka the Java-enabled web server.  Servlets work on the server-side. Servlets are capable of handling complex requests obtained from web server.
![Visual of Web Service](https://github.com/nighthawkcoders/spring-idea/blob/master/assets/javaservlets.png)

First you need to install Java on your Raspberry Pi.  The default as of this writing is OpenJDK 11.

    pi@raspberrypi:~ $ sudo apt update; sudo apt upgrade
    pi@raspberrypi:~ $ sudo apt install default-jdk

To run and start application automatically it will require a .service file that executes java. In this service file we are providing details the service: it should start after “network.target” has been started, ExecStart is the command that executes the service, currently this is running a JAR file. Create a 'service' file like the one below and place it in: /etc/systemd/system/<your_service_file>.service

    [Unit]
    Description=Java
    After=network.target

    [Service]
    User=pi
    Restart=always
    ExecStart=java -jar /home/pi/IdeaProjects/spring-idea/target/serving-web-content-0.0.1-SNAPSHOT.jar
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