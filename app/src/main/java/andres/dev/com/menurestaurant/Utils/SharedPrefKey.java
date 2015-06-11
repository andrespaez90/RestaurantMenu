package andres.dev.com.menurestaurant.Utils;

/**
 * Created by INNSO SAS on 10/06/2015.
 */
public enum SharedPrefKey {

    APP_DATA_FILE("APP_DATA_FILE"),

    APP_TIMESTAMP ("APP_TIMESTAMP"),
    CATEGORY_TIMESTAMP ("CATAGORY_TIMESTAMP"),
    DESCRIPTION_TIMESTAMP("DESCRIPTION_TIMESTAMP");

    private String text;

    private SharedPrefKey(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
