package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class PersonDaoJdbc implements PersonDao {
    private JdbcOperations jdbcOperations;
    private NamedParameterJdbcOperations jdbc;

    private static class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Person(id, name);
        }
    }

    public PersonDaoJdbc(JdbcOperations jdbcOperations, NamedParameterJdbcOperations jdbc) {
        this.jdbcOperations = jdbcOperations;
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbcOperations.queryForObject("SELECT count (*) FROM Persons", Integer.class);
    }

    @Override
    public void insert(Person person) {
        jdbcOperations.update("insert into persons (id,name) values(?,?)", person.getId(), person.getName());
    }

    @Override
    public Person getById(int id) {
        return jdbcOperations.queryForObject("select * from persons where id = ?",
                new Object[]{id}, new PersonMapper());
    }

    @Override
    public Person getByID(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select * from persons where id = :id", params, new PersonMapper());
    }

    @Override
    public void deleteByID(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbc.update("delete from persons where id = :id", params);
    }

    @Override
    public List<Person> getAll() {
        return jdbcOperations.query("select * from persons", new PersonMapper());
    }
}
