
#FlipCards
>A 3D Flip Card for Android

>创意来自[**Dribbble**](https://dribbble.com)

#Preview

![FlipCards](gif/screen.gif)

---
###Usage
```
animation = new FlipCardAnimation(0, degree, width, height);
            animation.setInterpolator(new AnticipateOvershootInterpolator());
            animation.setDuration(3000);
            animation.setFillAfter(false);
            animation.setRepeatCount(-1);//设置无限循环
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                    ((FlipCardAnimation)animation).setCanContentChange();//如果设置循环，务必在这里添加这行代码
                }
            });
            animation.setOnContentChangeListener(new FlipCardAnimation.OnContentChangeListener() {
                @Override
                public void contentChange() {
                    if (iv_pro == null) {
                        return;
                    }
                    iv_pro.setBackgroundResource(DRAWABLE[num]);
                    tv_item.setText("￥" + new Random().nextInt(500));
                    tv_price_item.setText("Discount");
                }
            });
            llyt_item.startAnimation(animation);
```
###About me
---
An Android Developer in ZhengZhou.

【[**我的简书地址**](http://www.jianshu.com/users/3c751e06dc32/latest_articles)】

【[**我的CSDN地址**](http://blog.csdn.net/zhangke3016)】

---
###License
