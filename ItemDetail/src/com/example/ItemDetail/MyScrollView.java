package com.example.ItemDetail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import com.example.other.Util;

/**
 * Created with IntelliJ IDEA.
 * User: hint
 * Date: 13-5-21
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class MyScrollView extends ScrollView{

    private static final String TAG = "MyScrollView";

    private boolean canScroll;

    private GestureDetector mGestureDetector;
    private ScrollViewListener scrollViewListener = null;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        canScroll = true;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

//        Log.i(TAG, "======================================");

        if(ev.getAction() == MotionEvent.ACTION_UP)
            canScroll = true;
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(canScroll)
                if (Math.abs(distanceY) >= Math.abs(distanceX))
                    canScroll = true;
                else
                    canScroll = false;
            return canScroll;
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent ev) {

       *//* Log.i(TAG, "onTouchEvent => ACTION_MOVE X=" + ev.getX()
                + " Y = " + ev.getY()
        );*//*
        Log.i(TAG, "======================================");
        super.onTouchEvent(ev);
        return true;
    }*/


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {

        super.onScrollChanged(x, y, oldx, oldy);
        if(scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
