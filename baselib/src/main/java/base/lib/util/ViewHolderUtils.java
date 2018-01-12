package base.lib.util;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by Administrator on 2017/10/12.
 */
public class ViewHolderUtils {
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        //谷歌建议使用 SparseArray<Integer,Object>代替HashMap<Integer,Object>
//        HashMap<Integer,View> holderView= (HashMap<Integer, View>) view.getTag();
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
