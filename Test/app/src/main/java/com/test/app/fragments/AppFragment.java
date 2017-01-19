package com.test.app.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.google.inject.Inject;
import com.test.app.R;
import com.test.app.activities.CategoryActivity;
import com.test.app.model.Category;
import com.test.app.network.services.api.IAppsService;
import com.test.app.utils.ProgressWheel;
import com.test.app.utils.ViewUtils;
import com.test.app.adapter.CategoriesAdapter;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.List;
import java.util.Random;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Fragment que muestra la lista de apps por categorias
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class AppFragment extends RoboFragment implements CategoryActivity.IFragmentInfo {

    /** Categories DynamicListView **/
    @InjectView(R.id.categories_dlv)
    private DynamicListView mCategoriesDlv;

    /** Loading ProgressWheel **/
    @InjectView(R.id.loading_pw)
    private ProgressWheel mLoadingPw;

    /** Apps Service **/
    @Inject
    private IAppsService appsService;

    /** Default constructor **/
    public AppFragment() {
        // Required empty public constructor
    }

    /**
     * This method creates a new Whole World Fragment instance
     */
    public static AppFragment newInstance() {
        return new AppFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new InitialLoadingAsyncTask().execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * This method enables/disables the loading Progress Wheel. This hides the Categories List View
     * if it is necessary
     *
     * @param enable
     *         True to enable/show and False to disable/hide the Progress Wheel
     */
    private void enableProgressWheel(boolean enable) {
        mCategoriesDlv.setVisibility(enable ? View.GONE : View.VISIBLE);
        mLoadingPw.setVisibility(enable ? View.VISIBLE : View.GONE);

        if (mLoadingPw.isSpinning()) {
            mLoadingPw.stopSpinning();
        }

        if (enable) {
            mLoadingPw.spin();
        }
    }

    @Override
    public int getMenuNameId() {
        return 0;
    }

    /**
     * This class does the initial loading, that is load all categories and its views
     */
    private class InitialLoadingAsyncTask extends AsyncTask<Void, Void, List<Category>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            enableProgressWheel(true);
        }

        @Override
        protected List<Category> doInBackground(Void... params) {
            return appsService.getCategories();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            super.onPostExecute(categories);
            enableProgressWheel(false);
            if (categories == null) {
                ViewUtils.makeToast(getActivity(), R.string.error_loading,
                        SuperToast.Duration.EXTRA_LONG, Style.RED).show();
                return;
            }
            CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), categories);
            AnimationAdapter animAdapter = ViewUtils
                    .animateAdapter(new Random().nextInt(5), adapter);
            animAdapter.setAbsListView(mCategoriesDlv);
            mCategoriesDlv.setAdapter(animAdapter);
        }
    }
}
