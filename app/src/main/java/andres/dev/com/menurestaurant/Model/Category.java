package andres.dev.com.menurestaurant.Model;

import java.util.ArrayList;

public class Category {

    private int id;
    private String Name;
    private String imagePath;
    private String Description;

    private Category Parent;
    private ArrayList<Category> subCategory;

    public Category() {
        subCategory = new ArrayList<Category>();
    }

    public Category ( int id, String name, String imagePath, String descripption){
        this.id = id;
        this.Name = name;
        this.imagePath = imagePath;
        this.Description = descripption;

        subCategory = new ArrayList<Category>();
    }

    public Category(int id, String name, Category parent) {
        this.id = id;
        this.Name = name;
        this.Parent = parent;
        subCategory = new ArrayList<Category>();
    }

    public void addCategory(Category subcategory){
        subCategory.add(subcategory);
    }

    public ArrayList<Category> getSubCategory() {
        return subCategory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return Description;
    }

    public Category getParent() {
        return Parent;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setSubCategory(ArrayList<Category> subCategory) {
        this.subCategory = subCategory;
    }

    public void setParent(Category parent) {
        Parent = parent;
    }
}
