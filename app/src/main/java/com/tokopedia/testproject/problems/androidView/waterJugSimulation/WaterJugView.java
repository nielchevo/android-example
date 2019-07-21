package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tokopedia.testproject.R;


public class WaterJugView extends LinearLayout{

    private Context context = null;
    private int maxWater = 0;
    private int waterFill = 0;

    public WaterJugView(Context context) {
        super(context);
        this.context = context;
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WaterJugView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMaxWater(int maxWater) {
        this.maxWater = maxWater;
    }

    public void setWaterFill(int waterFill) {
        this.waterFill = waterFill;
    }

    //TODO
    /*
    Based on these variables: maxWater and waterFill, draw the jug with the water

    Example a:
    maxWater = 10
    waterFill = 0

    Result,
    View will draw like below
    |        |
    |        |
    |        |
    |        |
    `--------'

    Example b:
    maxWater = 10
    waterFill = 5

    Result,
    View will draw like below
    |        |
    |        |
    |--------|
    |        |
    `--------'

    Example c:
    maxWater = 10
    waterFill = 8

    Result,
    View will draw like below
    |        |
    |--------|
    |        |
    |        |
    `--------'

    Example d:
    maxWater = 10
    waterFill = 10

    Result,
    View will draw like below
     ________
    |        |
    |        |
    |        |
    |        |
    `--------'
    */

    //draw WaterJug view
    public void drawCurrentStatus(int imageId) {
        // get this jug view; TODO: CHANGE TO PICTURE

        // Create a imageView contaner

        ImageView imageHolder = findViewById(imageId);

        //imageHolder.setLayoutParams(new LinearLayout.LayoutParams(this.getLayoutParams().width, this.getLayoutParams().height));
        imageHolder.setVisibility(getRootView().VISIBLE);

        int jugContent = maxWater - waterFill;

        if(jugContent == 0) {
            // is empty

            //this.setBackgroundColor(Color.parseColor("#000000"));
            imageHolder.setImageResource(R.drawable.empty);
        }
        else if(jugContent == maxWater) {
            // is full
//            this.setBackgroundColor(Color.parseColor("#ffffff")); //black
            imageHolder.setImageResource(R.drawable.full);
        }
        else {
            // half
            if(jugContent >= (maxWater/2)) {
                //half FULL!
//                this.setBackgroundColor(Color.parseColor("#0000ff")); // blue
                imageHolder.setImageResource(R.drawable.half_full);
            } else {
//                this.setBackgroundColor(Color.parseColor("#ff0000")); // red
                imageHolder.setImageResource(R.drawable.half_empty);
            }
        }
    }
}
