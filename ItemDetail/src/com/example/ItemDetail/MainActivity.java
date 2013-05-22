package com.example.ItemDetail;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.AndroidCharacter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.example.service.HttpService;
import com.example.test.ViewPagerAdapter;
import com.example.test.ViewPagerDemoActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String PACKAGE_NAME = "com.example.ItemDetail";

    RelativeLayout priceBuyPlaceholder;

    LinearLayout itemEvaluate;

    RelativeLayout itemEvaluateMoudle;

    LinearLayout bottonModule;

    private ViewPager viewPager;

    private List<View> views;

    private int offset = 0;
    private int currIndex = 0;
    private int bmpW;

    ImageView itemLargePicture;

    private static final String TAG = "MainActivity";

    /**
     * 相册的资源,实战开发中都是网络数据或者本地相册.
     */
    private static final int ALBUM_RES[] = {
            R.drawable.test1,R.drawable.test2,R.drawable.test3,
            R.drawable.test4,R.drawable.test5,R.drawable.test6
    };
   /* private ViewPager mViewPager;*/

    /**
     * 适配器.
     */
    private ViewPagerAdapter mViewPagerAdapter;

    /**
     * 数据源.
     */
    private JSONArray mJsonArray;

    private static final int ALBUM_COUNT = 100;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //初始化组件
        initCompoment();
        //初始化大图
        initViewPager();
        //添加评论信息
        addItemEvaluate();
        //设置组件透明度
        setComponentTransparent();

    }


    public void buyPage(View view){
        Intent intent = new Intent(MainActivity.this,ViewPagerDemoActivity.class);
        startActivity(intent);
    }

    /**
     * 点击图片展示大图
     * @param view
     */
    public void openLargePicture(View view){
          Toast.makeText(MainActivity.this,"大图",100).show();
       /* String uriStr = "android.resource://" + PACKAGE_NAME + "/" + R.drawable.meinv;
        Uri imageFile = Uri.parse(uriStr);

        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setDataAndType(imageFile, "image*//*");
        startActivity(it);*/
    }

    private void initCompoment(){
        priceBuyPlaceholder = (RelativeLayout) findViewById(R.id.price_buy_placeholder);
        itemEvaluate = (LinearLayout) findViewById(R.id.item_evaluate);
        viewPager = (ViewPager) findViewById(R.id.vPager);
        itemEvaluateMoudle = (RelativeLayout) findViewById(R.id.item_picture_moudle);
        bottonModule = (LinearLayout) findViewById(R.id.botton_module);
    }

    private void setComponentTransparent(){
        priceBuyPlaceholder.setBackgroundColor(R.color.black);
        priceBuyPlaceholder.getBackground().setAlpha(160);

        bottonModule.setBackgroundColor(R.color.black);
        bottonModule.getBackground().setAlpha(150);
    }

    /**
     * 初始化商品详情大图

    private void initViewPager() {
        int viewHeight = 0;
        views = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0; i < 3; i++){
            View tmpView = inflater.inflate(R.layout.item_picture, null);
            itemLargePicture = (ImageView) tmpView.findViewById(R.id.item_large_picture);
            String IMAGE_URL = "http://ptg.tc.qq.com/qtuan2/0/_abcompat_77a776cf3d04b6f3195e0cfd7fc67435/660";
            new DownloadImageTask().execute(IMAGE_URL);
            views.add(tmpView);
            viewHeight = tmpView.getHeight() > viewHeight ? tmpView.getHeight() : viewHeight;
        }

        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
     */

    private void initViewPager(){
        //初始化JSonArray,给ViewPageAdapter提供数据源用.
        mJsonArray = new JSONArray();
        for(int i = 0;i<ALBUM_COUNT; i++){
            JSONObject object = new JSONObject();
            try {
                object.put("resid", ALBUM_RES[i % ALBUM_RES.length]);
                object.put("name", "Album " + i);
                mJsonArray.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
//        viewPager = (ViewPager)findViewById(R.id.viewpager);
        mViewPagerAdapter = new ViewPagerAdapter(this, mJsonArray);
        viewPager.setAdapter(mViewPagerAdapter);
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Drawable>
    {

        protected Drawable doInBackground(String... urls) {
            return loadImageFromNetwork(urls[0]);
        }

        protected void onPostExecute(Drawable result) {

            if(result == null){
                Log.d(TAG,"DownloadImageTask=>onPostExecute ,rst is null");
            }

            itemLargePicture.setImageDrawable(result);
        }
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


    /**
     * 添加评论
     */
    private void addItemEvaluate(){
        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0 ; i < 3; i++){
            View view = inflater.inflate(R.layout.item_evaluate,itemEvaluate);
            view.setBackgroundColor(R.color.black);
            view.getBackground().setAlpha(150);
        }
    }



    /**
     * 填充viewPage页面
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)   {
            container.removeView(mListViews.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return  mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    }

    /**
     * 监听viewPage事件
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// view1 -> view2
        //        int two = one * 2;
        public void onPageScrollStateChanged(int arg0) {


        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        public void onPageSelected(int arg0) {

            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);
            currIndex = arg0;
            animation.setFillAfter(true);// True:
            animation.setDuration(300);
        }

    }

}
