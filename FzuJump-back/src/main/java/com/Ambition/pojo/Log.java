package com.Ambition.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log{
    private int id;
    private String message;
    private Date createTime;

    private String time;
}
