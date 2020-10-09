package com.example.astrademostep4;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingImplementation implements GreetingService {

    @Autowired
    public CqlSession cqlSession;

    public GreetingImplementation(CqlSession cqlSession){
        this.cqlSession = cqlSession;
    }

    @Override
    public String sayHello() {

        //This assumes a table of name greeting with a column of name greeting in the database
        SimpleStatement statement = SimpleStatement.builder("select * from greeting").build();

        ResultSet result = this.cqlSession.execute(statement);

        Row row = result.one();


        return row.getString("greeting");
    }

    @Override
    public String sayHelloLanguage(String language) {

        SimpleStatement languageStatement = SimpleStatement.builder(
                "select * from greeting2 where language = ?")
                .addPositionalValue(language).build();

        ResultSet result2 = this.cqlSession.execute(languageStatement);

        Row row2 = result2.one();

        return row2.getString("greeting");
    }

    @Override
    public String insertGreeting(String language, String greeting) {

        SimpleStatement insertStatement = SimpleStatement.builder(
                "insert into greeting2 (language, greeting) values (?,?)")
                .addPositionalValue(language)
                .addPositionalValue(greeting)
                .build();

        this.cqlSession.execute(insertStatement);

        return "values inserted";
    }
}