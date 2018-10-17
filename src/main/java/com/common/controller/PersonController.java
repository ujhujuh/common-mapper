package com.common.controller;

import com.common.entity.Person;
import com.common.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Random;

/**
 * @author fantasy
 * @date 2018/10/16
 * @time 15:21
 */

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @RequestMapping("findById")
    public Person findById(@RequestParam("id") Integer id) {
        Person person = personMapper.selectById(id);
        Example example = new Example(Person.class);
        example.createCriteria().andBetween("id", 1, 20);
        List<Person> list = personMapper.selectByExample(example);
        System.out.println(list);
        return personMapper.selectByPrimaryKey(id);
    }

    @RequestMapping("insert")
    public int insert() {
        Person person = new Person();
        person.setName("dengzhuo");
        person.setAge(24);
        person.setUserName("dengzhuo" + new Random().nextInt(10));
        return personMapper.insertSelective(person);
    }

    @RequestMapping("findByName")
    public List<Person> findByName() {
        Person person = new Person();
        person.setName("dengzhuo");
        return personMapper.select(person);
    }

    @RequestMapping("updateById")
    public int updateById(@RequestParam("id") Integer id) {
        Person person = personMapper.selectByPrimaryKey(id);
        person.setName("邓庆博");
        return personMapper.updateByPrimaryKey(person);
    }

    @RequestMapping("deleteById")
    public int deleteById(@RequestParam("id") Integer id) {
        Person person = personMapper.selectByPrimaryKey(id);
        return personMapper.delete(person);
    }
}
