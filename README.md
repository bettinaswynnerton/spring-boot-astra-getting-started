# spring-boot-astra-getting-started

This is an example journey of building a simple REST api that reads and writes data to Datastax Astra, starting with the Spring boot initializer.

The journey consists of four steps:

## Step 1:

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

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/bettinaswynnerton/spring-boot-astra-getting-started/)

When running on gitpod, navigage to your equivalent page to see the greeting, e.g:

```
https://8080-fc467d78-1ba4-4011-afdc-80525e2a26bf.ws-eu01.gitpod.io/greeting
```

##  Step 2:

Expand the structure of the project with a Rest controller, an interface and an implementation of the interface, wire the implementation to the controller.

We are adding a couple of files:

`GreetingService.java`

The implementation agnostic interface that defines our methods for a greeting service.

`GreetingImplementation.java`

A specific implementation of the interface. The implementation is annotated with `@Component` for autodiscovery by the Spring framework. 

And we are autowiring the interface to the rest controller with this annotation:

```
    @Autowired
    private GreetingService greetingservice;
```

In the `/greeting` route we are now using the methods from the interface (which in turn uses the implementation):

```
    @GetMapping("/greeting")
    public String  hello(){
        return greetingservice.sayHello();
    }
```

## Step 3:

Create a Cassandra driver bean that connects to an Astra instance and wire it to the implementation. Test with reads.

Create a class with annotation @Configuration

```
import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AstraDriverConfig {

    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder().build();
    }
}
```

We then autowire this into the implementation:

```
    @Autowired
    public CqlSession cqlSession;

    public GreetingImplementation(CqlSession cqlSession){
        this.cqlSession = cqlSession;
    }
```

And we use a `select` statement to read from a greeting from the database:

```
    @Override
    public String sayHello() {

        //This assumes a table of name greeting with a column of name greeting in the database
        SimpleStatement statement = SimpleStatement.builder("select * from greeting").build();

        ResultSet result = this.cqlSession.execute(statement);

        Row row = result.one();


        return row.getString("greeting");
    }
```

This requires a table of name `greeting` with a column `greeting` in the Astra database.

We also need to add the driver dependency to our pom.xml

```
		<dependency>
			<groupId>com.datastax.oss</groupId>
			<artifactId>java-driver-core</artifactId>
			<version>4.6.1</version>
		</dependency>
```



## Step 4: 

Expand the interface and its implementation for further reads and writes to database. 



