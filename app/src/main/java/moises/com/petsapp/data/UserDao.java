package moises.com.petsapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

@Dao
public interface UserDao {

    @Query("SELECT COUNT(*) FROM " + User.TABLE_NAME)
    int count();

    @Insert
    long insert(User user);

    @Insert
    long[] insertAll(User[] users);

    @Query("SELECT * FROM " + User.TABLE_NAME)
    Cursor selectAll();

    @Query("SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + " = :id")
    Cursor selectById();

    @Query("DELETE FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + " =:id")
    int deleteById(long id);

    @Update
    int update(User user);
}
