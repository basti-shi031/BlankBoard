package basti.coryphaei.com.blankboard;

/**
 * Created by Bowen on 2015-11-05.
 */
public class EraserType {

    private int imgId;
    private int eraserSize;

    public EraserType(int imgId, int eraserSize) {
        this.imgId = imgId;
        this.eraserSize = eraserSize;
    }

    public void setEraserSize(int eraserSize) {
        this.eraserSize = eraserSize;
    }

    public int getEraserSize() {
        return eraserSize;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
