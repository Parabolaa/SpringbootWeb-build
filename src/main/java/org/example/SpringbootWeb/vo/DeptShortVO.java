package org.example.SpringbootWeb.vo;

import lombok.Data;
import org.example.SpringbootWeb.pojo.Dept;

import java.time.LocalDateTime;

@Data
public class DeptShortVO {
    private Integer id;
    private String name;
    private LocalDateTime updateTime;

    public static DeptShortVO from(Dept dept) {
        DeptShortVO deptShortVO = new DeptShortVO();
        deptShortVO.setName(dept.getName());
        deptShortVO.setId(dept.getId());
        deptShortVO.setUpdateTime(dept.getUpdateTime());
        return deptShortVO;
    }
}
