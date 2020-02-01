package org.techtown.samplesocket;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    TextView textView;
    TextView textView1;

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data= editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });
        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }
    public void printClientLog(final String data){
        Log.d("MainActivity", data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data+ "\n");
            }
        });
    }
    public void printServerLog(final String data){
        Log.d("MainActivity", data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView1.append(data+ "\n");
            }
        });
    }
    public void send(String data){
        try{
            int port = 5001;
            Socket socket = new Socket("localhost", port);
            printClientLog("소켓 연결");
            ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
            outstream.writeObject(data);
            outstream.flush();
            printClientLog("데이터 전송");

            ObjectInputStream instream  = new ObjectInputStream(socket.getInputStream());
            printClientLog("데이터 받음" + instream.readObject());

            socket.close();
        }
        catch (Exception e) {e.printStackTrace();}
    }
    public void startServer(){
        try{
            int port = 5001;
            ServerSocket server = new ServerSocket(port);
            printServerLog("서버 시작함" + port);
            while(true){
                Socket sock = server.accept();
                InetAddress clienHost = sock.getLocalAddress();
                int clientPort = sock.getPort();
                printServerLog("클라이언트 연결됨" + clienHost + " " + clientPort);

                ObjectInputStream instream  = new ObjectInputStream(sock.getInputStream());
                Object obj = instream.readObject();
                printServerLog("데이터 받음" + obj);

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(obj + "from server");
                outstream.flush();
                printServerLog("데이터 보냄");

                sock.close();
            }
        }catch(Exception e) {e.printStackTrace();}
    }
}
