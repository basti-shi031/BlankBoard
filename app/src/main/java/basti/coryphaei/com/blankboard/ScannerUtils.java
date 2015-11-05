package basti.coryphaei.com.blankboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: HouSheng
 * 
 * @类 说 明: 图片扫描工具类
 */
public class ScannerUtils {

  // 扫描的三种方式
  public static enum ScannerType {
    RECEIVER, MEDIA
  }

  // 首先保存图片
  public static boolean saveImageToGallery(Context context, Bitmap bitmap, ScannerType type) {
    boolean saveSuccess = false;
    File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"blankboard");
    if (!appDir.exists()) {
      // 目录不存在 则创建
      appDir.mkdirs();
    }
    String fileName = System.currentTimeMillis() + ".jpg";
    File file = new File(appDir, fileName);
    try {
      FileOutputStream fos = new FileOutputStream(file);
      bitmap.compress(CompressFormat.JPEG, 100, fos); // 保存bitmap至本地
      fos.flush();
      fos.close();
      saveSuccess = true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (type == ScannerType.RECEIVER) {
        ScannerByReceiver(context, file.getAbsolutePath());
      } else if (type == ScannerType.MEDIA) {
        ScannerByMedia(context, file.getAbsolutePath());
      }
      if (!bitmap.isRecycled()) {
        // bitmap.recycle(); 当存储大图片时，为避免出现OOM ，及时回收Bitmap
        System.gc(); // 通知系统回收
      }
    }
    return saveSuccess;
  }

  /** Receiver扫描更新图库图片 **/

  private static void ScannerByReceiver(Context context, String path) {
    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
        + path)));
    Log.v("TAG", "receiver scanner completed");
  }

  /** MediaScanner 扫描更新图片图片 **/

  private static void ScannerByMedia(Context context, String path) {
    MediaScannerConnection.scanFile(context, new String[] {path}, null, null);
    Log.v("TAG", "media scanner completed");
  }
}
