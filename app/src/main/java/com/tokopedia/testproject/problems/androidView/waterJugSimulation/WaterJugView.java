package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class WaterJugView extends View {

    private int maxWater = 0;
    private int waterFill = 0;

    public WaterJugView(Context context) {
        super(context);
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    public void drawCurrentStatus() {
        // get this jug view; TODO: CHANGE TO PICTURE
        /*
        hardcoded by color code
        FULL -> BLACK;
        HALF FULL -> BLUE
        HALF EMPTY -> RED;
        EMPTY -> WHITE
        */

        int jugContent = maxWater - waterFill;

        if(jugContent == 0) {
            // is empty

            this.setBackgroundColor(Color.parseColor("#000000"));
        }
        else if(jugContent == maxWater) {
            // is full
            this.setBackgroundColor(Color.parseColor("#ffffff")); //black
        }
        else {
            // half
            if(jugContent >= (maxWater/2)) {
                //half FULL!
                this.setBackgroundColor(Color.parseColor("#0000ff")); // blue
            } else {
                this.setBackgroundColor(Color.parseColor("#ff0000")); // red
            }
        }
    }
}
