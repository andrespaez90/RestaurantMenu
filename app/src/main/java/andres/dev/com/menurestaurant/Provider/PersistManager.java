package andres.dev.com.menurestaurant.Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import andres.dev.com.menurestaurant.Model.Category;

public class PersistManager extends SQLiteOpenHelper {

    public PersistManager(Context context){
        super(context,"MenuDataBase",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Category (id INTEGER, Name TEXT, Description TEXT, filePath TEXT); ");
        db.execSQL("CREATE TABLE subCategory (id INTEGER, Name TEXT, idParent INTEGER); ");
        db.execSQL("CREATE TABLE itemCategory (id INTEGER, Name TEXT, Description TEXT, filePath TEXT, idParent INTEGER); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveCategory ( Category c ){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Category VALUES ('"+c.getId()+"', '"+c.getName()+"' '"+c.getDescription()+"' '"+c.getImagePath() +"')");
    }

    public void saveSubCategory( Category c ){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO subCategory VALUES ('"+c.getId()+"', '"+c.getName()+"', '"+c.getParent().getId()+"' )");
    }

    public void saveItemCategory( Category c ){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO itemCategory VALUES ('"+c.getId()+"', '"+c.getName()+"', '"+c.getDescription()+"', '"+c.getImagePath()+"','"+c.getParent().getId()+"' )");
    }


    public ArrayList< Category > getCategories(){
        ArrayList< Category > result = new ArrayList<Category>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursosr = db.rawQuery("SELECT * FROM Category", null);
        Category data;
        while(cursosr.moveToNext()){
            data = new Category(cursosr.getInt(0),cursosr.getString(1),cursosr.getString(2),cursosr.getString(3));
            result.add(data);
        }
        cursosr.close();
        return result;
    }

    public ArrayList< Category > getSubCategory(Category categoryParent){
        ArrayList< Category > result = new ArrayList<Category>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursosr = db.rawQuery("SELECT * FROM subCategory WHERE idParent = '"+categoryParent.getId()+"'", null);
        Category data;
        while(cursosr.moveToNext()){
            data = new Category(cursosr.getInt(0),cursosr.getString(1), categoryParent);
            result.add(data);
        }
        cursosr.close();
        return result;
    }

}
