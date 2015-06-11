package andres.dev.com.menurestaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Model.Category;
import andres.dev.com.menurestaurant.UI.MainActivity;
import andres.dev.com.menurestaurant.R;
import andres.dev.com.menurestaurant.Utils.JSONkeys;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by INNSO SAS on 10/06/2015.
 */
public class RecycleItemAdapter extends RecyclerView.Adapter<RecycleItemAdapter.DataViewHolder> {


        private ArrayList<Category> visibleItems;
        private ArrayList<Category> allItems;
        private Context mContext;

        public RecycleItemAdapter(ArrayList<Category> data, Context context){
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
            Category item = visibleItems.get(i);
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
        for (Category item: allItems) {
            if (item.getName().toLowerCase().contains(queryText))
                visibleItems.add(item);
        }
        notifyDataSetChanged();
    }



    public static class DataViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        private Category Category;

        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtDescription;

        private Context mContext;

        public DataViewHolder(View itemView, Context context) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = context;
            imageView = (ImageView)itemView.findViewById(R.id.item_img);
            txtTitle = (TextView)itemView.findViewById(R.id.item_title);
            txtDescription = (TextView)itemView.findViewById(R.id.item_description);
        }

        public void bindItem(Category item) {
            Category = item;
            txtTitle.setText( item.getName() );
            txtDescription.setText( item.getDescription() );
            Picasso.with(mContext).load("http://192.237.180.31/archies/public/" + item.getImagePath()).placeholder(R.drawable.bg_load).into(imageView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
