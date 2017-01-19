package com.test.app.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.app.R;
import com.test.app.utils.AppAttribute;
import com.test.app.model.App;
import com.test.app.utils.AttrsManager;
import com.test.app.utils.ImageUtils;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Actividad que muestra el detalle del app seleccionado
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
@ContentView(R.layout.activity_app_detail_content)
public class DetailActivity extends RoboActionBarActivity {

    /** Llave del app seleccionada **/
    public static final String SELECTED_APP_KEY = "SELECTED_APP_KEY";

    /** Variable formato de moneda **/
    private static final String CURRENCY_FORMAT = "%1$,.2f";

    /** Tag logs **/
    private static final String TAG_LOG = DetailActivity.class.getName();

    /** Toolbar **/
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /**
     * TextView del título del toolbar
     */
    @InjectView(R.id.title_toolbar_tv)
    private TextView mTitleToolbarTv;

    /** ImageView de la aplicacion **/
    @InjectView(R.id.app_image_siv)
    private ImageView mAppImageSiv;

    /** TextView que muestra si es free o pago **/
    @InjectView(R.id.price_rtv)
    private TextView mPriceAppRtv;

    /** TextView que contiene el resumen**/
    @InjectView(R.id.summary_content_tv)
    private TextView mSummaryContentTv;

    /** Titulo del app **/
    @InjectView(R.id.app_title_rtv)
    private TextView mAppTitleRtv;

    /** TextView rights **/
    @InjectView(R.id.app_rights_rtv)
    private TextView mRightsRtv;

    /** Variable de tipo App **/
    private App mSelectedApp;

    /** Nombre aplicacion seleccionada **/
    private String mNameApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mSelectedApp = (App) getIntent().getSerializableExtra(SELECTED_APP_KEY);
        mNameApp = AttrsManager.getLabel(mSelectedApp.getName());

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

        //Metodo que carga la informacion
        loadApp();
    }

    /**
     *Evento que carga la informacion del app seleccionado
     */
    private void loadApp() {
        mTitleToolbarTv.setText(mNameApp);
        mAppTitleRtv.setText(mNameApp);
        mSummaryContentTv.setText(AttrsManager.getLabel(mSelectedApp.getSummary()));
        String url = AttrsManager.getLabel(mSelectedApp.getImages()[2]);
        ImageUtils.displayImage(mAppImageSiv, url, null);

        float price = AttrsManager.getFloat(mSelectedApp.getPrice(), AppAttribute.AMOUNT);
        String priceStr = (price == 0.0f ? getString(R.string.free_caps) :
                String.format(CURRENCY_FORMAT, price));
        mPriceAppRtv.setText(priceStr);
        mRightsRtv.setText(AttrsManager.getLabel(mSelectedApp.getRights()));
    }

    /**
     * This activity finishes properly the activity
     */
    protected void finishActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    /**
     * Metodo que finaliza la actividad
     */
    @Override
    public void onBackPressed() {
       finishActivity();
    }
}
