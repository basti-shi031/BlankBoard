package basti.coryphaei.com.blankboard;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Bowen on 2015-11-05.
 */
public class PaintTypeDialog extends DialogFragment {

    private LayoutInflater inflater;
    private View view;
    private GridView mTypeSelector;
    private PaintTypeSelectAdapter mAdapter;
    private PaintTypeSelect mListener;

    public interface PaintTypeSelect{
        void onSelectType(PaintType type);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.typedialog, null, false);

        initView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setTitle(getResources().getString(R.string.color_selector));

        initDates();

        return builder.create();
    }

    private void initDates() {
        mAdapter = new PaintTypeSelectAdapter(getActivity(), PaintUtils.getPaintType());
        mTypeSelector.setAdapter(mAdapter);
    }

    private void initView(View view) {

        mTypeSelector = (GridView) view.findViewById(R.id.type_grid);

        mTypeSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null)
                    mListener.onSelectType(PaintUtils.getPaintType().get(position));

                dismiss();
            }
        });
    }

    public void setOnTypeSelectListener(PaintTypeSelect listener){
        mListener = listener;
    }
}
