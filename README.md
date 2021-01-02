# The spring-idea repo is a very simple introduction to Spring
Purpose of this repo is to build up the very basics of Spring and Java.

## Instruction for IntelliJ IDEA beginner
Jetbrains has good Tutorial.  To get started, you can spend time with these links:

[Beginning your own project](https://www.jetbrains.com/help/idea/your-first-spring-application.html)

[Understanding Spring and Springboot](https://dzone.com/articles/the-springbootapplication-annotation-example-in-ja#)

[Spring Homesite](https://spring.io/)

## Or start with this project and extend it.
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

Now that the Spring application is running as a service, an NGINX proxy allows opening the application to an unprivileged port and setting up SSL.
Create an NGINX configuration for the reverse proxy, File: /etc/nginx/conf.d/<my_nginx_file>.conf
    
    server {
            listen 80;
            listen [::]:80;
    
            server_name example.com;
    
            location / {
                 proxy_pass http://localhost:8080/;
                 proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                 proxy_set_header X-Forwarded-Proto $scheme;
                 proxy_set_header X-Forwarded-Port $server_port;
            }
    }

Test the configuration to make sure there are no errors:

    sudo nginx -t

If there are no errors, restart NGINX so the changes take effect:

    sudo systemctl restart nginx