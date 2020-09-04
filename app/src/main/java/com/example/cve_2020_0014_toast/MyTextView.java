package com.example.cve_2020_0014_toast;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        Log.d("LittleLisk", String.format("x:%f, y:%f", x, y));
        return false;
    }
}
