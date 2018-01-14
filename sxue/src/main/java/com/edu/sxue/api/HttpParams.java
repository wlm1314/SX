package com.edu.sxue.api;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by 王少岩 on 2016/9/14.
 */
public class HttpParams {

    /**
     * @param keyword
     * @param page
     * @return
     */
    public static Map<String, String> getIndexParam(String keyword, String page) {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("page", page);
        map.put("pagesize", "10");
        return map;
    }

    /**
     * @param id
     * @return
     */
    public static Map<String, String> getInfoParam(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return map;
    }
    public static Map<String, String> getMemberIdParam(String member_id) {
        Map<String, String> map = new HashMap<>();
        map.put("member_id", member_id);
        return map;
    }

    public static Map<String, String> getLessonParam(String course_id, String institution_id, String member_id) {
        Map<String, String> map = new HashMap<>();
        map.put("course_id", course_id);
        map.put("institution_id", institution_id);
        map.put("member_id", member_id);
        return map;
    }

    public static Map<String, String> lessonTry(String course_id, String room_reserve_id, String institution_id, String member_id) {
        Map<String, String> map = new HashMap<>();
        map.put("room_reserve_id", room_reserve_id);
        map.put("course_id", course_id);
        map.put("institution_id", institution_id);
        map.put("member_id", member_id);
        return map;
    }

    public static Map<String, String> getExerciseParam(String activity_id, String institution_id, String member_id) {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("institution_id", institution_id);
        map.put("member_id", member_id);
        return map;
    }

    /**
     * 预约报名
     *
     * @param id
     * @return
     */
    public static Map<String, String> getPageParam(String id, String page) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("page", page);
        map.put("pagesize", "10");
        return map;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public static Map<String, String> userLogin(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return map;
    }

    /**
     * 修改密码
     * @param password_new
     * @param password_old
     * @return
     */
    public static Map<String, String> updatePwd(String id, String password_new, String password_old) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("password_new", password_new);
        map.put("password_old", password_old);
        return map;
    }

    public static Map<String, String> resetPwd(String id, String password_new) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("password_new", password_new);
        return map;
    }

    /**
     * 修改个人信息
     * @param id
     * @param pic
     * @param name
     * @param sex
     * @param birthdate
     * @param health
     * @param address
     * @param weight
     * @param phone
     * @param school
     * @return
     */
    public static Map<String, String> updateProfile(String id, String pic, String name, String sex, String birthdate, String health, String address, String weight, String phone, String school) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pic", pic);
        map.put("name", name);
        map.put("sex", sex);
        map.put("birthdate", birthdate);
        map.put("health", health);
        map.put("address", address);
        map.put("weight", weight);
        map.put("phone", phone);
        map.put("school", school);
        return map;
    }

    public static Map<String, String> getPageParam(String id, String page, String keyword){
        Map<String, String> map = new HashMap<>();
        map.put("member_id", id);
        map.put("page", page);
        map.put("keyword", keyword);
        map.put("pagesize", "10");
        return map;
    }

    public static Map<String, String> getLessonListParam(String id, String page, String courseId){
        Map<String, String> map = new HashMap<>();
        map.put("member_id", id);
        map.put("page", page);
        map.put("course_id", courseId);
        map.put("pagesize", "10");
        return map;
    }

    public static Map<String, String> getCheckCode(String phone){
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        return map;
    }
    public static Map<String, String> getLesonOrderAppCourseApi(String id,String institution_id,String card_id,String member_id){
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("institution_id",institution_id);
        map.put("card_id",card_id);
        map.put("member_id",member_id);
        return map;
    }
    public static Map<String, String> getSchedule(String id, String page, String keyword,String time){
        Map<String, String> map = new HashMap<>();
        map.put("member_id", id);
        map.put("page", page);
        map.put("keyword", keyword);
        map.put("pagesize", "10");
        map.put("time",time);
        return map;
    }

    public static Map<String, RequestBody> uploadPic(String memberId, File uploadfile){
        RequestBody member_id = RequestBody.create(MediaType.parse("text/plain"), memberId);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("member_id", member_id);

        if (uploadfile != null) {
            RequestBody fileBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), uploadfile);
            map.put("uploadfile\"; filename=\""+uploadfile.getName()+"", fileBody);
        }
        return map;
    }

    public static Map<String, String> addReserve(String course_id, String institution_id, String member_id, String start, String end) {
        Map<String, String> map = new HashMap<>();
        map.put("course_id", course_id);
        map.put("institution_id", institution_id);
        map.put("member_id", member_id);
        map.put("start_time", start);
        map.put("end_time", end);
        return map;
    }

    public static Map<String, String> getPageMemberParam(String member_id, String page){
        Map<String, String> map = new HashMap<>();
        map.put("member_id", member_id);
        map.put("page", page);
        map.put("pagesize", "10");
        return map;
    }
}
