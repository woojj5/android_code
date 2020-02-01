package org.techtown.sampleintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, 101);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101) {
            Toast.makeText(this, "onactivityresult 호출됨"+ requestCode, Toast.LENGTH_LONG).show();;

            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                Toast.makeText(this, "응답으로 받은  name" +name, Toast.LENGTH_LONG).show();
            }
        }
    }
}
