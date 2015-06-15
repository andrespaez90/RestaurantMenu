package andres.dev.com.menurestaurant.Model;

public class itemSelect {

    private Category Category;
    private int nCount;

    public itemSelect(andres.dev.com.menurestaurant.Model.Category category, int nCount) {
        Category = category;
        this.nCount = nCount;
    }

    public andres.dev.com.menurestaurant.Model.Category getCategory() {
        return Category;
    }

    public void setCategory(andres.dev.com.menurestaurant.Model.Category category) {
        Category = category;
    }

    public int getnCount() {
        return nCount;
    }

    public void setnCount(int nCount) {
        this.nCount = nCount;
    }
}
