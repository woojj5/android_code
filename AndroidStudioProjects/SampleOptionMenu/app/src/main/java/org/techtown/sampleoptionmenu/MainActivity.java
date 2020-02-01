package org.techtown.sampleoptionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch(curId){
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택됨", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색메뉴가 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "세팅이 되었습니다", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
