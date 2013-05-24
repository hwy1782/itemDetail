package com.example.ItemDetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.example.other.Util;
import com.example.test.NetworkActivity;
import com.example.test.ViewPagerAdapter;
import com.example.test.ViewPagerDemoActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends Activity implements View.OnTouchListener{

    private static final String PACKAGE_NAME = "com.example.ItemDetail";

    RelativeLayout priceBuyPlaceholder;

    LinearLayout itemEvaluate;

    RelativeLayout itemEvaluateMoudle;

    LinearLayout bottonModule;

    private ViewPager viewPager;

    private List<View> views;

    private static final String TAG = "MainActivity";

    /**
     * 相册的资源,实战开发中都是网络数据或者本地相册.
     */
    private static final int ALBUM_RES[] = {
            R.drawable.test1,
            R.drawable.test2,
            R.drawable.test3,
            R.drawable.test4,
            R.drawable.test5,
            R.drawable.test6
    };

    /**
     * 适配器.
     */
    private ViewPagerAdapter mViewPagerAdapter;

    /**
     * 数据源.
     */
    private JSONArray mJsonArray;

    private static final int ALBUM_COUNT = 100;

    MyScrollView contentWrap;
    LinearLayout floatContent;


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

    public void shareItem(View view){
        Intent intent = new Intent(MainActivity.this,NetworkActivity.class);
        startActivity(intent);
    }

    public void shareItem2(View view){
        Intent intent = new Intent(MainActivity.this,NetworkActivity2.class);
        startActivity(intent);
    }


    public void testListenScrollPostion(View view){
        Intent intent = new Intent(MainActivity.this,ListenScrollPostion.class);
        startActivity(intent);
    }

    private void initCompoment(){
        priceBuyPlaceholder = (RelativeLayout) findViewById(R.id.price_buy_placeholder);
        itemEvaluate = (LinearLayout) findViewById(R.id.item_evaluate);
        viewPager = (ViewPager) findViewById(R.id.vPager);
        itemEvaluateMoudle = (RelativeLayout) findViewById(R.id.item_picture_moudle);
        bottonModule = (LinearLayout) findViewById(R.id.botton_module);

        contentWrap = (MyScrollView) findViewById(R.id.content_wrap);
        contentWrap.setOnTouchListener(this);

        floatContent = (LinearLayout) findViewById(R.id.float_content);

    }

    private void setComponentTransparent(){
        priceBuyPlaceholder.setBackgroundColor(R.color.black);
        priceBuyPlaceholder.getBackground().setAlpha(160);

        bottonModule.setBackgroundColor(R.color.black);
        bottonModule.getBackground().setAlpha(150);

        floatContent.setBackgroundColor(R.color.black);
        floatContent.getBackground().setAlpha(160);
    }

    private void initViewPager(){
        //初始化JSonArray,给ViewPageAdapter提供数据源用.
        mJsonArray = new JSONArray();
        for(int i = 0;i < ALBUM_COUNT; i++){
            JSONObject object = new JSONObject();
            try {
                object.put("resid", ALBUM_RES[i % ALBUM_RES.length]);
                object.put("name", "Album " + i);
                mJsonArray.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        mViewPagerAdapter = new ViewPagerAdapter(this, mJsonArray);
        viewPager.setAdapter(mViewPagerAdapter);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {

//        if(event.getAction()==MotionEvent.ACTION_MOVE){

            //可以监听到ScrollView的滚动事件
            Log.i(TAG, "ACTION_MOVE X=" + contentWrap.getScrollX() + "w=" + contentWrap.getScrollY());

            if(Util.px2dip(this,contentWrap.getScrollY()) >  300){
                floatContent.setVisibility(View.VISIBLE);
            }else{
                floatContent.setVisibility(View.INVISIBLE);
            }

//        }

        return false;

    }



}
