package com.soft.electronicroom.database;

import android.content.Context;

import androidx.room.Room;

public class MainApplication {

    private Context mContext;

    private CreateDatabase createDatabase;

    private static MainApplication mInstance;

    public MainApplication(Context mContext) {
        this.mContext = mContext;
        this.createDatabase = Room.databaseBuilder(mContext, CreateDatabase.class, "electronic_room").build();
    }

    public static synchronized MainApplication getInstance(Context mContext) {

        if (mInstance == null) {
            mInstance = new MainApplication(mContext);
        }

        return mInstance;
    }

    public CreateDatabase getCreateDatabase() {
        return createDatabase;
    }
}
