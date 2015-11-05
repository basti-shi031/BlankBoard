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
public class EraserDialog extends DialogFragment {

    private LayoutInflater inflater;
    private View view;
    private GridView mEraserSelector;
    private EraserSelectAdapter mAdapter;
    private EraserSelect mListener;

    public interface EraserSelect{
        void onSelectEraser(EraserType type);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.typedialog, null, false);

        initView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setTitle(getResources().getString(R.string.eraser_selector));

        initDates();

        return builder.create();
    }

    private void initDates() {
        mAdapter = new EraserSelectAdapter(getActivity(), PaintUtils.getEraserPaint());
        mEraserSelector.setAdapter(mAdapter);
    }

    private void initView(View view) {

        mEraserSelector = (GridView) view.findViewById(R.id.type_grid);

        mEraserSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null)
                    mListener.onSelectEraser(PaintUtils.getEraserPaint().get(position));

                dismiss();
            }
        });
    }

    public void setOnTypeSelectListener(EraserSelect listener){
        mListener = listener;
    }

}
