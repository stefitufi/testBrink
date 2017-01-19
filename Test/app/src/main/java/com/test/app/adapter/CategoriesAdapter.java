package com.test.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.test.app.R;
import com.test.app.activities.DetailActivity;
import com.test.app.model.App;
import com.test.app.model.Category;
import com.test.app.network.services.api.IAppsService;
import com.test.app.utils.AttrsManager;
import com.test.app.utils.ImageUtils;
import com.test.app.utils.AppUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * Adapter correspondiente a las categorias
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class CategoriesAdapter extends ArrayAdapter<Category> {

    /** The Layout Inflater **/
    private LayoutInflater inflater;

    /** Apps Service **/
    @Inject
    private IAppsService appsService;

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param objects
     *         The objects to represent in the ListView.
     */
    public CategoriesAdapter(Context context, List<Category> objects) {
        super(context, R.layout.category_snap_item_list, objects);
        injectFields(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * This method injects the custom fields
     *
     * @param context
     *         App context
     */
    private void injectFields(Context context) {
        final RoboInjector injector = RoboGuice.getInjector(context);
        // This will inject all fields marked with the @Inject annotation
        injector.injectMembersWithoutViews(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        Category currentCategory = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.category_snap_item_list, parent, false);
            holder = new Holder();
            holder.title = (TextView) convertView.findViewById(R.id.category_title_rtv);
            // holder.caption = (TextView) convertView.findViewById(R.id.category_caption_rtv);
            holder.container = (LinearLayout) convertView.findViewById(R.id.apps_container_ll);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.container.removeAllViews();
        for (App app : appsService.getAppsByCategory(currentCategory.getId(), 10)) {
            holder.container.addView(createAppGoogleCard(app, holder.container));
        }

        holder.title.setText(currentCategory.getLabel());
        //holder.caption.setText(R.string.recommended_you);

        return convertView;
    }

    /**
     * This method creates a Google Card based on a given app
     *
     * @param app
     *         App to be converted to Google Card
     *
     * @return The Google Card
     */
    private View createAppGoogleCard(final App app, ViewGroup parent) {
        View card = inflater.inflate(R.layout.app_google_card, parent, false);


        String url = AttrsManager.getLabel(app.getImages()[2]);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getContext()));
        final ImageView appThumb = (ImageView) card.findViewById(R.id.app_image_siv);
        ImageUtils.displayImage(appThumb, url, null);

        final TextView appTitleTv = (TextView) card.findViewById(R.id.app_title_rtv);
        appTitleTv.setText(AttrsManager.getLabel(app.getName()));

        TextView appCaptionTv = (TextView) card.findViewById(R.id.app_caption_rtv);
        appCaptionTv.setText(AttrsManager.getLabel(app.getSummary()));

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appDetailIntent = new Intent(getContext(), DetailActivity.class);
                appDetailIntent.putExtra(DetailActivity.SELECTED_APP_KEY, app);
                if (AppUtils.isGraterLollipop()) {
                    String transitionView = getContext().getString(R.string.transition_view);
                    String transitionTextView =
                            getContext().getString(R.string.transition_textview);
                    Pair<View, String>[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(appThumb, transitionView);
                    pairs[1] = new Pair<View, String>(appTitleTv, transitionTextView);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation((Activity) getContext(), pairs);
                    getContext().startActivity(appDetailIntent, options.toBundle());
                } else {
                    getContext().startActivity(appDetailIntent);
                }
            }
        });

        return card;
    }

    /**
     * View Holder pattern
     */
    private class Holder {

        TextView title;
        TextView caption;
        LinearLayout container;

    }
}
