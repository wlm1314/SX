package com.edu.sxue.module.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.sxue.R;

/**
 * Created by Administrator on 2017/9/26.
 */
public class MeAdapter extends BaseAdapter {
    // context用来加载inflater
    private LayoutInflater mInflater;
    //图片数组
    private Integer[] imgs = {
            R.mipmap.idcard,
            R.mipmap.ic_sign,
            R.mipmap.ordered,
            R.mipmap.classes,
            R.mipmap.huodong,
            R.mipmap.jiaren,
            R.mipmap.shezhi
    };
    private String [] menes={"我的会员卡","我的预约报名","我的订购","我的课程表","我的活动","我的家人","设置"};

    public MeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.name);
            holder.image = (ImageView) convertView.findViewById(R.id.pic);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(menes[position]);
        holder.image.setBackgroundResource(imgs[position]);
        return convertView;
    }

    private class ViewHolder {
        TextView title;
        ImageView image;
    }
}
