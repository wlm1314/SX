package com.edu.sxue.rxbus.event;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 王少岩 在 2017/3/22 创建了它
 */

public class CommonEvent {
    public static final int FLAG_COMPLETE = 1001;
    public static final int FLAG_NOCOMPLETE = 1002;
    public static final int FLAG_LESSON = 1003;
    public static final int FLAG_KECHENGBAO = 1004;
    public static final int FLAG_JIGOUJIESHAO = 1005;
    public static final int FLAG_JIGOUKECHENG = 1006;
    public static final int FLAG_SHIZILILIANG = 1007;
    public static final int FLAG_LESSONHONOR = 1008;
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({FLAG_COMPLETE, FLAG_NOCOMPLETE,FLAG_LESSON,FLAG_KECHENGBAO,FLAG_JIGOUJIESHAO,FLAG_JIGOUKECHENG,FLAG_SHIZILILIANG,FLAG_LESSONHONOR})
    public @interface EventType {
    }

    public int eventType;

    public CommonEvent(@EventType int eventType) {
        this.eventType = eventType;
    }

}
