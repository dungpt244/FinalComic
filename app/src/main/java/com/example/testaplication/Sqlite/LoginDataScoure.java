package com.example.testaplication.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.testaplication.Account.Account;

import java.util.ArrayList;
import java.util.List;

public class LoginDataScoure {
    // Các trường database.
    private SQLiteDatabase database;
    private LoginSQLiteHelper dbHelper;
    private String[] allColumns = {
            LoginSQLiteHelper.COLUMN_ID,
            LoginSQLiteHelper.COLUMN_USERNAME,
            LoginSQLiteHelper.COLUMN_PASSWORD
    };

    public LoginDataScoure(Context context) {
        dbHelper = new LoginSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean createAccount(Account account) {
        if(existAccount(account))
            return false;

        String userName = account.getUsername().trim();
        String password = account.getPassword().trim();

        ContentValues values = new ContentValues();
        values.put(LoginSQLiteHelper.COLUMN_USERNAME, userName);
        values.put(LoginSQLiteHelper.COLUMN_PASSWORD, password);
        long insertId = database.insert(LoginSQLiteHelper.TABLE_ACCOUNT, null, values);

//        Cursor cursor = database.query(LoginSQLiteHelper.TABLE_ACCOUNT,
//                allColumns, LoginSQLiteHelper.COLUMN_ID + " = " + insertId, null,
//                null, null, null);
//        cursor.moveToFirst();
//
//        Account newAccount = cursorToAccount(cursor);
//        cursor.close();
        return insertId != -1;
    }

    public boolean existAccount(Account account) {
        String userName = account.getUsername().trim();
        String password = account.getPassword().trim();

        String selection = LoginSQLiteHelper.COLUMN_USERNAME + " = ?" + " or " + LoginSQLiteHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { userName, password };

        Cursor cursor = database.query(LoginSQLiteHelper.TABLE_ACCOUNT,
                allColumns, selection, selectionArgs, null, null, null);

        boolean isExist = cursor.getCount() > 0;
        cursor.close();
        return  isExist;
    }

    public void deleteAccount(Account account) {
        long id = account.getId();
        Log.e("SQLite", "Account entry deleted with id: " + id);
        database.delete(LoginSQLiteHelper.TABLE_ACCOUNT, LoginSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Account> getAllAccount() {
        List<Account> accounts = new ArrayList<Account>();

        Cursor cursor = database.query(LoginSQLiteHelper.TABLE_ACCOUNT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Account account = cursorToAccount(cursor);
            accounts.add(account);
            cursor.moveToNext();
        }
        // Nhớ đóng con trỏ lại nhé.
        cursor.close();
        return accounts;
    }

    public boolean changePassword(Account account, String newPassword) {
        Cursor cursor = database.query(LoginSQLiteHelper.TABLE_ACCOUNT,
                allColumns, LoginSQLiteHelper.COLUMN_PASSWORD + " = ?", new String[]{newPassword}, null, null, null);

        if(cursor.getCount() > 0) {
            cursor.close();
            return false;
        }

        String userName = account.getUsername().trim();
        String password = account.getPassword().trim();
        ContentValues values = new ContentValues();
        values.put(LoginSQLiteHelper.COLUMN_PASSWORD, newPassword);

        // Which row to update, based on the title
        String selection = LoginSQLiteHelper.COLUMN_USERNAME + " = ?" + " and " + LoginSQLiteHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {userName, password};

        int count = database.update(
                LoginSQLiteHelper.TABLE_ACCOUNT,
                values,
                selection,
                selectionArgs);

        return count > 0;
    }

    private Account cursorToAccount(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getInt(0));
        account.setUsername(cursor.getString(1));
        account.setPassword(cursor.getString(2));
        return account;
    }
}