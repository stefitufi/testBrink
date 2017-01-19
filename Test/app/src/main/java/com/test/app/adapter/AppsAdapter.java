package com.test.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.app.R;
import com.test.app.model.App;
import com.test.app.utils.AttrsManager;
import com.test.app.utils.ImageUtils;
import com.test.app.utils.AppUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter correspondiente a la lista de aplicaciones
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class AppsAdapter extends ArrayAdapter<App> {

    /** Tag for Logs **/
    private static final String TAG_LOG = AppsAdapter.class.getName();

    /** The original dataset **/
    private final List<App> mApps;

    /** The filter to be applied **/
    private final Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            String[] words = constraint.toString().split(" +");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].toUpperCase();
            }

            List<App> apps = findMatches(words);

            results.count = apps.size();
            results.values = apps;

            Log.d(TAG_LOG, String.format("Filtered apps: %s", apps));

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List<App>) results.values);
            notifyDataSetChanged();
        }
    };

    /** Layout Inflater **/
    private LayoutInflater mInflater;

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param apps
     *         The objects to represent in the ListView.
     */
    public AppsAdapter(Context context, App[] apps) {
        super(context, R.layout.app_google_card, apps);
        mApps = new ArrayList<>(apps.length);
        Collections.addAll(mApps, apps);
        init();
    }

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param apps
     *         The objects to represent in the ListView.
     */
    public AppsAdapter(Context context, List<App> apps) {
        super(context, R.layout.app_google_card, apps);
        mApps = new ArrayList<>(apps);
        init();
    }

    /**
     * This method find matches for an array of given words. All words must to be contained in the
     * App's name
     *
     * @param words
     *         The words to be matched
     *
     * @return List of matched apps
     */
    @NonNull
    private List<App> findMatches(String[] words) {
        List<App> apps = new ArrayList<App>();
        for (App app : mApps) {
            String appName = AttrsManager.getLabel(app.getName()).toUpperCase();
            boolean match = true;
            for (int i = 0; i < words.length && match; i++) {
                match = appName.contains(words[i]);
            }
            if (match) {
                apps.add(app);
            }
        }
        return apps;
    }

    /**
     * Inits the basic fields
     */
    private void init() {
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            int layout = AppUtils.isTablet(getContext()) ? R.layout.app_google_card :
                    R.layout.app_list_item;
            convertView = mInflater.inflate(layout, parent, false);
            holder = new Holder();

            holder.image = (ImageView) convertView.findViewById(R.id.app_image_siv);
            holder.title = (TextView) convertView.findViewById(R.id.app_title_rtv);
            holder.caption = (TextView) convertView.findViewById(R.id.app_caption_rtv);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        App app = getItem(position);

        ImageUtils.displayImage(holder.image, AttrsManager.getLabel(app.getImages()[2]), null);
        holder.title.setText(AttrsManager.getLabel(app.getName()));
        holder.caption.setText(AttrsManager.getLabel(app.getSummary()));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    /**
     * This is the Holder class for HolderView pattern
     */
    private class Holder {
        /** App image **/
        ImageView image;
        /** App title **/
        TextView title;
        /** App caption **/
        TextView caption;
    }
}
