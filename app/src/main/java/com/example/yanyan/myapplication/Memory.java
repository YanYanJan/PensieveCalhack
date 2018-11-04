package com.example.yanyan.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanyan on 11/3/18.
 */

public class Memory extends AppCompatActivity {
    private TextView mTextMessage;
    private Context mContext;

    private boolean isMenuOpen = false;
    private int[] res = {R.id.create, R.id.sketchMem, R.id.cameraMem, R.id.audioMem, R.id.textMem};
    private List<ImageView> imageViewList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_geo);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_unread);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_memo);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory);
        mContext = this;

        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView)findViewById(res[i]);
            imageViewList.add(imageView);
        }
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.create:
                if (!isMenuOpen) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
            case R.id.cameraMem:
                Intent cameraIntent = new Intent(mContext, Create.class);
                startActivity(cameraIntent);
        }

    }

    private void startAnim() {
        for (int i = 1; i < res.length; i++) {
            imageViewList.get(i).setVisibility(View.VISIBLE);
            float angle = (90 + 180 * 1.0f / (res.length - 2) * (i - 1));
            PropertyValuesHolder h1 = PropertyValuesHolder.ofFloat("translationX", 0, (float) Math.sin(angle * 1.57 / 90) * 200);
            PropertyValuesHolder h2 = PropertyValuesHolder.ofFloat("translationY", 0, (float) Math.cos(angle * 1.57 / 90) * 200);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageViewList.get(i), h1, h2);
            animator.setDuration(1000);
            animator.setFrameDelay(500 * i);
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
            isMenuOpen = true;
        }
    }

    private void closeAnim() {
        for (int i = 1; i < res.length; i++) {
            float angle = (90 + 180 * 1.0f / (res.length - 2) * (i - 1));
            PropertyValuesHolder h1 = PropertyValuesHolder.ofFloat("translationX",  (float) Math.sin(angle * 1.57 / 90) * 200, 0);
            PropertyValuesHolder h2 = PropertyValuesHolder.ofFloat("translationY", (float) Math.cos(angle * 1.57 / 90) * 200, 0);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageViewList.get(i), h1, h2);
            animator.setDuration(500);
            animator.start();
            isMenuOpen = false;
        }
    }
}
