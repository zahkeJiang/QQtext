package com.jy.android.travel;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.security.MessageDigest;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CoreUtil {
    private static final Executor LOAD_IMAGE_EXECUTOR = Executors.newFixedThreadPool(3);

    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        } else if (o1 == null || o2 == null) {
            return false;
        } else {
            return o1.equals(o2);
        }
    }

    /**
     * Load network image.
     *
     * @param imageView
     * @param initImage
     * @param url
     */
    public static void loadImage(final ImageView imageView, final int width, final int height, final int radius,
                                 final Drawable initImage, String url) {
        if (imageView == null || url == null || url.length() == 0) {
            return;
        }
        new AsyncTask<Object, Integer, Bitmap>() {
            private Object tag;

            @Override
            protected void onPreExecute() {
                tag = Long.valueOf(System.currentTimeMillis());
                imageView.setTag(tag);
                //
                // Set init local image.
                //
                if (initImage != null) {
                    imageView.setImageDrawable(initImage);
                } else {
                    imageView.setImageDrawable(null);
                }
            }

            @Override
            protected Bitmap doInBackground(Object... parameters) {
                try {
                    //
                    // Load network image.
                    //
                    //String url = (String) parameters[0];
                    //ImageRequest imageRequest = new ImageRequest(url);
                    //Bitmap bitmap = imageRequest.send();
                    //if (bitmap != null && width > 0 && height > 0) {
                     //   bitmap = scaledImage(bitmap, width, height, radius);
                    //}
                   // return bitmap;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null && imageView.getTag() == tag) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }.executeOnExecutor(LOAD_IMAGE_EXECUTOR, url);
    }

    public static Bitmap scaledImage(Bitmap bitmap, int width, int height, int radius) {
        double scale = Math.max(((double) width) / bitmap.getWidth(), ((double) height) / bitmap.getHeight());
        int tempWidth = (int) (bitmap.getWidth() * scale);
        int tempHeight = (int) (bitmap.getHeight() * scale);
        Bitmap tempBitmap = Bitmap.createScaledBitmap(bitmap, tempWidth, tempHeight, false);
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawCircle(radius, radius, radius, paint);
        canvas.drawRect(radius, 0, width - radius, radius, paint);
        canvas.drawCircle(width - radius, radius, radius, paint);
        canvas.drawRect(0, radius, width, height - radius, paint);
        canvas.drawCircle(radius, height - radius, radius, paint);
        canvas.drawRect(radius, height - radius, width - radius, height, paint);
        canvas.drawCircle(width - radius, height - radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(tempBitmap, (width - tempWidth) / 2, (height - tempHeight) / 2, paint);
        return newBitmap;
    }

    /**
     * Convert text to bitmap image.
     *
     * @param text
     * @param size
     * @param color
     * @return
     */
    public static Bitmap textToBitmap(String text, int size, int color) {
        if (text == null) {
            return null;
        }
        Paint paint = new Paint();
        paint.setTextSize(size * Resources.getSystem().getDisplayMetrics().density);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setLinearText(true);
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        Bitmap bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, -rect.left, -rect.top, paint);
        return bitmap;
    }

    /**
     * Calculate MD5 message digest.
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte bytes[] = messageDigest.digest(string.getBytes());
            StringBuilder result = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                if ((b & 0xFF) < 0x10) {
                    result.append("0");
                }
                result.append(Integer.toHexString(b & 0xFF));
            }
            return result.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Convert a URI to absolute local path.
     *
     * @param uri
     * @return
     */
    public static String uriToPath(Uri uri) {
        //Context context = App.getInstance().getApplicationContext();
        //Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        //try {
        //    if (cursor != null && cursor.moveToFirst()) {
        //        return cursor.getString(cursor.getColumnIndex("_data"));
        //    }
        //} finally {
        //    if (cursor != null) {
        //        cursor.close();
        //    }
        //}
        return null;
    }

    /**
     * Show soft input.
     *
     * @param activity
     * @param editText
     */
    public static void showSoftInput(Activity activity, EditText editText) {
        if (editText.requestFocus()) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Service.INPUT_METHOD_SERVICE);
            manager.showSoftInput(activity.getCurrentFocus(), 0);
        }
    }

    /**
     * Hide soft input.
     *
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Service.INPUT_METHOD_SERVICE);
        IBinder token = activity.getWindow().getDecorView().getWindowToken();
        manager.hideSoftInputFromWindow(token, 0);
    }
}
