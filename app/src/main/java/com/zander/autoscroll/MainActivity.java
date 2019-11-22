package com.zander.autoscroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by finup_zander on 2019-11-18.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        Window window = this.getWindow();
        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = 0; // 高度设置为0
        p.width = 0;//宽0
        p.gravity = Gravity.CENTER;
        window.setAttributes(p);

        BaseAccessibilityService.getInstance().init(this);
        BaseAccessibilityService.getInstance().goAccess();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        this.finish();
    }
}
