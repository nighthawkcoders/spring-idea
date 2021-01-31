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

At the end of each development cycle it is a good practice to create a self-contained JAR file for deployment.  IntelliJ (IJ) can delegate Maven to build a Jar that works for deployment.  This file can be copied to a new host and run according to deployment instructions in the document.

Start by picking "Maven" off of right boarder of IJ.  Click-on the "Wrench" on Maven screen. Navigate to Maven Settings.
![Maven](assets/maven.png)

Drill into Maven > Runner (Build,Execution,Depoyment > Build Tools > Maven > Runner).  Click and Apply setting "Delegate IDE build/run actions to Maven". 
![Delegate IDE](assets/mavenrunner.png)

Returning to IJ Project work use the "Hammer" to build (not the traditional "Play") this will produce a VERY USEFUL deployment Jar which is created in "Target" path as shown.

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
    
# How to deploy Spring on Amazon Web Services (AWS)
Login into your AWS IAM user, search for EC2.

To get started, launch an Amazon EC2 instance, which is a virtual server in the cloud.
![Launch EC2 instance](assets/ec2launch.png)

Step 1: Choose an Amazon Machine Image (AMI)Cancel and Exit
An AMI is a template that contains the software configuration (operating system, application server, and applications) required to launch your instance. Ubuntu is a free tier operating system that uses the Linux kernel and is very compatible with Raspbian used on Raspberry Pi's.
![Select EC2 OS](assets/ec2os.png)

Step 2: Choose an Instance Type
Amazon EC2 provides a wide selection of instance types optimized to fit different use cases. Instances have varying combinations of CPU, memory, storage, and networking capacity.   Stick with Free Tier options, as of this writing t2.mico with free tier designation is suggested.  Click Review and Launch which skips a bunch of steps.

Step 7: Review Instance Launch
This step provides security warning, click on warning and it will take you back to Step 6.

Step 6: Configure Security Group
Cancel Previous Review and Launch
A security group is a set of firewall rules that control the traffic for your instance. On this page, you can add rules to allow specific traffic to reach your instance. For example, if you want to set up a web server and allow Internet traffic to reach your instance, add rules that allow unrestricted access to the HTTP and HTTPS ports.  Here is an example of restriction on SSH and opening up HTTP.
![Select EC2 OS](assets/ec2security.png)



    Keep the settings at default

    For storage leave it at 8 gibs you get 30 for free across all ec2 instances

    For tags I add a name for each of my instances to keep track of them

    For this next part under ssh port 22 select my ip, advance( if you know your assigned dynamic cidr from isp you can enter that in)

    Review and launch you EC2

    Download the key pair file used authenticate when signing into the computer

To login into the EC2 instance you will use SSH (secure shell)
ssh -i <keypair.pem> ubuntu@ipv4

The remaining procedures should be the same as a Raspberry Pi

