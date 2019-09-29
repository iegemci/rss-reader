package com.enesgemci.rssreader.network.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.WorkerThread;

import com.enesgemci.rssreader.network.MExecutor;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ImageDownloaderClient {

    @WorkerThread
    public void connect(
            final String baseUrl,
            final Handler mainThreadHandler,
            final ImageView imageView
    ) {
        if (!TextUtils.isEmpty(baseUrl)) {
            synchronized (baseUrl) {
                MExecutor.execute(() -> {
                    URL url;
                    HttpURLConnection connection = null;

                    try {
                        url = new URL(baseUrl);
                        connection = (HttpURLConnection) url.openConnection();

                        int responseCode = connection.getResponseCode();

                        if (responseCode == HttpURLConnection.HTTP_OK) {

                            InputStream stream = connection.getInputStream();

                            final BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeStream(stream, null, options);

                            // Calculate inSampleSize
                            options.inSampleSize = calculateInSampleSize(options, 100, 100);

                            // Decode bitmap with inSampleSize set
                            options.inJustDecodeBounds = false;
                            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream(), null, options);

                            mainThreadHandler.post(() -> imageView.setImageBitmap(bitmap));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                });
            }
        }
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
