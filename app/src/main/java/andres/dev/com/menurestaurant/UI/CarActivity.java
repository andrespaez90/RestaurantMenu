package andres.dev.com.menurestaurant.UI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Adapter.RecycleCarAdapter;
import andres.dev.com.menurestaurant.Adapter.RecycleItemAdapter;
import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.Model.ModelFacade;
import andres.dev.com.menurestaurant.Model.itemSelect;
import andres.dev.com.menurestaurant.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CarActivity extends ActionBarActivity {

    @InjectView((R.id.car_recView))
    RecyclerView itemList;

    @InjectView(R.id.car_continue)
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        ButterKnife.inject(this);
        createList(ModelFacade.ItemsCar);
    }

    private void createList(ArrayList<itemSelect> list){

        //Inicializacion RecyclerView
        itemList.setHasFixedSize(true);
        RecycleCarAdapter adaptador = new RecycleCarAdapter(list,this);



        itemList.setAdapter(adaptador);
        itemList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    @OnClick(R.id.car_continue)
    public void continueShop(View view) {
       finish();
    }

}
