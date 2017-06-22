package com.weigan.imagetransformanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button button, button2;
    private ImageView mImage;
    private ViewGroup mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        mImage = (ImageView) findViewById(R.id.image);
        mRootView = (ViewGroup) findViewById(R.id.rootView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomIn();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomOut();
            }
        });
    }


    private void zoomIn() {
        ViewGroup.LayoutParams layoutParams = mImage.getLayoutParams();
        int width = layoutParams.width;
        int height = layoutParams.height;
        layoutParams.width = (int) (width * 2);
        layoutParams.height = height * 2;
        mImage.setLayoutParams(layoutParams);
        mImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

        TransitionSet transitionSet = new TransitionSet();
        Transition bound = new ChangeBounds();
        transitionSet.addTransition(bound);
        Transition changeImageTransform = new ChangeImageTransform();
        transitionSet.addTransition(changeImageTransform);
        transitionSet.setDuration(1000);
        TransitionManager.beginDelayedTransition(mRootView, transitionSet);
    }

    private void zoomOut() {
        ViewGroup.LayoutParams layoutParams = mImage.getLayoutParams();
        int width = layoutParams.width;
        int height = layoutParams.height;
        layoutParams.width = (int) (width / 2);
        layoutParams.height = height / 2;
        mImage.setLayoutParams(layoutParams);
        mImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TransitionSet transitionSet = new TransitionSet();
        Transition bound = new ChangeBounds();
        transitionSet.addTransition(bound);
        Transition changeImageTransform = new ChangeImageTransform();
        transitionSet.addTransition(changeImageTransform);
        transitionSet.setDuration(1000);
        TransitionManager.beginDelayedTransition(mRootView, transitionSet);
    }
}
