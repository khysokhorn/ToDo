package com.example.todo;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.database.DBOpenHelper;

import java.util.Calendar;

public class AddNewTodo extends AppCompatActivity {
    EditText etName,etDescription, etCategory,etDateTime;

    Button btnAdd;
    private static final String TAG = "AddNewTodo";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);

        final DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

        etName = findViewById(R.id.etName_aTodo);
        etCategory = findViewById(R.id.etCategory_atodo);
        etDescription = findViewById(R.id.etDescription_atodo);
        btnAdd = findViewById(R.id.btnAdd_atodo); // button

        etDateTime = findViewById(R.id.etDateTime_atodo);
        etDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddNewTodo.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(AddNewTodo.this,hourOfDay + " : " + minute, Toast.LENGTH_SHORT).show();
                        etDateTime.setText( hourOfDay + " : " + minute); // set text to our etDateTime
                    }
                }, hour,minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etName.getText().toString();
                String category = etCategory.getText().toString();
                String time = etDateTime.getText().toString();
                String description = etDescription.getText().toString();

                //// insert our data into our database
                dbOpenHelper.insert(title, description, category, time);

                Toast.makeText(AddNewTodo.this, "Data Inserted ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
