package com.unsober.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by akshay on 05-07-2016.
 */
public class CuyabraTextView extends TextView {
    public CuyabraTextView(Context context) {
        super(context);
    }

    public CuyabraTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cuyabra.otf"));
    }

    public CuyabraTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cuyabra.otf"));
    }
}
