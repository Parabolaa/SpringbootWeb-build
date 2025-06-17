package org.example.SpringbootWeb.service;

import org.example.SpringbootWeb.pojo.Dept;
import org.example.SpringbootWeb.vo.DeptShortVO;

import java.util.List;

public interface DeptService {
    List<DeptShortVO> list();
    void deleteById(Integer id);
    void insert(Dept dept);
    Dept getById(Integer id);
    void updateById(Dept dept);
}
