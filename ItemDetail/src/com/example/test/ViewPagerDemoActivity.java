package com.example.test;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.ItemDetail.MyScrollView;
import com.example.ItemDetail.R;
import com.example.other.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * @author frankiewei
 * ViewPager控件使用的Demo.
 */
public class ViewPagerDemoActivity extends Activity implements View.OnTouchListener{

    /**
     * 这里定义了相册的总数100条.
     */
    private static final int ALBUM_COUNT = 100;

    /**
     * 相册的资源,实战开发中都是网络数据或者本地相册.
     */
    private static final int ALBUM_RES[] = {
            R.drawable.test1,R.drawable.test2,R.drawable.test3,
            R.drawable.test4,R.drawable.test5,R.drawable.test6
    };
    private static final String TAG = "";

    private ViewPager mViewPager;

    /**
     * 适配器.
     */
    private ViewPagerAdapter mViewPagerAdapter;

    /**
     * 数据源.
     */
    private JSONArray mJsonArray;


    LinearLayout priceAndBuyMoudle;

    MyScrollView contentWrap;
    LinearLayout floatContent;

    LinearLayout priceBuyPlaceholder;

    LinearLayout itemEvaluate;

    private ViewPager viewPager;
    RelativeLayout itemEvaluateMoudle;

    LinearLayout bottomModule;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //初始化组件
        initCompoment();

        initViewPager();

        initBackGround();
        
        //添加评论信息
        addItemEvaluate();
    }

    private void initCompoment() {

        priceBuyPlaceholder = (LinearLayout) findViewById(R.id.price_and_buy_moudle);
        itemEvaluate = (LinearLayout) findViewById(R.id.item_comment);
        viewPager = (ViewPager) findViewById(R.id.vPager);
        itemEvaluateMoudle = (RelativeLayout) findViewById(R.id.item_picture_moudle);

        //底层浮动工具栏
        bottomModule = (LinearLayout) findViewById(R.id.bottom_module);

        contentWrap = (MyScrollView) findViewById(R.id.item_detail_scroll);
        contentWrap.setOnTouchListener(this);

        // freamlayout 顶端浮动工具栏
        floatContent = (LinearLayout) findViewById(R.id.top_float_content);
    }

    private void initBackGround() {
        priceAndBuyMoudle = (LinearLayout) findViewById(R.id.price_and_buy_moudle);
        Drawable black = getResources().getDrawable(R.color.black);
        priceAndBuyMoudle.setBackgroundDrawable(black);


        floatContent.setBackgroundDrawable(black);
        floatContent.getBackground().setAlpha(150);
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
        mViewPager = (ViewPager)findViewById(R.id.vPager);
        mViewPagerAdapter = new ViewPagerAdapter(this, mJsonArray);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    /**
     * 添加评论
     */
    private void addItemEvaluate(){

        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0 ; i < 6; i++){
            View view = inflater.inflate(R.layout.item_evaluate,itemEvaluate);
            Drawable black = getResources().getDrawable(R.color.black);
            view.setBackgroundDrawable(black);
            view.getBackground().setAlpha(150);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_MOVE){

            //可以监听到ScrollView的滚动事件
            Log.i(TAG, "ACTION_MOVE X=" + contentWrap.getScrollX() + " Y =" + contentWrap.getScrollY());

            if(Util.px2dip(this, contentWrap.getScrollY()) >  300){
                floatContent.setVisibility(View.VISIBLE);

                Drawable backGroundBlack = getResources().getDrawable(R.color.black);
                floatContent.setBackgroundDrawable(backGroundBlack);
            }else{
                floatContent.setVisibility(View.INVISIBLE);
            }
        }
        return false;

    }
}