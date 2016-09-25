package com.example.administrator.animator;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private final long TIME_INTERVAL = 4000L;
    private ViewAnimator viewAnimator;
    private Button buttonNext;
    private Button buttonPrevious;
    private Button buttonAuto;

    private boolean autoPlayFlag = false;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(autoPlayFlag){
                showNext();
            }
            handler.sendMessageDelayed(new Message(),TIME_INTERVAL);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAnimator = (ViewAnimator) this.findViewById(R.id.animator);
        buttonNext = (Button) this.findViewById(R.id.btn_next);
        buttonPrevious = (Button) this.findViewById(R.id.btn_previous);
        buttonAuto = (Button) this.findViewById(R.id.btn_auto);

        handler.sendMessageDelayed(new Message(),TIME_INTERVAL);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }
        });
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrevious();
            }
        });
        buttonAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoPlayFlag = true;
            }
        });
    }
    public void showNext(){
        viewAnimator.setOutAnimation(this,R.anim.slide_out_up);
        viewAnimator.setInAnimation(this,R.anim.slide_in_down);
        viewAnimator.showNext();
    }
    public void showPrevious(){
        viewAnimator.setOutAnimation(this,R.anim.slide_out_down);
        viewAnimator.setInAnimation(this,R.anim.slide_in_up);
        viewAnimator.showPrevious();
    }
}
