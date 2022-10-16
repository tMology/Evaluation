package edu.uncc.evaluation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    //#1 Start we will need to program our welcome activity to bring us to the next screen.
    //This would suggest that our program starts at the welcome activity as noted here

    EditText editTextPersonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);// We are starting in welcome activty
        setTitle("Welcome");

        editTextPersonName = findViewById(R.id.editTextPersonName);

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextPersonName.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(WelcomeActivity.this, "Please Enter a name" , Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
}