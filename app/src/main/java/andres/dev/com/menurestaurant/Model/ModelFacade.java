package andres.dev.com.menurestaurant.Model;

import java.util.ArrayList;


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

    public static void addItemCar(itemSelect c){
        for( itemSelect item : ItemsCar){
            if(item.getCategory().getId() == c.getCategory().getId() &&
                    item.getCategory().getParent().getId() == c.getCategory().getParent().getId() ){
                item.setnCount(item.getnCount()+c.getnCount());
                return;
            }
        }
        ItemsCar.add(c);
    }
}
