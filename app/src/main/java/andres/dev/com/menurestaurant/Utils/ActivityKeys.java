package andres.dev.com.menurestaurant.Utils;

/**
 * Created by INNSO SAS on 10/06/2015.
 */
public enum ActivityKeys {

    SPLASH_ACTIVITY ("SPLAH_ACTIVITY"),
    INTENT_IDPARENT ("ID_PARENT"),
    INTENT_IDSUBCATEGORY ("ID_SUBCATEGORY");

    private String text;

    private ActivityKeys(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
