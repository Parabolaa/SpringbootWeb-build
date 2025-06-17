package org.example.SpringbootWeb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.SpringbootWeb.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> list();

    void deleteById(Integer id);

    void insert(Dept dept);

    Dept getById(Integer id);

    void updateById(Dept dept);
}
