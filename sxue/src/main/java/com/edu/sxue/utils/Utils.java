package com.edu.sxue.utils;

import android.os.Environment;

import java.io.File;

import base.lib.util.ActivityManager;

/**
 * Administrator 在 2018/1/14 创建了它.
 */

public class Utils {
    /**
     * SD卡中自己拍照照片的存储路径
     */
    private static final File SD_CAMERA_DIR = new File(Environment.getExternalStorageDirectory(), "DCIM/Camera");
    private static final File CACHE_DIR = ActivityManager.getActivity().getCacheDir();
    private static final String PATH_IMAGE_CAMERA = "Camera";

    /**
     * 获取拍摄照片的存储路径
     */
    public static File getCameraFile() {
        File filePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filePath = SD_CAMERA_DIR;
        } else {
            filePath = new File(CACHE_DIR, PATH_IMAGE_CAMERA);
        }
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String tempFileName = System.currentTimeMillis() + ".jpg";
        return new File(filePath, tempFileName);
    }
}
