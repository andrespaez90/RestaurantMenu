package andres.dev.com.menurestaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.R;
import andres.dev.com.menurestaurant.UI.ItemActivity;
import andres.dev.com.menurestaurant.Utils.ActivityKeys;


public class ListSubCategoryAdaper extends BaseAdapter implements AdapterView.OnItemClickListener {

    private ArrayList<Category> allItems;
    private int LayoutContent;
    private Context mContext;

    public ListSubCategoryAdaper(Context contexto, int R_layout_IdView, ArrayList<Category> entradas) {
        super();
        this.mContext = contexto;
        this.allItems = entradas;
        this.LayoutContent = R_layout_IdView;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(LayoutContent, null);

        }
        onEntrada (allItems.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return allItems.size();
    }

    @Override
    public Object getItem(int posicion) {
        return allItems.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;

    }

    public void onEntrada (Category category, View view){
        if(category != null){
            TextView textName = (TextView)view.findViewById(R.id.subcategory_name);
            textName.setText( category.getName() );
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Category subCategory = (Category) parent.getItemAtPosition(position);
        Intent intent = new Intent(mContext, ItemActivity.class);
        intent.putExtra( ActivityKeys.INTENT_IDPARENT.toString(), subCategory.getParent().getId() );
        intent.putExtra( ActivityKeys.INTENT_IDSUBCATEGORY.toString(), subCategory.getId() );
        mContext.startActivity(intent);
    }
}
