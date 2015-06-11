package andres.dev.com.menurestaurant.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.Model.ModelFacade;
import andres.dev.com.menurestaurant.Model.itemSelect;
import andres.dev.com.menurestaurant.R;

/**
 * Created by INNSO SAS on 10/06/2015.
 */
public class RecycleCarAdapter extends RecyclerView.Adapter<RecycleCarAdapter.DataViewHolder> {


        private ArrayList<itemSelect> visibleItems;
        private ArrayList<itemSelect> allItems;
        private Context mContext;

        public RecycleCarAdapter(ArrayList<itemSelect> data, Context context){
            mContext = context;
            allItems = data;
            flushFilter();
        }

        @Override
        public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item, viewGroup, false);

            DataViewHolder tvh = new DataViewHolder(itemView,mContext);

            return tvh;
        }

        @Override
        public void onBindViewHolder(DataViewHolder data, int i) {
            itemSelect item = visibleItems.get(i);
            data.bindItem(item);
        }

        @Override
        public int getItemCount() {
            return visibleItems.size();
        }

    public void flushFilter(){
        visibleItems = new ArrayList<>();
        visibleItems.addAll(allItems);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        visibleItems = new ArrayList<>();
        for (itemSelect item: allItems) {
            if (item.getCategory().getName().toLowerCase().contains(queryText))
                visibleItems.add(item);
        }
        notifyDataSetChanged();
    }



    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private itemSelect Category;

        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtDescription;

        private Context mContext;

        public DataViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            imageView = (ImageView)itemView.findViewById(R.id.item_img);
            txtTitle = (TextView)itemView.findViewById(R.id.item_title);
            txtDescription = (TextView)itemView.findViewById(R.id.item_description);
        }

        public void bindItem(itemSelect item) {
            Category = item;
            txtTitle.setText( item.getCategory().getName() );
            txtDescription.setText("Cantidad: "+item.getnCount());
            Picasso.with(mContext).load("http://192.237.180.31/archies/public/" + item.getCategory().getImagePath()).placeholder(R.drawable.bg_load).into(imageView);
        }


    }
}
