package com.andreibacalu.android.dbexport;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by abacalu on 5/20/2016.
 */

public class DBExport {

    public static void exportDatabse(String dbName, String exportedDbName, Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();

            if (externalStorageDirectory.canWrite()) {
                File currentDB = context.getDatabasePath(dbName);
                File backupDB = new File(externalStorageDirectory, exportedDbName);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
