package org.example.SpringbootWeb.service.impl;

import org.example.SpringbootWeb.mapper.DeptMapper;
import org.example.SpringbootWeb.mapper.EmpMapper;
import org.example.SpringbootWeb.pojo.Dept;
import org.example.SpringbootWeb.service.DeptService;
import org.example.SpringbootWeb.vo.DeptShortVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<DeptShortVO> list() {
        List<Dept> deptList = deptMapper.list();
        return deptList.stream()
                .map(DeptShortVO::from)
                .toList();

//        return deptMapper.list();
//        return deptList.stream().map(dept -> {
//            DeptShortVO deptShortVO = new DeptShortVO();
//            deptShortVO.setName(dept.getName());
//            deptShortVO.setId(dept.getId());
//            return deptShortVO;
//        }).collect(Collectors.toList());

    }

    @Transactional(rollbackFor = Exception.class) // 交给spring进行事务管理
    public void deleteById(Integer id) {
        // 需要进行事务控制
        // 如果在中间抛出了不是runtime异常，是不会rollback的
        // 比如1/0，是算数异常-runtime异常
        // 需要设置可回滚的异常类
        deptMapper.deleteById(id);
        empMapper.deleteByDeptId(id); // 删除部门下的员工
    }

    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    public void updateById(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }

}
