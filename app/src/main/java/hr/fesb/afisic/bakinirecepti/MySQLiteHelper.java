package hr.fesb.afisic.bakinirecepti;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private	static	final	int	DATABASE_VERSION	=	11;


    private	static	final	String	DATABASE_CREATE	=	"create table shoppingList"
            +	"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            +	"amount REAL NOT NULL,"
            +	"unit TEXT NOT NULL,"
            +   "ingredientName TEXT NOT NULL );";

    public	MySQLiteHelper(Context context)	{
        super(context,	"mydatabase.db",	null,	DATABASE_VERSION);
    }

    @Override
    public	void	onCreate(SQLiteDatabase	database)	{
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public	void	onUpgrade(SQLiteDatabase	db,	int	oldVersion,	int	newVersion)	{
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading	database	from	version	"	+	oldVersion	+	"	to	"
                        +	newVersion	+	",	which	will	destroy	all	old	data");
        db.execSQL("DROP	TABLE	IF	EXISTS	ShoppingList");
        onCreate(db);
    }
}