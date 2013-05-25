package com.example.ItemDetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class MainActivity extends Activity implements View.OnTouchListener{

    //价格购买区块
    private LinearLayout priceAndBuyMoudle;
    //商品评论区块
    private LinearLayout itemCommentMoudle;
    //商品大图区块
    private RelativeLayout itemLargePicMoudle;
    //底部工具栏
    private LinearLayout bottomModule;
    //滚动大图
    private ViewPager itemViewPager;
    //滚动scroll
    MyScrollView itemDetailScroller;
    // freamlayout 顶端浮动工具栏
    private LinearLayout topFloatContent;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //初始化组件
        initCompoment();
        //设置组件透明度
        setComponentTransparent();
        //获取大图
        initViewPager();
        //添加评论信息
        addItemEvaluate();
    }


    public void buyPage(View view){
        Intent intent = new Intent(MainActivity.this,ViewPagerDemoActivity.class);
        startActivity(intent);
    }

    public void shareItem(View view){
        Intent intent = new Intent(MainActivity.this,NetworkActivity.class);
        startActivity(intent);
    }

    private void initCompoment() {

        priceAndBuyMoudle = (LinearLayout) findViewById(R.id.price_and_buy_moudle);
        itemCommentMoudle = (LinearLayout) findViewById(R.id.item_comment);
        itemViewPager = (ViewPager) findViewById(R.id.vPager);
        itemLargePicMoudle = (RelativeLayout) findViewById(R.id.item_picture_moudle);

        //底层浮动工具栏
        bottomModule = (LinearLayout) findViewById(R.id.bottom_module);

        itemDetailScroller = (MyScrollView) findViewById(R.id.item_detail_scroll);
        itemDetailScroller.setOnTouchListener(this);

        // freamlayout 顶端浮动工具栏
        topFloatContent = (LinearLayout) findViewById(R.id.top_float_content);
    }

    private void  setComponentTransparent() {
        Drawable black = getResources().getDrawable(R.color.black);
        priceAndBuyMoudle.setBackgroundDrawable(black);

        topFloatContent.setBackgroundDrawable(black);
        topFloatContent.getBackground().setAlpha(150);

        bottomModule.getBackground().setAlpha(150);
    }

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
        mViewPagerAdapter = new ViewPagerAdapter(this, mJsonArray);
        itemViewPager.setAdapter(mViewPagerAdapter);
    }

    /**
     * 添加评论
     */
    private void addItemEvaluate(){

        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0 ; i < 6; i++){
            View view = inflater.inflate(R.layout.item_evaluate, itemCommentMoudle);
            Drawable black = getResources().getDrawable(R.color.black);
            view.setBackgroundDrawable(black);
            view.getBackground().setAlpha(150);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_MOVE){

            //可以监听到ScrollView的滚动事件
            Log.i(TAG, "ACTION_MOVE X=" + itemDetailScroller.getScrollX() + " Y =" + itemDetailScroller.getScrollY());

            if(Util.px2dip(this, itemDetailScroller.getScrollY()) >  300){
                topFloatContent.setVisibility(View.VISIBLE);
                Drawable black = getResources().getDrawable(R.color.black);
                topFloatContent.setBackgroundDrawable(black);

            }else{
                topFloatContent.setVisibility(View.INVISIBLE);
            }
        }
        return false;

    }
}
