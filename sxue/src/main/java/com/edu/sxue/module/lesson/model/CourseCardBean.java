package com.edu.sxue.module.lesson.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/28.
 */
public class CourseCardBean {
    public String id;
    public String name;
    public ArrayList<CourseCard> card_bag;

    public static class CourseCard {
        public String id;
        public String course_id;
        public String name;
        public String price_typeid;
        public String validity;
        public String validity_ttimes;
        public String course_price;
        public String quantity;
        public String gifts;
    }
}
