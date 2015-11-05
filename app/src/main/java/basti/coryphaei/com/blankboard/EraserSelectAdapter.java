package basti.coryphaei.com.blankboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Bowen on 2015-11-05.
 */
public class EraserSelectAdapter extends BaseAdapter {

    private List<EraserType> mList;
    private Context mContext;

    public EraserSelectAdapter(Context context,List<EraserType> list){
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_eraser,parent,false);

            viewHolder = new ViewHolder();

            viewHolder.eraser = (ImageView) convertView.findViewById(R.id.eraser);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.eraser.setImageResource(mList.get(position).getImgId());

        return convertView;
    }

    class ViewHolder{
        private ImageView eraser;
    }
}
