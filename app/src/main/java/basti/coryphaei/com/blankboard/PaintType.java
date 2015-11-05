package basti.coryphaei.com.blankboard;

/**
 * Created by Bowen on 2015-11-05.
 */
public class PaintType {

    public boolean isSimplePath = true;
    private int StrokeWidth;
    private String paintName;
    private int id;

    public PaintType() {
    }

    public PaintType(int strokeWidth, String paintName, int id) {
        StrokeWidth = strokeWidth;
        this.paintName = paintName;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPaintName(String paintName) {
        this.paintName = paintName;
    }

    public String getPaintName() {
        return paintName;
    }

    public int getStrokeWidth() {
        return StrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        StrokeWidth = strokeWidth;
    }
}
