package hr.fesb.afisic.bakinirecepti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingListDataSource {

    private SQLiteDatabase database;
    private	MySQLiteHelper	dbHelper;

    public	ShoppingListDataSource(Context context)	{
        dbHelper	=	new	MySQLiteHelper(context);
    }

    public	void	open()	throws SQLException {
        database	=	dbHelper.getWritableDatabase();
    }

    public	void	close()	{
        database.close();
    }

    public void read(){
        database = dbHelper.getReadableDatabase();
    }

    public void delete(int id){


        String[] selectionArgs = { String.valueOf(id) };
        int deletedRows = database.delete("shoppingList", "_id=?", selectionArgs);
        Log.d("TAG", "delete: ");
    }

    public	void	addShoppingListToDb(Double amount, String unit, String ingredientName)	{
        ContentValues values	=	new	ContentValues();
        values.put("amount",	amount);
        values.put("unit",	unit);
        values.put("ingredientName", ingredientName);
        database.insert("shoppingList",	null,	values);
    }

    public HashMap<Integer, Ingredients> getAllRecipes(){
        HashMap<Integer, Ingredients> recipe = new HashMap<>();
        Ingredients ing;
        Cursor cursor	=	database.rawQuery("SELECT	*	FROM	ShoppingList",	null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            ing = new Ingredients(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("ingredientName")),cursor.getDouble(cursor.getColumnIndex("amount")),cursor.getString(cursor.getColumnIndex("unit")));
            recipe.put(cursor.getPosition(),ing);
            cursor.moveToNext();
        }
        cursor.close();
        return recipe;
    }

}
