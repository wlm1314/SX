package com.edu.sxue.module.lesson.model;

import java.io.Serializable;

/**
 * Administrator 在 2017/6/5 创建了它.
 */

public class LessonBean implements Serializable {
    public String id;//	int(11)	是	主键
    public String name;//	varchar	是	课程名称
    public String institution_id;//	机构id
    public String course_time;//	varchar	是	课程时长
    public String single_time_price;//	double	是	单次基准价格
    public String pic;//	varchar		课程图片
    public String institution_name;//	int		机构名

}
