package org.example.SpringbootWeb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.SpringbootWeb.pojo.Emp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public Long getTotalEmp();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> getEmpList(Integer start, Integer pageSize);

//    @Select("select * from emp")
    List<Emp> findAll(String name, Short gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);

//    public Emp search(Integer page, Integer pageSize,String name, Short gender,
//                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);

    void deleteEmp(List<Integer> ids);
    void addEmp(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getLoginInfo(Emp emp);

    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);
}
