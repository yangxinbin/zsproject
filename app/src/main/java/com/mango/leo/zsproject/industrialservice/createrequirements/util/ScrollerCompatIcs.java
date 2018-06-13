package com.mango.leo.zsproject.industrialservice.createrequirements.util;

/**
 * Created by admin on 2018/6/14.
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.Scroller;

class ScrollerCompatIcs {
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static float getCurrVelocity(Scroller scroller) {
        return scroller.getCurrVelocity();
    }
}
