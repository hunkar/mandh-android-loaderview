package com.mandh.loader;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

class Dialog extends android.app.Dialog {
    private Context context;
    private ImageView imageLoader;
    private RelativeLayout containerImageLoader;
    private Drawable loaderImg;
    private RotationDirection direction = RotationDirection.CLOCKWISE;

    private Animation loaderAnimation;


    public Dialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loader_layout);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);

        setDefinitions();
    }

    private void setDefinitions() {
        imageLoader = findViewById(com.mandh.loader.R.id.loader_disc);
        containerImageLoader = findViewById(com.mandh.loader.R.id.loader_disc_container);

        if (loaderImg != null)
            imageLoader.setImageDrawable(loaderImg);

        setLoaderStatus();
    }

    public void setLoaderStatus() {
        if (direction == RotationDirection.CLOCKWISE) {
            loaderAnimation = AnimationUtils.loadAnimation(context, com.mandh.loader.R.anim.rotate);
        } else {
            loaderAnimation = AnimationUtils.loadAnimation(context, com.mandh.loader.R.anim.rotate_anti_clock);
        }

        loaderAnimation.setFillAfter(true);
        imageLoader.startAnimation(loaderAnimation);

        containerImageLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void show() {
        super.show();
        setLoaderStatus();
    }

    @Override
    public void dismiss() {
        super.dismiss();

        if (loaderAnimation != null) {
            imageLoader.clearAnimation();
            loaderAnimation = null;
        }
    }
}
