package com.example.ItemDetail;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    ListView itemEvaluateLst;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        itemEvaluateLst = (ListView)findViewById(R.id.item_evaluate_lst);
//        itemEvaluateLst.setAdapter(getItemEvaluateLst());
    }

    private Adapter getItemEvaluateLst(){
        return  null;
    }

}
