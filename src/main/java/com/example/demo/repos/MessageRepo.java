package com.example.demo.repos;

import com.example.demo.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message,Long> {

    List<Message> findByTag (String tag); //спринг сам реализует фильтр и достает то что нужно с помощью SQL. Запрос создает исходя из названия. Описание есть в документации
                                                //https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
}
