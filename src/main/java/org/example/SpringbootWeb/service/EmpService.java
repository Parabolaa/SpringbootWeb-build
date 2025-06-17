package org.example.SpringbootWeb.service;

import org.example.SpringbootWeb.pojo.Emp;
import org.example.SpringbootWeb.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean showPage(Integer page, Integer pageSize,String name, Short gender,
                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);

    void deleteEmp(List<Integer> ids);
    void addEmp(Emp emp);
    Emp getEmpById(Integer id);
    void updateEmp(Emp emp);
    Emp getLoginInfo(Emp emp);

}
