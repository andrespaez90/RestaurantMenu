package andres.dev.com.menurestaurant.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Adapter.ListCategoryAdapter;
import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.Model.ModelFacade;
import andres.dev.com.menurestaurant.R;
import andres.dev.com.menurestaurant.Utils.ActivityKeys;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.subcat_list)
    ExpandableLayoutListView ExpandableList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        startSplash();
    }

    private void init(){
        createLista(ModelFacade.Categoties);
    }

    private void startSplash (){
        Intent intent = new Intent(this, SplashActivity.class);
        startActivityForResult(intent, ActivityKeys.SPLASH_ACTIVITY.ordinal());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == ActivityKeys.SPLASH_ACTIVITY.ordinal()){
            if(resultCode == RESULT_OK)
                init();
            else
                finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchview = (SearchView)(menu.findItem(R.id.main_action_search).getActionView());
        searchview.setQueryHint("Buscar");
        searchview.setOnQueryTextListener(new MainController());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public class MainController implements SearchView.OnQueryTextListener{

        @Override
        public boolean onQueryTextSubmit(String query) {
            ((ListCategoryAdapter) ExpandableList.getAdapter()).setFilter(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            ((ListCategoryAdapter) ExpandableList.getAdapter()).setFilter(newText);
            return false;
        }
    }


    private void createLista(ArrayList<Category> datos) {
        ExpandableList.setAdapter(new ListCategoryAdapter(this, R.layout.list_e_row, datos));
    }
}
