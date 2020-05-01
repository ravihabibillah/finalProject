package com.example.finalproject.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataFavorite.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataFavoriteDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase initDb(Context context){
        if (appDatabase == null) {
            appDatabase = Room
                    .databaseBuilder(context, AppDatabase.class, "dbFavorite")
                    .allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    public static void destroyInstance(){appDatabase = null;}

}
