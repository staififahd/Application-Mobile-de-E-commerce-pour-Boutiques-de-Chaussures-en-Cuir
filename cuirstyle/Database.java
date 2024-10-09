package mastersidi.fste.umi.ac.ma.cuirstyle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CuirStyle";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "_name";
    private static final String COL_SURNAME = "_surname";
    private static final String COL_USERNAME = "_username";
    private static final String COL_PASSWORD = "_password";

    private static final String TABLE_PRODUCTS = "products";
    private static final String COL_PRODUCT_ID = "_id";
    private static final String COL_PRODUCT_NAME = "_name";
    private static final String COL_PRODUCT_DESCRIPTION = "_description";
    private static final String COL_PRODUCT_PRICE = "_price";
    private static final String COL_PRODUCT_QUANTITY = "_quantity";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String createTableUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_SURNAME + " TEXT, " +
                COL_USERNAME + " TEXT, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createTableUsers);

        // Create products table
        String createTableProducts = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                COL_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_PRODUCT_NAME + " TEXT, " +
                COL_PRODUCT_DESCRIPTION + " TEXT, " +
                COL_PRODUCT_PRICE + " TEXT, " +
                COL_PRODUCT_QUANTITY + " INTEGER)";
        db.execSQL(createTableProducts);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long addUser(String name, String surname, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_SURNAME, surname);
        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }
    public long addProduct(String name, String description, String price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PRODUCT_NAME, name);
        values.put(COL_PRODUCT_DESCRIPTION, description);
        values.put(COL_PRODUCT_PRICE, price);
        values.put(COL_PRODUCT_QUANTITY, quantity);
        long result = db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        return result;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID};
        String selection = COL_USERNAME + "=? AND " + COL_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, null, null);
        db.close();
    }

    public List<Product> getCartProducts() {
        List<Product> cartProducts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_PRODUCT_NAME, COL_PRODUCT_DESCRIPTION, COL_PRODUCT_PRICE, COL_PRODUCT_QUANTITY};
        Cursor cursor = db.query(TABLE_PRODUCTS, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                int productNameIndex = cursor.getColumnIndex(COL_PRODUCT_NAME);
                int descriptionIndex = cursor.getColumnIndex(COL_PRODUCT_DESCRIPTION);
                int priceIndex = cursor.getColumnIndex(COL_PRODUCT_PRICE);
                int quantityIndex = cursor.getColumnIndex(COL_PRODUCT_QUANTITY);

                String productName = (productNameIndex != -1) ? cursor.getString(productNameIndex) : "";
                String productDescription = (descriptionIndex != -1) ? cursor.getString(descriptionIndex) : "";
                String productPrice = (priceIndex != -1) ? cursor.getString(priceIndex) : "";
                int quantity = (quantityIndex != -1) ? cursor.getInt(quantityIndex) : 0;

                Product product = new Product(0, productName, productPrice, productDescription);
                product.setQuantity(quantity);
                cartProducts.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cartProducts;
    }
}
