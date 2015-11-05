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
public class ColorDialog extends DialogFragment {

    private LayoutInflater inflater;
    private View view;
    private GridView mColorSelector;
    private ColorSelectAdapter mAdapter;
    private ColorDialogSelect mListener;

    public interface ColorDialogSelect{
        void onSelectColor(ColorBean colorBean);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.colordialog, null, false);

        initView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setTitle(getResources().getString(R.string.color_selector));

        initDates();

        return builder.create();
    }

    private void initDates() {
        mAdapter = new ColorSelectAdapter(getActivity(), PaintUtils.getColors());
        mColorSelector.setAdapter(mAdapter);
    }

    private void initView(View view) {

        mColorSelector = (GridView) view.findViewById(R.id.color_grid);

        mColorSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null)
                mListener.onSelectColor(PaintUtils.getColors().get(position));

                dismiss();
            }
        });
    }

    public void setOnColorSelectListener(ColorDialogSelect listener){
        mListener = listener;
    }

}
