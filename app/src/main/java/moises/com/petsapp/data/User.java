package moises.com.petsapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.provider.BaseColumns;

import static moises.com.petsapp.data.User.COLUMN_ID;
import static moises.com.petsapp.data.User.COLUMN_NAME;

@Entity(tableName = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_NAME)
    public String name;

    public static User fromContentValues(ContentValues values){
        final User user = new User();
        if(values.containsKey(COLUMN_ID)){
            user.id = values.getAsLong(COLUMN_ID);
        }

        if(values.containsKey(COLUMN_NAME)){
            user.name = values.getAsString(COLUMN_NAME);
        }
        return user;
    }

    public static final String[] USERS = {"Moises", "Gabriel", "Abrahan", "SEGU", "EPICO"};
}
