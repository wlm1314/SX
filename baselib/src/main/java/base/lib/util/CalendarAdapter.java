package base.lib.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import base.lib.R;

/**
 * 日期选择适配器
 * Created by Administrator on 2017/10/12.
 */
public class CalendarAdapter extends BaseAdapter {
    /*
   适配器
    */
    private List<Map<String, Object>> datas;
    protected int last;
    protected int next;
    protected int selectedPosition[]=new int[3];// 默认选中的位置
    private int year=-1;
    private int month=-1;
    private Context context;

    public CalendarAdapter(Context context, List<Map<String, Object>> datas, int lastMonth, int nextMonth, int[] position) {
//            int lastMonth,int dayOfMonth,int nextMonth
        this.datas = datas;
//            Log.v("适配器数据是：", "data"+datas);
//            Log.v("Tag_Adapter", "datas" + datas);
        this.last=lastMonth;
        this.next=nextMonth;
        this.context=context;
        for (int i=0;i<position.length;i++)
        {
            selectedPosition[i]=position[i];
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = View.inflate(context, R.layout.item_gridview_select_month, null);
        final TextView textView = ViewHolderUtils.get(view, R.id.tv_item_select_month_dialog);
        textView.setText((String) datas.get(position).get("Data"));
        if (position<last||position>(datas.size()-next-1)) {
            textView.setTextColor(0x7F593442);
        }
        else
        if (year==-1&&month==-1)
        {
            if (position==last+selectedPosition[2]-1)
            {
                view.setBackgroundResource(R.drawable.circle_item_gv_select_month_pressed);
                textView.setTextColor(0xffffffff);
            }
        }else
        if (year==selectedPosition[0]&&month==selectedPosition[1])
        {
            if (position==last+selectedPosition[2]-1)
            {
                view.setBackgroundResource(R.drawable.circle_item_gv_select_month_pressed);
                textView.setTextColor(0xffffffff);
            }
        }
        return view;
    }


    public void setItems(List datas, int lastMonth, int nextMonth, int[] position)
    {
        this.datas.clear();
        this.datas=datas;
        this.last=lastMonth;
        this.next=nextMonth;

        for (int i=0;i<position.length;i++)
        {
            selectedPosition[i]=position[i];
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void getYearAndMonth(int year,int month)
    {
        this.year=year;
        this.month=month;
    }
}
