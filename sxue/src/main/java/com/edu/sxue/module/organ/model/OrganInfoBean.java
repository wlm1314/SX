package com.edu.sxue.module.organ.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class OrganInfoBean implements Serializable{
    public String id;//	int
    public String logo;//	varchar	是	机构图片
    public String name;//	varchar		机构名称
    public String shop_id;//	int		商铺位置
    public String web_site;//	varchar		机构网址
    public String about;//	text		机构简介
    public String certificate_img;//	text		行业资质证书（可放多张）
    public String number;//	int		机构编号
    public String imgs;//	text		机构环境
    public ArrayList<Teacher> teacher;

    public static class Teacher{
        public String name;//	int		（教师风采）教师
        public String imgs;//	text		（教师风采）老师相片
        public String remarks;//	text		（教师风采）老师相片
    }

}
