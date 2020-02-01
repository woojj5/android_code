package org.techtown.sample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String name;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        showtoast("oncdestroy 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showtoast("onstop 호출");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showtoast("oncreate 호출");

        editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        if(button!=null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = editText.getText().toString();
                    showtoast("입력된 값을 name 변수에 저장");

                }
            });
        }
        if(savedInstanceState != null){
            savedInstanceState.getString("name");
            showtoast("값을 복원 "+ name);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name);
    }


    public void showtoast(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
