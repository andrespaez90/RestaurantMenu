package andres.dev.com.menurestaurant.Utils;

/**
 * Created by INNSO SAS on 10/06/2015.
 */
public enum JSONkeys {

    JSON_ID("id"),
    JSON_TIMESTAMP("timestamp"),
    JSON_NAME("name"),
    JSON_URL("url"),
    JSON_SUBCATEGORY("subcategory"),
    JSON_ITEMS("items"),
    JSON_DESCRIPTION("description"),
    JSON_IMAGE("img_path");

    private String Keys;

    private JSONkeys (String keys){
        Keys = keys;
    }

    @Override
    public String toString(){
        return Keys;
    }
}
