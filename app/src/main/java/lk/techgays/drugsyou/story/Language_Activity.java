package lk.techgays.drugsyou.story;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lk.techgays.drugsyou.R;

/**
 * * Created by Isuru Chanaka on 2018-02-22.
 **/

public class Language_Activity extends Activity {

    private TextView txt_sinhala;
    private TextView txt_tamil;
    private ImageView languageActLogo;
    private Animation zoomin;
    private Animation zoomout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activty);


        Typeface tf = Typeface.createFromAsset(getApplication().getAssets(), "fonts/NotoSansSinhala-Regular.ttf");
        txt_sinhala= findViewById(R.id.txtSinhala);
        txt_tamil= findViewById(R.id.txtTamil);
        TextView textAbout = findViewById(R.id.textAbout);
        Button btn_sinhala = findViewById(R.id.btn_sinhala);
        Button btn_tamil = findViewById(R.id.btn_tamil);
        languageActLogo = findViewById(R.id.imageView);

        txt_sinhala.setTypeface(tf);
        txt_tamil.setTypeface(tf);
        btn_sinhala.setTypeface(tf);
        btn_tamil.setTypeface(tf);

        btn_sinhala.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Category_Activity.class);
                i.putExtra("LanguageId", "SI");
                startActivity(i);
                finish();
            }
        });

        btn_tamil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Category_Activity.class);
                i.putExtra("LanguageId", "TM");
                startActivity(i);
                finish();
                //Toast.makeText(getApplicationContext(), "not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });

        textAbout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(i);

            }
        });

        setAnimation();


    }


    private void setAnimation() {

        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(txt_sinhala, "scaleX", 5.0F, 1.0F);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1200);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(txt_sinhala, "scaleY", 5.0F, 1.0F);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(1200);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(txt_sinhala, "alpha", 0.0F, 1.0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(1200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(500);
        animatorSet.start();

        txt_tamil.setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_center);
        txt_tamil.startAnimation(anim);
        // animation
        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        languageActLogo.setAnimation(zoomin);
        languageActLogo.setAnimation(zoomout);

        zoomin.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                languageActLogo.startAnimation(zoomout);

            }
        });

        zoomout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                languageActLogo.startAnimation(zoomin);

            }
        });


    }
}
