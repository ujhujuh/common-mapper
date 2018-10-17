package com.common.mapper;

import com.common.entity.Person;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author fantasy
 * @date 2018/10/16
 * @time 15:24
 */

@Repository
public interface PersonMapper extends Mapper<Person> {

    Person selectById(Integer id);
}
