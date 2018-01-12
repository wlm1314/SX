package com.edu.sxue.module.lesson.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/28.
 */
public class TeacherBean {
    public String id;
    public String name;
    public String content;
    public String institution_id;
    public ArrayList<Teacher> teacher;

    public static class Teacher{
        public String id;
        public String name;
        public String pic;
        public String remarks;
    }
}
