package org.example.SpringbootWeb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.SpringbootWeb.mapper.EmpMapper;
import org.example.SpringbootWeb.pojo.Emp;
import org.example.SpringbootWeb.pojo.PageBean;
import org.example.SpringbootWeb.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

//    public PageBean showPage(Integer page, Integer pageSize) {
//        Long total = empMapper.getTotalEmp();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.getEmpList(start, pageSize);
//        return new PageBean(total, empList);
//    }

    public PageBean showPage(Integer page, Integer pageSize,String name, Short gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.findAll(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteEmp(List<Integer> ids) {
        empMapper.deleteEmp(ids);
    }

    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    public Emp getEmpById(Integer id) {
        return empMapper.getEmpById(id);
    }

    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }

    public Emp getLoginInfo(Emp emp) {
        return empMapper.getLoginInfo(emp);
    }
}
