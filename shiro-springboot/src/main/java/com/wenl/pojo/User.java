package com.wenl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //get set 方法
@AllArgsConstructor //全参
@NoArgsConstructor  //无参
public class User {
        private int id;
        private String name;
        private String pwd;
        private String perms;
}
