package com.example.astrademostep3;

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
}