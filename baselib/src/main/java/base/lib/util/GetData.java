package base.lib.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/12.
 */
public class GetData {
    private int tYear;
    private int tMonth;
    private boolean isALeapYear;
    public int dayOfMonth;
    public int monthArray[] = null;
    public int lastMonthArray[] = null;
    public int lastMonth;
    public String week[]={"MON","TUE","WED","THU","FRI","SAT","SUN"};
    public int nextMonth;

    /*
    构造方法
     */
    public GetData(int year, int month) {
        this.tMonth = month;
        this.tYear = year;
        dayOfMonth = judgeMonth(tYear, tMonth);
        nextMonth=getNextMonthDay(tYear,tMonth,dayOfMonth);
        lastMonth=getLastMonthDay(tYear, tMonth);
        initData();
    }

    /*
    判断某年某月有多少天
     */
    public int judgeMonth(int year, int month) {
        boolean b = false;
        int dayMonth = -1;

        if (year % 400 == 0) {
            b = true;
        } else if (year % 100 == 0 && year % 4 == 0) {
            b = true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            b = true;
        } else
            b = false;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            dayMonth = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            dayMonth = 30;
        } else if (month == 2) {
            if (b) dayMonth = 29;
            else if (!b) dayMonth = 28;
        }
        return dayMonth;
    }


    public void initData() {
        lastMonthArray = new int[lastMonth];
        monthArray=new int [dayOfMonth];
        Log.v("Tag_initData","lastMonth=="+lastMonth);
        for (int i = 0; i < dayOfMonth; i++)
        {
            monthArray[i] =( i + 1);
//            Log.v("TAG","monthArray: "+monthArray[i]);
        }
        if (tMonth > 1)
        {
            int a = judgeMonth(tYear, tMonth - 1);
            for (int i = 0; i < lastMonth; i++)
            {
                lastMonthArray[lastMonth - (i+1)] = a - i;
            }
        }
        else if (tMonth == 1)
        {
            int a = judgeMonth(tYear - 1, 12);
            Log.v("Tag_ben","lastMonthDay a==" +a);
            for (int i = 0; i < lastMonth; i++)
            {
                lastMonthArray[lastMonth - i-1 ] = a - i;
//                Log.v("getDATA","initData   lastMonthArray[]"+lastMonthArray[lastMonth-i]);
            }
//            Log.v("getDATA","initData   lastMonthArray[]"+lastMonthArray);
        }
        for (int j=0;j<lastMonthArray.length;j++)
        {
            Log.v("Tag","lastMonthArray: "+lastMonthArray[j]);}
    }


    public int  getLastMonthDay(int year, int month) {
        int lastMonthDay=-1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//先指定年份
        calendar.set(Calendar.MONTH, month - 1);//再指定月份 Java月份从0开始算
//        int daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天
//获取指定年份月份中指定某天是星期几
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                lastMonthDay = 0;
                break;
            case 2:
                lastMonthDay = 1;
                break;
            case 3:
                lastMonthDay = 2;
                break;
            case 4:
                lastMonthDay = 3;
                break;
            case 5:
                lastMonthDay = 4;
                break;
            case 6:
                lastMonthDay = 5;
                break;
            case 7:
                lastMonthDay = 6;
                break;
        }
        return lastMonthDay;
    }
    public List<Map<String,Object>> getDataList()
    {
        int j=0;
        int k=0;
        List<Map<String, Object>> data=new ArrayList<Map<String,Object>>();
        Map map;
        int dataSize=lastMonthArray.length+monthArray.length+nextMonth;
        for (int i=0;i<dataSize;i++)
        { map=new HashMap();
            if (i<lastMonthArray.length)
            {
                map.put("Data",""+lastMonthArray[i]);
            }else
            if(i<(lastMonthArray.length+monthArray.length))
            {
                map.put("Data",""+monthArray[j]);
                j+=1;
            }else
            {
                k+=1;
                map.put("Data",""+k);
            }
            data.add(map);
        }
        return data;
    }

    public int  getNextMonthDay(int year,int month,int day)
    {
        int nextMonthDay=-1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//先指定年份
        calendar.set(Calendar.MONTH, month - 1);//再指定月份 Java月份从0开始算
//        int daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天
//获取指定年份月份中指定某天是星期几
        calendar.set(Calendar.DAY_OF_MONTH,day );
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek)
        {
            case 1:
                nextMonthDay = 6;
                break;
            case 2:
                nextMonthDay = 5;
                break;
            case 3:
                nextMonthDay = 4;
                break;
            case 4:
                nextMonthDay = 3;
                break;
            case 5:
                nextMonthDay = 2;
                break;
            case 6:
                nextMonthDay = 1;
                break;
            case 7:
                nextMonthDay = 0;
                break;
        }
        return nextMonthDay;
    }

}
