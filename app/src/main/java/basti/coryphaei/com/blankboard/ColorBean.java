package basti.coryphaei.com.blankboard;

import android.graphics.Color;

/**
 * Created by Bowen on 2015-11-05.
 */
public class ColorBean {

    private String colorString;
    private int color;

    public ColorBean() {
    }

    public ColorBean(String colorString, int color) {
        this.colorString = colorString;
        this.color = color;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getColorString() {
        return colorString;
    }

    public int getColor() {
        return color;
    }
}
