package org.example.SpringbootWeb.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.SpringbootWeb.anno.Log;
import org.example.SpringbootWeb.pojo.Dept;
import org.example.SpringbootWeb.pojo.Result;

import org.example.SpringbootWeb.service.DeptService;
import org.example.SpringbootWeb.vo.DeptShortVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

//    private static Logger logger = LoggerFactory.getLogger(DeptController.class);

//    @RequestMapping(value = "/depts", method= RequestMethod.GET) //指定请求方法

    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<DeptShortVO> deptShortList = deptService.list();
        return Result.success(deptShortList);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("查询待编辑的部分id>{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门数据: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("添加了数据 其name为: {}", dept);
        deptService.insert(dept);
        return Result.success();
    }

//    @PutMapping("/{id}")
//    public Result update(@PathVariable Integer id, @RequestBody Dept dept){
//        log.info("根据id更新部门数据: {}", id);
//        dept.setId(id);
//        deptService.updateById(dept);
//        Dept updated = deptService.getById(id);
//        return Result.success(updated);
//    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        deptService.updateById(dept);
        return Result.success();
    }
}
