package com.studiant.com.presentation.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.studiant.com.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.CirclePageIndicator;
import com.synnapps.carouselview.ImageListener;

/**
 * Created by groupama on 14/04/2017.
 */

public class MCarouselView extends CarouselView {
    int[] sampleImages = {R.drawable.home1, R.drawable.home2, R.drawable.home3};

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


    public void setImage(){
        this.setPageCount(sampleImages.length);
        this.setImageListener(imageListener);

        CirclePageIndicator indicator = (CirclePageIndicator) this.findViewById(R.id.indicator);
        if(indicator !=null){
            indicator.setVisibility(View.GONE);
        }
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
