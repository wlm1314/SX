package com.edu.sxue.module.user.profile.viewmodel;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.ObservableField;
import android.net.Uri;
import android.provider.MediaStore;

import com.edu.sxue.api.HttpConsts;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.user.main.model.UploadPic;
import com.edu.sxue.module.user.profile.model.UserProfileBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.edu.sxue.utils.Utils;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;

import base.lib.util.ActivityManager;
import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class UserProfileViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private UserProfileBean mUserProfileBean;
    public ObservableField<String> imgUrl = new ObservableField<>(PreferencesUtils.getString(Constants.sUser_pic, ""));
    private File tempFile;

    public UserProfileViewModel(RequestApi requestApi, RxBus rxBus) {
        mRequestApi = requestApi;
        mRxBus = rxBus;
        mRequestApi.getUserProfile(HttpParams.getInfoParam(PreferencesUtils.getString(Constants.sUser_userid, "1")))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ArrayList<UserProfileBean>>() {
                    @Override
                    public void onNext(ArrayList<UserProfileBean> userProfileBeen) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        mUserProfileBean = userProfileBeen.get(0);
                        imgUrl.set(HttpConsts.getServer() + "/smartmalls" + mUserProfileBean.pic);
                    }
                });
    }

    public void updateUserProfile(String id, String pic, String name, String sex, String birthdate, String health, String address, String weight, String phone, String school){
        mRequestApi.updateUserProfile(HttpParams.updateProfile(id, pic, name, sex, birthdate, health, address, weight, phone, school))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        if (httpResult.isSuccess()) {
                            ToastUtils.showToast("修改成功");
                            ActivityManager.getActivity().finish();
                        }else
                            ToastUtils.showToast(httpResult.getInfo());
                    }
                });
    }

    public UserProfileBean getUserProfileBean() {
        return mUserProfileBean;
    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, throwable -> Logger.e(throwable.toString()));
        mRxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }

    public ReplyCommand uploadImg = new ReplyCommand(() -> {
        final String[] stringItems = {"拍照", "选择照片"};
        final ActionSheetDialog dialog = new ActionSheetDialog(ActivityManager.getActivity(), stringItems, null);
        dialog.isTitleShow(false).show();

        dialog.setOnOperItemClickL((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    tempFile = Utils.getCameraFile();
                    Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                    intentCamera.putExtra("output", Uri.fromFile(tempFile));
                    ActivityManager.getActivity().startActivityForResult(intentCamera, 1005);
                    break;
                case 1:
                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    ActivityManager.getActivity().startActivityForResult(intent, 1006);
                    break;
            }
            dialog.dismiss();
        });

    });

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1005:
                uploadImage();
                break;
            case 1006:
                String filePath = null;
                Uri selectedImage = data.getData();
                if (null != selectedImage) {
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = ActivityManager.getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        filePath = cursor.getString(columnIndex);
                        cursor.close();
                        if (filePath == null) {
                            ToastUtils.showToast("不支持网络图片,请从本地选择!");
                        }
                    }
                } else {
                    filePath = data.getAction().replace("file://", "");
                }
                if (null != filePath) {
                    tempFile = new File(filePath);
                    uploadImage();
                }
                break;
        }
    }

    private void uploadImage() {
        mRequestApi.uploadPic(HttpParams.uploadPic(PreferencesUtils.getString(Constants.sUser_userid, ""), tempFile))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<UploadPic>() {
                    @Override
                    public void onNext(UploadPic uploadPic) {
                        imgUrl.set(HttpConsts.getServer() + "/smartmalls" + uploadPic.url);
                        PreferencesUtils.saveString(Constants.sUser_pic, HttpConsts.getServer() + "/smartmalls" + uploadPic.url);
                    }
                });
    }

}