package com.example.ItemDetail;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: hint
 * Date: 13-5-23
 * Time: 上午11:34
 * To change this template use File | Settings | File Templates.
 */
public class ListenScrollPostion extends Activity {

    LinearLayout scrollPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listen_scroll_ostion);
        
        initView();
    }

    private void initView() {

        scrollPlaceHolder = (LinearLayout) findViewById(R.id.scroll_place_holder);


        for(int i = 0; i < 50; i++){
            TextView textView = new TextView(this);
            textView.setText("text ====>"+i);
            textView.setTextSize(20);
            scrollPlaceHolder.addView(textView);
        }

    }


}
