package andres.dev.com.menurestaurant.Model;

import java.util.ArrayList;

/**
 * Created by INNSO SAS on 10/06/2015.
 */
public class ModelFacade {

    public static ArrayList<Category> Categoties =  new ArrayList<Category>();
    public static ArrayList<itemSelect> ItemsCar = new ArrayList<itemSelect>();


    public static ArrayList<Category> findItem(int idparent, int idCategory) {
        for(Category parent : Categoties){
            if(parent.getId() == idparent){
                for(Category subcategory : parent.getSubCategory()){
                    if(subcategory.getId() == idCategory){
                        return subcategory.getSubCategory();
                    }
                }
            }
        }
        return null;
    }

    public static void addItemCar(Category c){
        for( itemSelect item : ItemsCar){
            if(item.getCategory().getId() == c.getId() &&
                    item.getCategory().getParent().getId() == c.getParent().getId() ){
                item.setnCount(item.getnCount()+1);
                return;
            }
        }
        ItemsCar.add(new itemSelect( c , 1));
    }
}
