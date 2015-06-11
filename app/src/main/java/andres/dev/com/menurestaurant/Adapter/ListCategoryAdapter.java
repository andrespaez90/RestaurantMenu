package andres.dev.com.menurestaurant.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.R;

/**
 * Created by INNSO SAS on 11/06/2015.
 */
public class ListCategoryAdapter extends BaseAdapter {

        private ArrayList<Category> visibleItems;
        private ArrayList<Category> allItems;
        private int LayoutContent;
        private Context mContext;
        private int minHeigt;

        public ListCategoryAdapter(Context contexto, int R_layout_IdView, ArrayList<Category> entradas) {
            super();
            this.mContext = contexto;
            this.allItems = entradas;
            this.visibleItems = allItems;
            this.LayoutContent = R_layout_IdView;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int posicion, View view, ViewGroup pariente) {
            if (view == null) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(LayoutContent, null);
                LinearLayout ly = (LinearLayout)view.findViewById(R.id.linearContent);
                minHeigt = ly.getMinimumHeight();
            }
            onEntrada (visibleItems.get(posicion), view);
            return view;
        }

        @Override
        public int getCount() {
            return visibleItems.size();
        }

        @Override
        public Object getItem(int posicion) {
            return visibleItems.get(posicion);
        }

        @Override
        public long getItemId(int posicion) {
            return posicion;

        }

    public void setFilter(String queryText) {

        visibleItems = new ArrayList<>();
        for (Category item: allItems) {
            if (item.getName().toLowerCase().contains(queryText))
                visibleItems.add(item);
        }
        notifyDataSetChanged();
    }


    public void onEntrada (Category category, View view){

        if (category != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.imagelist_img);
            TextView textView = (TextView)view.findViewById(R.id.imagelist_text);
            Picasso.with(mContext).load("http://192.237.180.31/archies/public/" + category.getImagePath()).placeholder(R.drawable.bg_load).into(imageView);
            textView.setText(category.getName());

            ArrayList<Category> subCategory = category.getSubCategory();
            ListView subList = (ListView) view.findViewById(R.id.main_listConten);
            ListSubCategoryAdaper adapter = new ListSubCategoryAdaper(mContext, R.layout.list_subcategory, subCategory);
            subList.setAdapter( adapter );
            subList.setOnItemClickListener( adapter );

            LinearLayout ly = (LinearLayout)view.findViewById(R.id.linearContent);
            ly.setMinimumHeight(minHeigt* subCategory.size()-1);

        }
    }

}
