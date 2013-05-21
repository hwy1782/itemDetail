package com.example.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.apache.http.HttpConnection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: hint
 * Date: 13-5-21
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class HttpService {


    public static Bitmap getPicture(String url){

        URL imageUrl = null;
        Bitmap rst = null;
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedInputStream bis = null;

        try{
            if (url == null){
                throw new IllegalArgumentException("url is null");
            }

            imageUrl = new URL(url);

            connection = (HttpURLConnection) imageUrl.openConnection();

            connection.setDoInput(true);

            is = connection.getInputStream();

            bis = new BufferedInputStream(is);

            rst = BitmapFactory.decodeStream(bis);

            return rst;

        }catch (MalformedURLException e) {

        } catch (IOException e) {

        }finally {
           connection.disconnect();
            try {
                if(is != null){
                    is.close();
                }if(bis != null){
                    bis.close();
                }
            } catch (IOException e) {

            }
        }

        return null;
    }

}
