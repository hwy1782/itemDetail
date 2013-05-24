package com.example.other;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: hint
 * Date: 13-5-24
 * Time: 上午10:11
 * To change this template use File | Settings | File Templates.
 */
public class Util {


    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }


}
