package com.example.yanyan.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by yanyan on 11/3/18.
 */

public class Login extends AppCompatActivity {

    private Context mContext;// context of the activity
    private Activity mActivity;

    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;
    private FloatingActionButton mFAB;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // 1 Get the app context
        mContext = getApplicationContext();

        //2 Get the activity
        mActivity = Login.this;

        //3 Get the widgets reference from your xml layout
        mButton = (Button) findViewById(R.id.loginbt);
        mTextView = (TextView)findViewById(R.id.welcome);
        mEditText = (EditText)findViewById(R.id.Pin);
        mImage = findViewById(R.id.imageView);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // change textView
                mTextView.setText(mEditText.getText()+", welcome back!");



            }

        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(mContext, Memory.class);
                startActivity(createIntent);
            }


        });

    }

}
