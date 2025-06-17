package org.example.SpringbootWeb.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.SpringbootWeb.anno.Log;
import org.example.SpringbootWeb.pojo.Emp;
import org.example.SpringbootWeb.pojo.PageBean;
import org.example.SpringbootWeb.pojo.Result;
import org.example.SpringbootWeb.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result showPage(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           String name, Short gender,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("参数{},{},{},{},{},{}",page,pageSize, name, gender, begin, end);
        PageBean pageBean = empService.showPage(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable List<Integer> ids) {
        log.info("删除这些id的员工：{}", ids);
        empService.deleteEmp(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工信息{}", id);
        Emp emp = empService.getEmpById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("根据id更新员工{}", emp);
        empService.updateEmp(emp);
        return Result.success();
    }

}
