package basti.coryphaei.com.blankboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements ColorDialog.ColorDialogSelect, PaintTypeDialog.PaintTypeSelect, EraserDialog.EraserSelect {

    private Toolbar mToolbar;
    private BlankView mBlankView;
    private ColorDialog mColorDialog;
    private PaintTypeDialog mTypeDialog;
    private EraserDialog mEraserDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.title));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);

        mBlankView = (BlankView) findViewById(R.id.blankview);

        mColorDialog = new ColorDialog();
        mColorDialog.setOnColorSelectListener(this);

        mTypeDialog = new PaintTypeDialog();
        mTypeDialog.setOnTypeSelectListener(this);

        mEraserDialog = new EraserDialog();
        mEraserDialog.setOnTypeSelectListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.color:
                mColorDialog.show(getFragmentManager(),"color");
                break;
            case R.id.stroke:
                mTypeDialog.show(getFragmentManager(),"type");
                break;
            case R.id.save:
                try {
                    saveBitmap();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.eraser:
                mEraserDialog.show(getFragmentManager(),"eraser");
                break;
            case R.id.clear:
                mBlankView.clear();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveBitmap() throws IOException {
        if (mBlankView.save()){
            Snackbar.make(mToolbar,getResources().getString(R.string.save_success),Snackbar.LENGTH_SHORT).show();
        }else {
            Snackbar.make(mToolbar,getResources().getString(R.string.save_failure),Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSelectColor(ColorBean colorBean) {
        mBlankView.setPaintColor(colorBean.getColor());
    }

    @Override
    protected void onDestroy() {
        mBlankView.recycleBitmap();
        super.onDestroy();
    }

    @Override
    public void onSelectType(PaintType type) {
        mBlankView.setPaintType(type);
    }

    @Override
    public void onSelectEraser(EraserType type) {
        mBlankView.setEraser(type);
    }
}
