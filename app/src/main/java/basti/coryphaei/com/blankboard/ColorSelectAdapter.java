package basti.coryphaei.com.blankboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bowen on 2015-11-05.
 */
public class ColorSelectAdapter extends BaseAdapter {

    private Context mContext;
    private List<ColorBean> mColorBeans;

    public ColorSelectAdapter(Context context,List<ColorBean> colorBeans){
        mContext = context;
        mColorBeans = colorBeans;
    }

    @Override
    public int getCount() {
        return mColorBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mColorBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder ;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_color,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.colorView = (ImageView) convertView.findViewById(R.id.color);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*viewHolder.colorView.setBackgroundTintMode(PorterDuff.Mode.ADD);
        viewHolder.colorView.setBackgroundTintList(mContext.getResources().getColorStateList(mColorBeans.get(position).getColor()));*/

        viewHolder.colorView.setColorFilter(mContext.getResources().getColor(mColorBeans.get(position).getColor()));


        return convertView;
    }

    class ViewHolder{
        private ImageView colorView;
    }
}
