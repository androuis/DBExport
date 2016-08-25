package com.andreibacalu.android.dbexport;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by abacalu on 5/20/2016.
 */

public class DBExport {

    /**
     * Exports a database from the application to the external storage
     *
     * @param dbName the database to be exported
     * @param exportedDbName the name of the file where the database is exported (can be null)
     * @param context the Context from where the db is exported.
     */
    public static void exportDatabse(String dbName, String exportedDbName, Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();

            if (externalStorageDirectory.canWrite()) {
                File currentDB = context.getDatabasePath(dbName);
                File backupDB = new File(externalStorageDirectory, TextUtils.isEmpty(exportedDbName) ? dbName : exportedDbName);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        
    }

}
