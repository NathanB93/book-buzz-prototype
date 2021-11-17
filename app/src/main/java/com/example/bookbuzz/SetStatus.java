package com.example.bookbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetStatus extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String isbn = getIntent().getStringExtra("isbn");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_status);

        radioGroup = findViewById(R.id.status_radio_group);
        status = findViewById(R.id.status_selection_txt);


        Button setStatus = findViewById(R.id.setStatus);
        setStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSet = "example";
                String title = getIntent().getStringExtra("title");
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                if(radioButton == findViewById(R.id.radio_read)){
                    toSet = "Read";
                } else if(radioButton == findViewById(R.id.radio_reading)){
                    toSet = "Reading";
                } else if(radioButton == findViewById(R.id.radio_want)){
                    toSet  = "Want to read";
                }

                DataUtility.setStatus(isbn, toSet);

                viewLibrary();


            }
        });

    }


    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        status.setText("Status: " + radioButton.getText());
    }
    public void viewLibrary() {
        Intent intent = new Intent(this, ViewLibrary.class);
        startActivity(intent);
    }

}
