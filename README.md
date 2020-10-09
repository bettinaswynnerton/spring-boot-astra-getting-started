# spring-boot-astra-getting-started

This is an example journey of building a simple REST api that reads and writes data to Datastax Astra, starting with the Spring boot initializer.

The journey consists of four steps:

Step 1:

Initialize the the project with Spring initializer and create a minimal hello world example with a Rext controller. 

Start here:

https://start.spring.io/

Select only one dependency:

Spring Web

Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.

Choose your project and package name, accept all other defaults ( requires Java 11 ) and click `Generate` to download the zipped starter package. Unzip and open with IntelliJ (for example)

The project can then be built and run with `mvn spring-boot:run`, but no page is served.

Create a Rest controller class:

```


@RestController
public class GreetingsController {

    @GetMapping("/greeting")
    public String  hello(){
        return "hello world!!!";
    }

}

```

Ctrl-C and mvn spring-boot:run to restart the app, navigate to http://localhost:8080/greeting, and you should see the greeting. 

Run the project from this folder in gitpod.

Step 2:

Expand the structure of the project with a Rest controller, an interface and an implementation of the interface, wire the implementation to the controller.



Step 3:

Create a Cassandra driver bean that connects to an Astra instance and wire it to the implementation. Test with reads.

Step 4: 

Expand the interface and its implementation for further reads and writes to database. 



