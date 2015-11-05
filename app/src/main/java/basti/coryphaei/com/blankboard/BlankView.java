package basti.coryphaei.com.blankboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Bowen on 2015-11-04.
 */
public class BlankView extends View{

    private Paint mPaint,mPaintEraser;
    private int defaultColor = getResources().getColor(PaintUtils.getColors().get(0).getColor());
    private int eraserColor  = getResources().getColor(R.color.white);
    private int defaultStrokeSize = 5;
    private Path mPath;
    private int mLastX,mLastY;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int width,height;
    private Mode mode = Mode.PEN;

    public BlankView(Context context) {
        this(context, null);
    }

    public BlankView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        initPaint();

        //初始化路径
        initPath();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(defaultStrokeSize);
        mPaint.setColor(defaultColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        mPaintEraser = new Paint();
        mPaintEraser.setStrokeWidth(defaultStrokeSize);
        mPaintEraser.setColor(eraserColor);
        mPaintEraser.setStyle(Paint.Style.STROKE);
        mPaintEraser.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();



        initBitmap();
    }

    private void initBitmap() {

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(getResources().getColor(android.R.color.white));
    }

    private void initPath() {
        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mPath = new Path();
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);

                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                mLastX = x;
                mLastY = y;
                invalidate();
                Log.i("TAG", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                mLastX = x;
                mLastY = y;
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        switch (mode){
            case PEN:mCanvas.drawPath(mPath, mPaint);break;
            case ERASER:mCanvas.drawPath(mPath, mPaintEraser);break;
        }

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public boolean save(){
        return ScannerUtils.saveImageToGallery(getContext(),mBitmap, ScannerUtils.ScannerType.RECEIVER);
    }

    public void clear(){
        Paint p = new Paint();
        //清屏
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPaint(p);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }

    public void setPaintColor(int color){
        mode = Mode.PEN;
        mPaint.setColor(getResources().getColor(color));
    }

    public void setPaintType(PaintType type){
        mode = Mode.PEN;
        mPaint.setStrokeWidth(type.getStrokeWidth());
    }

    public void recycleBitmap(){
        if (mBitmap != null&&!mBitmap.isRecycled()){
            mBitmap.recycle();
        }
    }

    public void setEraser(EraserType type) {
        mode = Mode.ERASER;
        mPaintEraser.setStrokeWidth(type.getEraserSize());
    }

    enum Mode{
        PEN,ERASER
    }
}
