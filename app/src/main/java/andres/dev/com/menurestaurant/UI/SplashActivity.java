package andres.dev.com.menurestaurant.UI;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import andres.dev.com.menurestaurant.MenuApplication;
import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.Model.ModelFacade;
import andres.dev.com.menurestaurant.Provider.JSONHander;
import andres.dev.com.menurestaurant.Provider.SharePreference;
import andres.dev.com.menurestaurant.R;
import andres.dev.com.menurestaurant.Utils.JSONkeys;
import andres.dev.com.menurestaurant.Utils.SharedPrefKey;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class SplashActivity extends Activity {

    @InjectView(R.id.splash_load)
    ShimmerTextView textviewLoad;

    @InjectView(R.id.splash_text)
    TextView textviewText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        startAnim();
        init();
    }

    private void init() {
        new UpdateData().execute("https://www.getpostman.com/collections/2a9f34a396ef5cd2822a");
    }


    private void startAnim() {
        Shimmer shimmer = new Shimmer();
        shimmer.start(textviewLoad);
    }


    private class UpdateData extends AsyncTask<String, String, String > {

        protected String doInBackground(String... urls) {
           try{
               String result = JSONHander.readJSON(urls[0]);
               JSONObject jsonObject = new JSONObject(result);
               String app_timestamp = SharePreference.getApplicationValue(SharedPrefKey.APP_TIMESTAMP);

               if( app_timestamp.equals(jsonObject.get(JSONkeys.JSON_TIMESTAMP.toString())) ){

               }else{
                   publishProgress("Descargando Actualizaciones");
                   JSONArray jsonArray = jsonObject.getJSONArray("requests");

                   JSONObject jObjectCategory = jsonArray.getJSONObject(1);
                   String responseCategory = JSONHander.readJSON(jObjectCategory.getString(JSONkeys.JSON_URL.toString()));
                   jObjectCategory = jsonArray.getJSONObject(0);
                   String urlSubcategory = jObjectCategory.getString(JSONkeys.JSON_URL.toString());
                   urlSubcategory = urlSubcategory.substring(0,urlSubcategory.length()-1);

                   createCategory( responseCategory, urlSubcategory );

                   return "Success";
               }

           }
           catch (Exception e) {
               Log.d("ReadWeatherJSONFeedTask", e.getMessage());
           }
            return null;
        }

        public void createCategory(String responseCategory, String urlSubCategory) throws JSONException {

            JSONArray JArrayData = new JSONArray(responseCategory);
            Category category;
            for( int i =0; i < JArrayData.length();i++){
                JSONObject object =  JArrayData.getJSONObject(i);
                category = createCategory(object);
                ModelFacade.Categoties.add( addSubCategories(category, urlSubCategory) );
            }
        }

        private Category addSubCategories(Category category, String url) throws JSONException {
            String resultSubCategory = JSONHander.readJSON( url+category.getId() );
            JSONObject JSONObjecto = new JSONObject( resultSubCategory );
            JSONArray SubCategories = JSONObjecto.getJSONArray( JSONkeys.JSON_SUBCATEGORY.toString() );

            JSONObject SubCat;
            for( int i =0; i < SubCategories.length(); i++ ){
                SubCat = SubCategories.getJSONObject(i);
                category.addCategory( createSubCategory(SubCat, category) );
            }
            return  category;
        }

        private Category createCategory(JSONObject jObject) throws JSONException {
            Category a = new Category();
            a.setId(jObject.getInt(JSONkeys.JSON_ID.toString()));
            a.setName(jObject.getString(JSONkeys.JSON_NAME.toString()));
            a.setImagePath(jObject.getString(JSONkeys.JSON_IMAGE.toString()));
            return a;
        }

        private Category createSubCategory(JSONObject jObject, Category category) throws JSONException {
            Category a = new Category();
            a.setId(jObject.getInt(JSONkeys.JSON_ID.toString()));
            a.setName(jObject.getString(JSONkeys.JSON_NAME.toString()));
            a.setParent(category);

            JSONArray jArray = jObject.getJSONArray( JSONkeys.JSON_ITEMS.toString() );
            for(int i =0 ; i < jArray.length() ; i++ ){
                JSONObject jItem = jArray.getJSONObject(i);
                a.addCategory( createItem( jItem, a ) );
            }

            return a;
        }

        private Category createItem(JSONObject jObject,  Category parent) throws JSONException {
            Category a = new Category();
            a.setId(jObject.getInt(JSONkeys.JSON_ID.toString()));
            a.setName(jObject.getString(JSONkeys.JSON_NAME.toString()));
            a.setImagePath(jObject.getString(JSONkeys.JSON_IMAGE.toString()));
            a.setParent(parent);
            a.setDescription( jObject.getString(JSONkeys.JSON_DESCRIPTION .toString()) );
            return a;
        }



        protected void onProgressUpdate(String... progress) {
            textviewText.setText(progress[0]);
        }

        protected void onPostExecute(String result) {
            if(result != null) {
                SplashActivity.this.setResult(RESULT_OK);
                SplashActivity.this.finish();
            }
        }

    }
}

