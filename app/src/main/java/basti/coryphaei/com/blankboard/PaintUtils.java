package basti.coryphaei.com.blankboard;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bowen on 2015-11-05.
 */
public class PaintUtils {

    private static List<ColorBean> mColorList;


    public static final List<ColorBean> getColors(){

        if (mColorList == null){
            mColorList = new ArrayList<>();
            mColorList.add(new ColorBean("黑", R.color.black));
            mColorList.add(new ColorBean("红", R.color.red));
            mColorList.add(new ColorBean("绿", R.color.green));
            mColorList.add(new ColorBean("蓝", R.color.blue));
            mColorList.add(new ColorBean("灰",R.color.gray));
        }

        return mColorList;
    }

    public static List<PaintType> mPaintList;

    public static final List<PaintType> getPaintType(){
        if (mPaintList == null){
            mPaintList = new ArrayList<>();

            mPaintList.add(new PaintType(5,"铅笔",0));
            mPaintList.add(new PaintType(20,"水彩笔",1));
            mPaintList.add(new PaintType(30,"记号笔",2));
        }

        return mPaintList;
    }

    public static List<EraserType> mEraserPaint;

    public static final List<EraserType> getEraserPaint(){
        if (mEraserPaint == null){
            mEraserPaint = new ArrayList<>();

            mEraserPaint.add(new EraserType(R.mipmap.ic_crop_5_4_black_18dp,5));
            mEraserPaint.add(new EraserType(R.mipmap.ic_crop_5_4_black_24dp,15));
            mEraserPaint.add(new EraserType(R.mipmap.ic_crop_5_4_black_36dp,30));
        }

        return mEraserPaint;
    }

}
