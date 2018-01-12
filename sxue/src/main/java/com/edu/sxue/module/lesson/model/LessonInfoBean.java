package com.edu.sxue.module.lesson.model;

import java.io.Serializable;

/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class LessonInfoBean implements Serializable {
    public String id;//	int(11)		主键
    public String name;//	varchar	是	课程名
    public String content;//	text		课程特色
    public String institution_id;//	tinyint	是	机构id
    public String institution_name;//	tinyint	是	机构名
    public String url;
}
