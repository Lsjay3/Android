package com.example.ienglish.ChangeMesg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ienglish.R;
import com.example.ienglish.UserDB.UserDb;
import com.example.ienglish.ieng_main_activity;

public class ChangeNick extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText newname;
    private String user_name;
    private UserDb userDb;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_nick);
        setAndroidNativeLightStatusBar(this,true);
        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        InitView();
    }

    private void InitView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        newname = findViewById(R.id.et_new_nickname);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {   //更改状态栏字体
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public void Change(View view) {
        String nickname = newname.getText().toString().trim();

        userDb = new UserDb(ChangeNick.this,"user_detail_mesg",null,1);
        db = userDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nick_name",nickname);
        String update = "update user_detail_mesg set user_nickname = '" + nickname + "' where user_name = '"+ user_name + "'";
        db.execSQL(update);
        Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeNick.this, ieng_main_activity.class);
        intent.putExtra("user_name",user_name);
        startActivity(intent);
    }
}
