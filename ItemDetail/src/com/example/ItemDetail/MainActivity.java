package com.example.ItemDetail;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    RelativeLayout priceBuyPlaceholder;

    LinearLayout itemEvaluate;

    private ViewPager viewPager;

    private List<View> views;

    private int offset = 0;
    private int currIndex = 0;
    private int bmpW;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initCompoment();

        initViewPager();

        addItemEvaluate();
    }


    public void buyPage(View view){
        Intent intent = new Intent(MainActivity.this,ItemDetail.class);
        startActivity(intent);
    }

    private void initCompoment(){
        priceBuyPlaceholder = (RelativeLayout) findViewById(R.id.price_buy_placeholder);
//        itemLargePicture = (ImageView) findViewById(R.id.item_large_picture);
        itemEvaluate = (LinearLayout) findViewById(R.id.item_evaluate);
        viewPager = (ViewPager) findViewById(R.id.vPager);
    }

    /**
     * 初始化商品详情大图
     */
    private void initViewPager() {
        int viewHeight = 0;
        views = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0; i < 3; i++){
            View tmpView = inflater.inflate(R.layout.item_picture, null);
            views.add(tmpView);
            viewHeight = tmpView.getHeight() > viewHeight ? tmpView.getHeight() : viewHeight;
        }

        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 添加评论
     */
    private void addItemEvaluate(){
        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0 ; i < 3; i++){
            inflater.inflate(R.layout.item_evaluate,itemEvaluate);
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
//            imageView.startAnimation(animation);
//            Toast.makeText(ViewPageTest.this, "current page is " + viewPager.getCurrentItem() , Toast.LENGTH_SHORT).show();
        }

    }

}
