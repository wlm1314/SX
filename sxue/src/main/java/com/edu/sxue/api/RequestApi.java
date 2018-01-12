package com.edu.sxue.api;


/**
 * Created by 王少岩 on 2016/11/9.
 */

import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.module.classes.model.ClassBean;
import com.edu.sxue.module.exercise.model.ExerciseBean;
import com.edu.sxue.module.exercise.model.ExerciseInfoBean;
import com.edu.sxue.module.exercise.model.ExerciseSignBean;
import com.edu.sxue.module.lesson.model.CourseCardBean;
import com.edu.sxue.module.lesson.model.IntroduceBean;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.model.LessonHonorBean;
import com.edu.sxue.module.lesson.model.LessonInfoBean;
import com.edu.sxue.module.lesson.model.LessonOrderBean;
import com.edu.sxue.module.lesson.model.LessonTryBean;
import com.edu.sxue.module.lesson.model.TeacherBean;
import com.edu.sxue.module.organ.model.LessonsBean;
import com.edu.sxue.module.organ.model.OrganBean;
import com.edu.sxue.module.organ.model.OrganCertBean;
import com.edu.sxue.module.organ.model.OrganInfoBean;
import com.edu.sxue.module.organ.model.OrganJghjBean;
import com.edu.sxue.module.organ.model.OrganTeacherBean;
import com.edu.sxue.module.organ.model.OrgenDesBean;
import com.edu.sxue.module.user.exercise.model.UserExerciseBean;
import com.edu.sxue.module.user.main.model.UserInfoBean;
import com.edu.sxue.module.user.order.model.UserOrderBean;
import com.edu.sxue.module.user.parent.model.UserParentBean;
import com.edu.sxue.module.user.profile.model.UserProfileBean;
import com.edu.sxue.module.user.schedule.model.UserScheduleBean;
import com.edu.sxue.module.user.signorder.model.SignOrderBean;
import com.edu.sxue.module.user.vip.model.ConsumeBean;
import com.edu.sxue.module.user.vip.model.VipCardBean;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * retrofit 以注解的方式访问接口
 * <p>
 * post请求方式如下
 *
 * @FormUrlEncoded
 * @POST(url) Observable<T> method(@FieldMap Map<String, String> params);
 */
public interface RequestApi {

    @FormUrlEncoded
    @POST(HttpPath.lesson_index)
    Observable<HttpResult<ArrayList<LessonBean>>> getLessonIndex(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.organ_index)
    Observable<HttpResult<ArrayList<OrganBean>>> getOrganIndex(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.lesson_info)
    Observable<HttpResult<ArrayList<LessonInfoBean>>> getLessonInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.organ_info)
    Observable<HttpResult<ArrayList<OrganInfoBean>>> getOrganInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_info)
    Observable<HttpResult<ArrayList<UserInfoBean>>> getUserInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.sign_order)
    Observable<HttpResult<ArrayList<SignOrderBean>>> getSignOrder(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_order)
    Observable<HttpResult<ArrayList<UserOrderBean>>> getUserOrder(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_lesson)
    Observable<HttpResult<ArrayList<UserScheduleBean>>> getUserSchedule(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.exercise_index)
    Observable<HttpResult<ArrayList<ExerciseBean>>> getExerciseIndex(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.exercise_info)
    Observable<HttpResult<ExerciseInfoBean>> getExerciseInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.exercise_sign)
    Observable<HttpResult<ExerciseSignBean>> exerciseSign(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_exercise)
    Observable<HttpResult<ArrayList<UserExerciseBean>>> getUserExercise(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_parent)
    Observable<HttpResult<ArrayList<UserParentBean>>> getUserParent(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_profile)
    Observable<HttpResult<ArrayList<UserProfileBean>>> getUserProfile(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_profile_edit)
    Observable<HttpResult> updateUserProfile(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.lesson_order)
    Observable<HttpResult<ArrayList<LessonOrderBean>>> lessonOrder(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.lesson_try)
    Observable<HttpResult<ArrayList<LessonTryBean>>> lessonTry(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_login)
    Observable<HttpResult<ArrayList<UserInfoBean>>> userLogin(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_pwd_update)
    Observable<HttpResult> updatePwd(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.app_update)
    Observable<HttpResult> resetPwd(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.user_balance)
    Observable<HttpResult<VipCardBean>> getBalance(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.app_consume)
    Observable<HttpResult<ArrayList<ConsumeBean>>> getConsume(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.organ_lesson)
    Observable<HttpResult<ArrayList<LessonBean>>> getOrganLesson(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.phone_code)
    Observable<HttpResult> getPhoneCode(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.course_content)
    Observable<HttpResult<IntroduceBean>> getCourseContent(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.course_card)
    Observable<HttpResult<CourseCardBean>> getCourseCard(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.course_teacher)
    Observable<HttpResult<TeacherBean>> getCourseTeacher(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.organ__inst_content)
    Observable<HttpResult<OrgenDesBean>> getOrganInstContent(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.organ__app_course_api)
    Observable<HttpResult<ArrayList<LessonsBean>>> getOrganAppCourseApi(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.lesson__app_order)
    Observable<HttpResult> getLesonOrderAppCourseApi(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.course_honor)
    Observable<HttpResult<LessonHonorBean>> getCourseHonor(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.lesson__indrodece)
    Observable<HttpResult<LessonInfoBean>> getLessonIndroce(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.organ_envoronment)
    Observable<HttpResult<OrganJghjBean>> getOrgan_envoronment(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.inst_teacher)
    Observable<HttpResult<OrganTeacherBean>> getInst_teacher(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.inst_certificate)
    Observable<HttpResult<OrganCertBean>> getInst_certificate(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.getCoursePlanList)
    Observable<HttpResult<ArrayList<ClassBean>>> getCoursePlanList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.CoursePlanList)
    Observable<HttpResult<ArrayList<ClassBean>>> CoursePlanList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.addReserve)
    Observable<HttpResult> addReserve(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(HttpPath.delReserve)
    Observable<HttpResult> delReserve(@FieldMap Map<String, String> params);
}
