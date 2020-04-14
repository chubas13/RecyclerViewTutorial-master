package com.androidfizz.recyclerviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class HandlerBD extends SQLiteOpenHelper {

    /** Tabla del carrito de compras */
    String  DATABASE_TABLE1 = "shoppingcart";
    String KEY_ID = "_id"; //campo 0
    String KEY_idUser = "UserID";
    String KEY_idProduct = "ProductID";
    String KEY_nameProduct = "ProductName";
    String KEY_priceProduct = "ProductPrice";
    String KEY_typeProduct = "ProductType";//campo 5 contando desde 0

    String sqlCreateTableShop= "CREATE TABLE " + DATABASE_TABLE1 + "(" + KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_idUser + " VARCHAR(255), " + KEY_idProduct + " VARCHAR(255), " + KEY_nameProduct + " VARCHAR(255), "
            + KEY_priceProduct + " VARCHAR(255), " + KEY_typeProduct + " VARCHAR(255));";

    public HandlerBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreateTableShop);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
