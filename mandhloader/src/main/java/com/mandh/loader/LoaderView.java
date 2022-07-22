package com.mandh.loader;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;

/**
 * LoaderView is class which include all logics of Mand Loader View
 */
public class LoaderView extends RelativeLayout {
    ImageView imageLoader;
    RelativeLayout containerImageLoader;
    Boolean isLoaderAnimating = false;
    Drawable loaderImg;
    RotationDirection direction = RotationDirection.CLOCKWISE;

    Animation loaderAnimation;

    Context context;

    /**
     * Constructor
     *
     * @param context Context of game.
     * @param attrs   attributes of loader view. Pass attributes by styleable.MandhLoladerView
     */
    public LoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        initView();
        setAttrs(attrs);
    }

    /**
     * Constructor
     *
     * @param context Context of game.
     */
    public LoaderView(Context context) {
        super(context);

        this.context = context;

        initView();
        attachToContext();
    }

    /**
     * Create loader view and add to context main layout.
     */
    public void attachToContext() {
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        ((ViewGroup) ((Activity) this.context).getWindow().getDecorView().findViewById(android.R.id.content)).addView(this, relativeParams);
    }

    /**
     * This method parse custom attributes
     *
     * @param attrs AttributeSet of view
     */
    private void setAttrs(AttributeSet attrs) {
        ViewCompat.setTranslationZ(this, Float.MAX_VALUE);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MandhLoladerView, 0, 0);

        try {
            loaderImg = typedArray.getDrawable(R.styleable.MandhLoladerView_loaderSrc);

            float loaderOpacity = typedArray.getFloat(R.styleable.MandhLoladerView_loaderOpacity, 1);
            float loaderBackgroundOpacity = typedArray.getFloat(R.styleable.MandhLoladerView_loaderBackgroundOpacity, 1);
            String backgroundColor = typedArray.getString(R.styleable.MandhLoladerView_loaderBackgroundColor);
            String rotationDirection = typedArray.getString(R.styleable.MandhLoladerView_rotationDirection);
            int width = typedArray.getInt(R.styleable.MandhLoladerView_loaderImageWidth, 75);
            int height = typedArray.getInt(R.styleable.MandhLoladerView_loaderImageHeight, 75);

            if(loaderImg != null){
                this.setLoaderImage(loaderImg);
            }

            if(rotationDirection != null && rotationDirection.equals("anticlockwise")){
                this.direction = RotationDirection.ANTI_CLOCKWISE;
            }

            this.setLoaderOpacity(loaderOpacity);

            if(backgroundColor != null && !backgroundColor.isEmpty()){
                this.setLoaderBackgroundColor(backgroundColor);
            }

            this.setLoaderBackgroundOpacity(loaderBackgroundOpacity);
            this.setLoaderImageWidth(width);
            this.setLoaderImageHeight(height);
        } finally {
            typedArray.recycle();
        }
    }


    /**
     * This method prepare view and definitions.
     */
    private void initView() {
        inflate(getContext(), R.layout.loader_layout, this);

        setDefinitions();
        setActions();
    }

    /**
     * Set animated loader icon with drawable image
     *
     * @param loaderImage loader image
     */
    public void setLoaderImage(Drawable loaderImage) {
        this.loaderImg = loaderImage;

        if (this.imageLoader != null) {
            this.imageLoader.setImageDrawable(this.loaderImg);
        }
    }

    /**
     * Set loader image width
     *
     * @param width integer value for loader image width
     */
    public void setLoaderImageWidth(int width) {
        if (this.imageLoader != null) {
            this.imageLoader.getLayoutParams().width = width;
        }
    }

    /**
     * Set loader image height
     *
     * @param height integer value for loader image height
     */
    public void setLoaderImageHeight(int height) {
        if (this.imageLoader != null) {
            this.imageLoader.getLayoutParams().height = height;
        }
    }

    /**
     * Set background overlay color.
     *
     * @param color String color code. It can be with alpha like '#10ff44ff'
     */
    public void setLoaderBackgroundColor(String color) {
        if (this.containerImageLoader != null) {
            this.containerImageLoader.setBackgroundColor(Color.parseColor(color));
        }
    }

    /**
     * Set background overlay opacity
     *
     * @param opacity Float opacity value
     */
    public void setLoaderBackgroundOpacity(float opacity) {
        if (this.containerImageLoader != null) {
            Drawable background = this.containerImageLoader.getBackground();

            int color = Color.BLUE;
            if (background instanceof ColorDrawable)
                color = ((ColorDrawable) background).getColor();

            int alpha = Color.alpha(color);
            int red = Color.red(color);
            int green = Color.green(color);
            int blue = Color.blue(color);

            alpha *= opacity;

            this.containerImageLoader.setBackgroundColor(Color.argb(alpha, red, green, blue));
        }
    }

    /**
     * Set loader direction
     *
     * @param direction RotationDirection enum for loader rotation direction.
     */
    public void setLoaderDirection(RotationDirection direction) {
        this.direction = direction;
    }

    /**
     * Set loader opacity
     *
     * @param opacity float value to set loader image opacity.
     */
    public void setLoaderOpacity(float opacity) {
        if (this.imageLoader != null) {
            this.imageLoader.setAlpha(opacity);
        }
    }

    /**
     * Reset loader image resource
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setDefaultLoaderImage() {
        if (loaderImg != null)
            imageLoader.setImageDrawable(context.getDrawable(R.drawable.loading));
    }

    /**
     * This method set definitons views from layout.
     */
    private void setDefinitions() {
        imageLoader = findViewById(R.id.loader_disc);
        containerImageLoader = findViewById(R.id.loader_disc_container);

        if (loaderImg != null)
            imageLoader.setImageDrawable(loaderImg);
    }

    /**
     * This method set actions and events on layout views.
     */
    private void setActions() {
        containerImageLoader.setOnClickListener(v -> {
            return;
        });
    }

    /**
     * This method set loader action.
     *
     * @param visible visibility of loader.
     */
    public void setloaderStatus(Boolean visible) {
        if (visible) {
            if (direction == RotationDirection.CLOCKWISE) {
                loaderAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate);
            } else {
                loaderAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_anti_clock);
            }

            loaderAnimation.setFillAfter(true);
            imageLoader.startAnimation(loaderAnimation);

            containerImageLoader.setVisibility(VISIBLE);

            isLoaderAnimating = true;
        } else if (loaderAnimation != null) {
            imageLoader.clearAnimation();

            containerImageLoader.setVisibility(GONE);

            isLoaderAnimating = false;
        }
    }

    /**
     * This method toggle visibility of comment loader.
     */
    public void toggleLoaderViewVisibility() {
        this.setloaderStatus(!isLoaderAnimating);
    }
}