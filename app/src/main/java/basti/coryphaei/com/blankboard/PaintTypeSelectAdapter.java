package basti.coryphaei.com.blankboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bowen on 2015-11-05.
 */
public class PaintTypeSelectAdapter extends BaseAdapter{

    private Context mContext;
    private List<PaintType> mList;

    public PaintTypeSelectAdapter(Context context,List<PaintType> list){
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

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_type,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.type = (TextView) convertView.findViewById(R.id.painttype);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.type.setText(PaintUtils.getPaintType().get(position).getPaintName());

        return convertView;

    }


    class ViewHolder{
        private TextView type;
    }
}
