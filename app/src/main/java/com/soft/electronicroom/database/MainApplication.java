package com.soft.electronicroom.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class MainApplication extends Application {

    private static CreateDatabase createDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static CreateDatabase getCreateDatabase(final Context mContext) {

        if (createDatabase == null) {
            createDatabase = Room.databaseBuilder(mContext, CreateDatabase.class, "electronic")
                    .build();
        }

        return createDatabase;
    }

    public static void setCreateDatabase(CreateDatabase createDatabase) {
        MainApplication.createDatabase = createDatabase;
    }
}
