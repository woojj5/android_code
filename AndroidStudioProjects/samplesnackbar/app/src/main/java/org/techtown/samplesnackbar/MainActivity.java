package org.techtown.samplesnackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar2);
        Button button =  findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showsnackbar(v);
            }
        });

        Button button2 =  findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
        Button button3 =  findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showprogress();
            }
        });
    }
    public void showsnackbar(View v){
        Snackbar.make(v, "스낵바 입니다", Snackbar.LENGTH_LONG).show();
    }
    public void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage( "종료하시겠?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "예 버튼이 눌림", Toast.LENGTH_LONG);
            }
        });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "아니오 버튼이 눌림", Toast.LENGTH_LONG);
                    }
                });
             AlertDialog dialog = builder.create();
             dialog.show();
    }
    public void showprogress(){
        progressBar.setProgress(70);
    }
}
