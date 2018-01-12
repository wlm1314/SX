package base.lib.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import base.lib.R;

/**
 * 日期选择类
 * Created by Administrator on 2017/10/12.
 */
public class CalendarUtil {
    private GridView gridView;
    private CalendarAdapter adapter;

    private RelativeLayout rl_left;
    private RelativeLayout rl_right;
    private TextView tv_year;

    private static int tMonth;
    private static int tYear;
    private static int tDay;
    private Activity activity;
    private AlertDialog ad;
    private int lastMonth;
    private int dayOfMonth;
    private int nextMonth;
    private int selectedPosition[]=new int[3];
    private List<Map<String, Object>> data_list;

    public boolean isSelect=false;

    String englishMonth[] = {"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12"};
    private IDateInterface iDateInterface;
    //            = new IDateInterface() {
//        @Override
//        public void onDateOnClickListerner() {
//            Log.i("接口回调",">>>>>>>>>>>");
//        }
//    };
    public CalendarUtil(Activity activity, String date, IDateInterface iDateInterface)
    {
        Log.v("CalendarUtil"," String "+date);
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
//        Log.v("Tag_month","month month"+month);
        this.activity = activity;
        this.tYear = year;
        this.tMonth = month;
        this.tDay=day;
        getSelectedPosition();
        this.iDateInterface=iDateInterface;
    }


    public AlertDialog setCalendarDialog(final TextView textView)
    {
        LinearLayout linearLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.dialog_calendar, null);

        rl_left = (RelativeLayout) linearLayout.findViewById(R.id.view_btn_left_select_month_dialog);
        rl_right = (RelativeLayout) linearLayout.findViewById(R.id.view_btn_right_select_month_dialog);
        tv_year = (TextView) linearLayout.findViewById(R.id.tv_year_select_month_dialog);

        gridView = (GridView) linearLayout.findViewById(R.id.gv_select_month_dialog);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        tv_year.setText( "" + tYear+"-"+englishMonth[tMonth - 1]);
        data_list=new ArrayList<Map<String, Object>>();
        getOriginData(tYear,tMonth);
        adapter = new CalendarAdapter(activity,data_list,lastMonth,nextMonth,selectedPosition);
        gridView.setAdapter(adapter);
        ad = new AlertDialog.Builder(activity)
                .setView(linearLayout)
                .show();
        initClick();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams lp = ad.getWindow().getAttributes();
        lp.height = lp.WRAP_CONTENT; // 高度设置为包裹内容
        lp.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.75
        lp.y = 300;
        lp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        ad.getWindow().setAttributes(lp);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                //adapter.notifyDataSetChanged();
                if (position<lastMonth||position>(lastMonth+dayOfMonth-1) ){return;}
                else
                {
                    for (int i = lastMonth; i < lastMonth + dayOfMonth ; i++)
                    {
                        if (i == position)
                        {
                            view.setBackgroundResource(R.drawable.circle_item_gv_select_month_pressed);
                            TextView textView = ViewHolderUtils.get(view, R.id.tv_item_select_month_dialog);
                            textView.setTextColor(0xffffffff);
                        }
                        else
                        {
                            View viewaa = gridView.getChildAt(i);
                            viewaa.setBackgroundResource(R.drawable.circle_item_gv_select_month_normal);
                            TextView textView = ViewHolderUtils.get(viewaa, R.id.tv_item_select_month_dialog);
                            textView.setTextColor(0xff593442);
                        }
                    }
                }
                tDay = position - lastMonth + 1;
                getSelectedPosition();

                getDateData();
                setTextForTextView(textView);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Log.i(">>>>>>>>",""+tDay);
                        isSelect=true;
                        iDateInterface.onDateOnClickListerner();
                        ad.dismiss();
                    }
                }, 800);
            }
        });
        return ad;
    }

    protected void initClick() {
        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tMonth>1) {
                    tMonth -= 1;
//                    tv_year.setText(englishMonth[tMonth - 1] + "," + tYear);
                    tv_year.setText( "" + tYear+"-"+englishMonth[tMonth - 1]);
                    getOriginData(tYear, tMonth);
//                    Log.i("传进去之前", "" + data_list);
//                    adapter.setItems(data_list);
                    adapter.getYearAndMonth(tYear,tMonth);
                    adapter.setItems(data_list,lastMonth,nextMonth,selectedPosition);
                }else
                {
                    tYear-=1;
                    tMonth=12;
//                    tv_year.setText(englishMonth[tMonth - 1] + "," + tYear);
                    tv_year.setText( "" + tYear+"-"+englishMonth[tMonth - 1]);
                    getOriginData(tYear, tMonth);
//                    Log.i("传进去之前", "" + data_list);
                    adapter.getYearAndMonth(tYear,tMonth);
                    adapter.setItems(data_list,lastMonth,nextMonth,selectedPosition);
                }
            }
        });

        rl_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tMonth<12) {
                    tMonth += 1;
                    tv_year.setText( "" + tYear+"-"+englishMonth[tMonth - 1]);
                    getOriginData(tYear, tMonth);
//                    Log.v("右边》》》", "" + data_list);
                    adapter.getYearAndMonth(tYear, tMonth);
                    adapter.setItems(data_list, lastMonth,nextMonth,selectedPosition);
                }
                else
                {
                    tYear+=1;
                    tMonth=1;
                    tv_year.setText( "" + tYear+"-"+englishMonth[tMonth - 1]);
                    getOriginData(tYear,tMonth);
                    adapter.getYearAndMonth(tYear,tMonth);
                    adapter.setItems(data_list,lastMonth,nextMonth,selectedPosition);
                }
            }
        });
    }

    public void getOriginData(int year,int month)
    {
        if (data_list!=null)
        {
            data_list.clear();
        }
        GetData getData=new GetData(year,month);
        lastMonth=getData.lastMonth;
        dayOfMonth=getData.dayOfMonth;
        nextMonth=getData.nextMonth;
        data_list=getData.getDataList();
//        Log.v("Tag_dataList", "" + data_list);
//        return  data_list;
    }

    private void setTextForTextView(TextView textView)
    {
        if (tMonth>=10&&tDay>=10) {
            textView.setText(tYear + "-" + tMonth + "-" + tDay);
        }
        else
        if (tMonth<10&&tDay>=10){
            textView.setText(tYear + "-" + "0"+tMonth+"-"+tDay);
        }
        else if (tMonth>=10&&tDay<10){
            textView.setText(tYear + "-" + tMonth+"-"+"0"+tDay);
        }
        else
        {
            textView.setText(tYear + "-" + "0"+tMonth+"-"+"0"+tDay);
        }
    }

    public void getSelectedPosition() {
        selectedPosition[0]=tYear;
        selectedPosition[1]=tMonth;
        selectedPosition[2]=tDay;
    }
    public void setOnDateListener(IDateInterface iDateInterface){
        this.iDateInterface = iDateInterface;
    }
    public static String getDateData()
    {
        String str=tYear+"-"+tMonth+"-"+tDay;
        return str;
    }
}
