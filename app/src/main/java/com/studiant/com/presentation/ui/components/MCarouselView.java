package com.studiant.com.presentation.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.studiant.com.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

/**
 * Created by groupama on 14/04/2017.
 */

public class MCarouselView extends CarouselView {
    int[] sampleImages = {R.drawable.test1, R.drawable.test2};

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


    public void setImage(){
        this.setPageCount(sampleImages.length);
        this.setImageListener(imageListener);
    }

    public MCarouselView(Context context) {
        super(context);
    }

    public MCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MCarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MCarouselView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
