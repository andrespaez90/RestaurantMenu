package andres.dev.com.menurestaurant.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Adapter.RecycleItemAdapter;
import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.Model.ModelFacade;
import andres.dev.com.menurestaurant.R;
import andres.dev.com.menurestaurant.Utils.ActivityKeys;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemActivity extends ActionBarActivity {

    @InjectView(R.id.item_recView)
    RecyclerView ItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.inject( this );
        init();
    }

    private void init(){
        int idparent = getIntent().getIntExtra(ActivityKeys.INTENT_IDPARENT.toString(),-1);
        int idCategory =  getIntent().getIntExtra(ActivityKeys.INTENT_IDSUBCATEGORY.toString(),-1);

        if(idparent != -1 && idCategory != -1){
            createList(ModelFacade.findItem( idparent , idCategory ));
        }else
            finish();
    }

    private void createList(ArrayList<Category> list){

        ItemList.setHasFixedSize(true);
        final RecycleItemAdapter adaptador = new RecycleItemAdapter(list,this);

        ItemList.setAdapter(adaptador);
        ItemList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.items_action_car) {
            Intent intent = new Intent(this,CarActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public class MainController implements SearchView.OnQueryTextListener{

        @Override
        public boolean onQueryTextSubmit(String query) {
            ((RecycleItemAdapter) ItemList.getAdapter()).setFilter(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            ((RecycleItemAdapter) ItemList.getAdapter()).setFilter(newText);
            return false;
        }
    }

}
