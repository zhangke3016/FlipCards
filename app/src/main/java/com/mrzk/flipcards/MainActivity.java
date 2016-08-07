package com.mrzk.flipcards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FlipCardAnimation animation1;
    FlipCardAnimation animation;
    FlipCardAnimation animation_item;
    FlipCardAnimation animation_item1;
    int num= 0;
    ImageView iv_pro,iv_pro_item,iv_pro_item1;

    private static final int[] DRAWABLE = {R.drawable.ic_card_giftcard_white_48dp,R.drawable.ic_card_membership_white_48dp,R.drawable.ic_markunread_white_48dp};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout llyt = (LinearLayout) findViewById(R.id.llyt);
        final LinearLayout llyt_item = (LinearLayout) findViewById(R.id.llyt_item);
        final LinearLayout llyt_item1 = (LinearLayout) findViewById(R.id.llyt_item1);
        final View view_bg =  findViewById(R.id.view_bg);

        iv_pro = (ImageView) findViewById(R.id.iv_pro);
        iv_pro_item = (ImageView) findViewById(R.id.iv_pro_item);
        iv_pro_item1 = (ImageView) findViewById(R.id.iv_pro_item1);
        final TextView tv_item = (TextView) findViewById(R.id.tv_item);
        final TextView tv_item1 = (TextView) findViewById(R.id.tv_item1);
        final TextView tv_price = (TextView) findViewById(R.id.tv_price);
        final TextView tv_price_item = (TextView) findViewById(R.id.tv_price_item);
        final TextView tv_price_item1 = (TextView) findViewById(R.id.tv_price_item1);
        final TextView tv = (TextView) findViewById(R.id.tv);

        llyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnimation(animation,llyt, tv, tv_price,iv_pro,180);
                startAnimation(animation1,view_bg, null, null,null,-180);
            }
        });

        llyt_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(animation_item, llyt_item, tv_item, tv_price_item, iv_pro_item, 180);
            }
        });
        llyt_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(animation_item1, llyt_item1, tv_item1, tv_price_item1, iv_pro_item1, -180);
            }
        });
    }

    private void startAnimation(FlipCardAnimation animation, View llyt_item, final TextView tv_item, final TextView tv_price_item, final ImageView iv_pro, int degree) {
        if (animation != null) {
            animation.setCanContentChange();
            llyt_item.startAnimation(animation);
        } else {
            int width = llyt_item.getWidth() / 2;
            int height = llyt_item.getHeight() / 2;
            animation = new FlipCardAnimation(0, degree, width, height);
            animation.setInterpolator(new AnticipateOvershootInterpolator());
            animation.setDuration(3000);
            animation.setFillAfter(false);
            animation.setRepeatCount(-1);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                    ((FlipCardAnimation)animation).setCanContentChange();
                }
            });
            animation.setOnContentChangeListener(new FlipCardAnimation.OnContentChangeListener() {
                @Override
                public void contentChange() {
                    if (iv_pro == null) {
                        return;
                    }
                    if (num >= 3) {
                        num = 0;
                    }
                    iv_pro.setBackgroundResource(DRAWABLE[num]);
                    tv_item.setText("￥" + new Random().nextInt(500));

                    if (num == 0) {
                        tv_price_item.setText("Discount");
                    } else if (num == 1) {
                        tv_price_item.setText("Price");
                    } else if (num == 2) {
                        tv_price_item.setText("Cost");
                    }
                    num++;
                }
            });
            llyt_item.startAnimation(animation);
        }
    }
}
