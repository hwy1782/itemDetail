package com.example.test;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import com.example.ItemDetail.OpenImage;
import com.example.ItemDetail.R;
import com.example.service.DownloadImageTask;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * 相册的ItemView,自定义View.方便复用.
 */
public class ViewPagerItemView extends FrameLayout implements View.OnClickListener{

    /**
     * 图片的ImageView.
     */
    private ImageView mAlbumImageView;

    /**
     * 图片名字的TextView.
     */
    private TextView mALbumNameTextView;

    /**
     * 图片的Bitmap.
     */
    private Bitmap mBitmap;

    /**
     * 要显示图片的JSONOBject类.
     */
    private JSONObject mObject;

    private Context myContext;

    private LinearLayout itemNamePlaceholder;


    public ViewPagerItemView(Context context){
        super(context);
        myContext = context;
        setupViews();
    }

    public ViewPagerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        myContext = context;
        setupViews();
    }

    //初始化View.
    private void setupViews(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.viewpager_itemview, null);

        mAlbumImageView = (ImageView)view.findViewById(R.id.item_imgview);
        mALbumNameTextView = (TextView)view.findViewById(R.id.item_name);
        itemNamePlaceholder = (LinearLayout)view.findViewById(R.id.item_name_placeholder);

        itemNamePlaceholder.getBackground().setAlpha(150);

        addView(view);

        //添加图片监听器
        mAlbumImageView.setOnClickListener(this);
    }

    /**
     * 填充数据，共外部调用.
     * @param object
     */
    public void setData(JSONObject object){
        this.mObject = object;
        try {
            int resId = object.getInt("resid");
            String name = object.getString("name");
            //实战中如果图片耗时应该令其一个线程去拉图片异步,不然把UI线程卡死.
            mAlbumImageView.setImageResource(resId);
            mALbumNameTextView.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setDataByNet(JSONObject object){
        this.mObject = object;
        try {
//            String imgUrl = object.getString("imgUrl");
            String imgUrl = "http://ptg.tc.qq.com/qtuan2/0/_abcompat_77a776cf3d04b6f3195e0cfd7fc67435/660";
            String name = object.getString("name");
            //实战中如果图片耗时应该令其一个线程去拉图片异步,不然把UI线程卡死.
            mAlbumImageView.setImageDrawable((Drawable) new DownloadImageTask().execute(imgUrl).get());
            mALbumNameTextView.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里内存回收.外部调用.
     */
    public void recycle(){
        mAlbumImageView.setImageBitmap(null);
        if ((this.mBitmap == null) || (this.mBitmap.isRecycled()))
            return;
        this.mBitmap.recycle();
        this.mBitmap = null;
    }


    /**
     * 重新加载.外部调用.
     */
    public void reload(){
        try {
            int resId = mObject.getInt("resid");
            //实战中如果图片耗时应该令其一个线程去拉图片异步,不然把UI线程卡死.
            mAlbumImageView.setImageResource(resId);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
       Intent openImageExplor = new Intent(myContext,OpenImage.class);
       myContext.startActivity(openImageExplor);
    }

    /*@Override
    public void setOnLongClickListener(OnLongClickListener l) {
        Intent intent = new Intent(myContext,OpenImage.class);
        myContext.startActivity(intent);
    }*/
}
