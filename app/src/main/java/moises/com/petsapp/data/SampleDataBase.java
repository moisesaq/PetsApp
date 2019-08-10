package moises.com.petsapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

@Database(entities = {User.class}, version = 1)
public abstract class SampleDataBase extends RoomDatabase{

    public abstract UserDao users();

    private static SampleDataBase sampleDataBase;

    public static synchronized SampleDataBase getInstance(Context context){
        if(sampleDataBase == null){
            sampleDataBase = Room
                    .databaseBuilder(context.getApplicationContext(), SampleDataBase.class, "ex")
                    .build();
            sampleDataBase.setUpInitialData();
        }
        return sampleDataBase;
    }

    @VisibleForTesting
    public static void switchToInMemory(Context context){
        sampleDataBase = Room
                .inMemoryDatabaseBuilder(context.getApplicationContext(), SampleDataBase.class)
                .build();
    }

    private void setUpInitialData(){
        if(users().count() == 0){
            User user = new User();
            beginTransaction();
            try{
                for (int i = 0; i < User.USERS.length; i++){
                    user.name = User.USERS[i];
                    users().insert(user);
                }
                setTransactionSuccessful();
            }finally {
                endTransaction();
            }
        }
    }
}
