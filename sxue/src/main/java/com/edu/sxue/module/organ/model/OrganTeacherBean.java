package com.edu.sxue.module.organ.model;

import java.util.ArrayList;

/**
 * 王少岩 在 2017/10/16 创建了它
 */

public class OrganTeacherBean {
    public String id;
    public String name;
    public ArrayList<Teacher> teacher;

    public static class Teacher{
        public String id;
        public String name;
        public String remarks;
        public String pic;
    }
}
