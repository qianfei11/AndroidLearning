package zjgsu.is.qff.homework05;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "starbuzz.db";
    private static final int DB_VER = 3;

    public StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "DESCRIPTION TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER);");
        insertDrink(db, "Latte", "sepresso with steamed milk", R.drawable.latte);
        insertDrink(db, "Coppuccino",
                "Espresso, hot milk, steamed milk foam", R.drawable.cappuccino);
        insertDrink(db, "Filter", "beans & brewed fresh", R.drawable.filter);
    }

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        long result = db.insert("DRINK", null, drinkValues);
        Log.d("SQLite", "Insert" + name + "_id" + result);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 1) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
        if (oldVersion <= 2) {
            ContentValues latteDesc = new ContentValues();
            latteDesc.put("DESCRIPTION", "Tasty");
            db.update("DRINK", latteDesc, "NAME=?", new String[]{"Latte"});
        }
    }
}
