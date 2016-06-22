package com.andreibacalu.android.dbexport;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExportUnitTest extends InstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testExportDB() {
        String dbName = "db1";
        createDBs(dbName);
        DBExport.exportDatabse(dbName, dbName, getInstrumentation().getContext());
        checkDBsLocally(dbName);
    }

    private void createDBs(String...dbNames) {
        if (dbNames != null && dbNames.length > 0) {
            for (String dbName: dbNames) {
                SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper(getInstrumentation().getContext(), dbName, null, 1) {
                    @Override
                    public void onCreate(SQLiteDatabase db) {
                    }

                    @Override
                    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    }
                };
                sqLiteOpenHelper.getReadableDatabase();
            }
        }
    }

    private void checkDBsLocally(String...dbNames) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory.canRead()) {
            for (String dbName: dbNames) {
                if (!new File(dbName).exists()) {
                    Assert.fail("Database <<" + dbName + ">> was not exported!");
                }
            }
        }
    }
}