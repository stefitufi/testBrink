package com.test.app.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.test.app.R;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * Actividad del Splash
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends RoboActionBarActivity {

    /** Constante que contiene duracion de la animacion **/
    private static final int ANIM_DURATION = 1000;

    /** Constante que retrasa la vista del splash **/
    private static final int SPLASH_DELAY = 1500;

    /** ImageView del logo **/
    @InjectView(R.id.ic_logo_iv)
    private ImageView mLogoIv;

    /** Transition Name **/
    @InjectResource(R.string.transition_view)
    private String mTransitionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fadeAnimation(mLogoIv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goHome();
            }
        }, SPLASH_DELAY);
    }

    /**
     * Este metodo inicializa el evento de la actividad
     */
    private void goHome() {
        Intent loginIntent = new Intent(SplashActivity.this, CategoryActivity.class);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mLogoIv, mTransitionView);
        startActivity(loginIntent, options.toBundle());
    }

    /**
     * Metodo que realiza la animacion
     */
    private void fadeAnimation(View view) {
        ObjectAnimator scaleXAnimation = createObjectAnimator(view, "scaleX", 5.0F, 1.0F);
        ObjectAnimator scaleYAnimation = createObjectAnimator(view, "scaleY", 5.0F, 1.0F);
        ObjectAnimator alphaAnimation = createObjectAnimator(view, "alpha", 0.0F, 1.0F);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.start();
    }

    /**
     * Metodo que crea el objeto de animacion
     * animated and the initial value and final value
     *
     * @param view
     *         Target view
     * @param property
     *         Property to be animated
     * @param init
     *         Initial value
     * @param end
     *         Final value
     *
     * @return ObjectAnimator with the given animated property
     */
    @NonNull
    private ObjectAnimator createObjectAnimator(View view, String property, float init, float end) {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, property, init, end);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(ANIM_DURATION);
        return scaleXAnimation;
    }
}
