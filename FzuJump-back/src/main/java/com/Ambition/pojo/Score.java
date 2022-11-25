package com.Ambition.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private int id;
    private String userCode;
    private String userName;
    private int userRole;
    private int JumpFrequency;
    private int ItemNumber;
    private Date modifyDate;

    private String rolename;
}
