package com.example.service;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: hint
 * Date: 13-5-22
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Drawable>{

    ImageView itemLargePicture;

    private static final String TAG = "DownloadImageTask";

    protected Drawable doInBackground(String... urls) {
        return loadImageFromNetwork(urls[0]);
    }

    protected void onPostExecute(Drawable result) {

        if(result == null){
            Log.d(TAG, "DownloadImageTask=>onPostExecute ,rst is null");
        }

        itemLargePicture.setImageDrawable(result);
    }

    private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        if (drawable == null) {
            Log.d(TAG, "null drawable");
        } else {
            Log.d(TAG, "not null drawable");
        }

        return drawable ;
    }

}
