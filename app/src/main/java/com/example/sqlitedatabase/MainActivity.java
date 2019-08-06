package com.example.sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText Name, Pass, Confirm_Pass, Age, ID;
    Button Save, View, Updating, Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        Name = findViewById(R.id.edit_name);
        Pass = findViewById(R.id.edit_pass);
        Confirm_Pass = findViewById(R.id.edit_Cpsw);
        Age = findViewById(R.id.edit_age);
        Save = findViewById(R.id.btn_save);
        View = findViewById(R.id.btn_view);
        Updating = findViewById(R.id.btn_update);
        ID = findViewById(R.id.edit_id);
        Delete = findViewById(R.id.btn_del);
        AddData();
        viewAll();
        Updatedata();
        DeleteData();


    }

    public void DeleteData() {
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Integer deletedRows = myDB.DeleteData(ID.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(MainActivity.this, "Data is Deleted Sucessfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data  Error", Toast.LENGTH_LONG).show();

                }
            }
        });

    }


    public void Updatedata() {
        Updating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                boolean isUpdate = myDB.Updatedata(ID.getText().toString(), Name.getText().toString(), Pass.getText().toString(), Confirm_Pass.getText().toString(), Age.getText().toString());
                if (isUpdate == true) {
                    Toast.makeText(MainActivity.this, "Data is Updated Sucessfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data  not Deleted", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void AddData() {
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(Name.getText().toString(), Pass.getText().toString(), Confirm_Pass.getText().toString(), Age.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted Sucessfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data  Error", Toast.LENGTH_LONG).show();


                }
            }
        });
    }

    public void viewAll() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Cursor res = myDB.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing Found");

                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("id :" + res.getString(0) + "\n");
                    buffer.append("username :" + res.getString(1) + "\n");
                    buffer.append("password :" + res.getString(2) + "\n");
                    buffer.append("Conf_pass :" + res.getString(3) + "\n");
                    buffer.append("age :" + res.getString(4) + "\n \n");

                }
                showMessage("Data", buffer.toString());

            }
        });
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {

        }
        return true;
    }
}
