package lk.techgays.drugsyou.story;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import lk.techgays.drugsyou.R;

public class Splash_Activity extends Activity {
	private TextView WelcomeTextEng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		//WelcomeTextSin= findViewById(R.id.txtWelcomeTextSin);
		WelcomeTextEng= findViewById(R.id.txtWelcomeTextEng);
		setAnimation();

		int SPLASH_TIME_OUT = 3000;
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(Splash_Activity.this, Language_Activity.class);
				startActivity(i);

				finish();
			}
		}, SPLASH_TIME_OUT);

/*		Thread splashThread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (waited < 2000) {
						sleep(100);
						waited += 100;
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {

					Intent i = new Intent(getApplicationContext(),Language_Activity.class);
					startActivity(i);
					finish();
					 
				}
			}
		};
		splashThread.start();*/
	}

	private void setAnimation() {

		/*ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(WelcomeTextSin, "scaleX", 5.0F, 1.0F);
		scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		scaleXAnimation.setDuration(1200);
		ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(WelcomeTextSin, "scaleY", 5.0F, 1.0F);
		scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		scaleYAnimation.setDuration(1200);
		ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(WelcomeTextSin, "alpha", 0.0F, 1.0F);
		alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		alphaAnimation.setDuration(1200);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
		animatorSet.setStartDelay(500);
		animatorSet.start();*/

		WelcomeTextEng.setAlpha(1.0F);
		Animation anim0 = AnimationUtils.loadAnimation(this, R.anim.zoomin);
		WelcomeTextEng.startAnimation(anim0);

		findViewById(R.id.imageSplash_Logo).setAlpha(1.0F);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_bottom_to_center);
		findViewById(R.id.imageSplash_Logo).startAnimation(anim);
	}
	
}