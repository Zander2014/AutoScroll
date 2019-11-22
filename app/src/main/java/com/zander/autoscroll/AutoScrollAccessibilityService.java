package com.zander.autoscroll;

import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build.VERSION_CODES;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.RequiresApi;

/**
 * Created by finup_zander on 2019-11-18.
 */
public class AutoScrollAccessibilityService extends BaseAccessibilityService {
    private long timeSplite = 15 * 1000;
    private long lastTime;
    @RequiresApi(api = VERSION_CODES.N)
    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event) {
        super.onAccessibilityEvent(event);
        Log.e("AutoScroll", event.toString());

//        if(event.getPackageName().equals("com.kuaishou.nebula")){
            while (true){
                long curTime = System.currentTimeMillis();
                if(curTime - lastTime > timeSplite){

                    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                    int middleYValue = displayMetrics.heightPixels/2;
                    final int originYSideOfScreen = displayMetrics.heightPixels * 4/5;
                    final int destYSizeOfScreen = displayMetrics.heightPixels * 1 / 8;

                    Log.e("AutoScroll", "originYSideOfScreen:" + originYSideOfScreen + "--destYSizeOfScreen:" + destYSizeOfScreen);

                    Point origin = new Point(middleYValue,originYSideOfScreen);
                    Point dest = new Point(middleYValue,destYSizeOfScreen);
                    GestureDescription.Builder builder = new GestureDescription.Builder();
                    Path p = new Path();
                    p.moveTo(origin.x, origin.y);
                    p.lineTo(dest.x, dest.y);
                    builder.addStroke(new GestureDescription.StrokeDescription(p, 200L, 500L));
                    GestureDescription gesture = builder.build();
                    boolean isDispatched = dispatchGesture(gesture, new GestureResultCallback() {
                        @Override
                        public void onCompleted(GestureDescription gestureDescription) {
                            Log.e("AutoScroll","===============Gesture Completed================");
                            super.onCompleted(gestureDescription);
                        }

                        @Override
                        public void onCancelled(final GestureDescription gestureDescription) {
                            super.onCancelled(gestureDescription);
                            Log.e("AutoScroll","===============Gesture Cancelled================");
                        }
                    },null);
                    lastTime = curTime;
                }


//            }
        }


    }
}
