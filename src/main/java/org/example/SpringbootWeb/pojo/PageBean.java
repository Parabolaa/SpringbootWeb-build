package org.example.SpringbootWeb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private long total; //总记录数字
    private List<Emp> rows; //结果列表
}
