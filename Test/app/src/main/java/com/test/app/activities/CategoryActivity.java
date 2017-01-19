package com.test.app.activities;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.test.app.R;
import com.test.app.activities.interfaces.IFilterRegister;
import com.test.app.activities.interfaces.IFilterSubscriber;
import com.test.app.fragments.AppFragment;

import java.util.HashSet;
import java.util.Set;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Actividad que muestra las actividades por categorias
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
@ContentView(R.layout.activity_home)
public class CategoryActivity extends RoboActionBarActivity implements IFilterRegister {

    /** Toolbar **/
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /**
     * TextView del título del toolbar
     */
    @InjectView(R.id.title_toolbar_tv)
    private TextView mTitleToolbarTv;

    /** Filtro subscribers **/
    private Set<IFilterSubscriber> subscribers = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitleToolbarTv.setText(R.string.title_app);

        ViewCompat.setElevation(mToolbar, getResources().getDimension(R.dimen.toolbar_elevation));

        Fragment fragment = null;

        fragment = AppFragment.newInstance();

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content_rl, fragment).commit();
        }
    }

    /**
     * This method registers a "subscriber" from filter actions
     *
     * @param subscriber
     *         The subscriber
     */
    @Override
    public void register(IFilterSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * This interfaces is used to request basic fragment information
     */
    public interface IFragmentInfo {

        /**
         * This method returns the Fragment Name Resource Id
         *
         * @return The String Res Id of the name of the fragment
         */
        @StringRes
        int getMenuNameId();
    }

    /**
     * This method unregister a subscriber from filter actions
     *
     * @param subscriber
     *         The subscriber to be unregistered
     */
    @Override
    public void unregister(IFilterSubscriber subscriber) {
        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        }
    }

    /**
     * Metodo que finaliza la actividad
     */
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
