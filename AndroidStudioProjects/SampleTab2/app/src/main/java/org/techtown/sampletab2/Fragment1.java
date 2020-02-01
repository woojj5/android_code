package org.techtown.sampletab2;
import android.app.Activity;
import android.content.Context;
import android.content.UriMatcher;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Fragment1 extends Fragment {

    EditText edit;
    EditText edit2;
    TextView text;
    Button button;

    String end1 = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey="; // 정류장 이름을 파라미터로 받는다
    String end2 = "http://ws.bus.go.kr/api/rest/stationinfo/getLowStationByUid?serviceKey="; // end1에서 얻은 stid값을 받아서 그 값을 파라미터로 사용
    String key = "iORkywxuz9aVxEKEHQqne9q5GfXw0EifQXDgFLtDFd%2BCyqWe%2BlMFrJJyDXd%2BVTctia9ifhr6gXsczAbHhgpVvA%3D%3D";
    String data;
    String armsg1;
    String armsg2;
    String adir;
    String stNm;
    String rtNm;
    String stId;
    String str;

    private Context mcontext;
    ArrayList<item1> arrayList = null;
    XmlPullParserFactory pullParserFactory;
    RecyclerView myRecyclerView;
    public Myadapter myadapter;
    item1 bus = null;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        edit = (EditText) view.findViewById(R.id.edit);
        edit2 = (EditText) view.findViewById(R.id.edit2);

        myRecyclerView = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setHasFixedSize(true);;
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        str = edit.getText().toString();
                                        MyAsyncTask myAsyncTask = new MyAsyncTask();
                                        myAsyncTask.execute(str);
                                        Toast.makeText(getActivity().getApplicationContext(), "클릭 인식됨", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).start();
                        break;
                }
            }
        });
        //dataAdapter.notifyDataSetChanged();
        return view;//StringBuffer 문자열 객체
    }
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if(context instanceof Activity) activity = (Activity)context;
//        else mcontext = context;
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        DataAdapter adapter = new DataAdapter(getActivity().getApplicationContext(), arrayList);
//        myRecyclerView.setAdapter(adapter);
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... args) {
                String loc = URLEncoder.encode(args[0]);
                String requestUrl = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey=" + key + "&stSrch=" + loc;
                try {
                    boolean b_locationNo1 = false;
                    boolean b_plateNo1 = false;
                    boolean b_routeId = false;
                    boolean b_stationId = false;

                    URL url = new URL(requestUrl);
                    InputStream is = url.openStream();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    parser.setInput(new InputStreamReader(is, "UTF-8"));

                    String tag;
                    int eventType = parser.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                arrayList = new ArrayList<item1>();
                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;
                            case XmlPullParser.END_TAG:
                                if (parser.getName().equals("itemList") && bus != null) {
                                    arrayList.add(bus);
                                }
                                break;
                            case XmlPullParser.START_TAG:
                                if (parser.getName().equals("itemList")) {
                                    bus = new item1();
                                }
                                if (parser.getName().equals("arsId")) b_locationNo1 = true;
                                if (parser.getName().equals("stNm")) b_plateNo1 = true;
                                if (parser.getName().equals("stId")) b_routeId = true;
                                if (parser.getName().equals("tmX")) b_stationId = true;
                                break;
                            case XmlPullParser.TEXT:
                                if (b_locationNo1) {
                                    bus.setLocationNo1(parser.getText());
                                    b_locationNo1 = false;
                                } else if (b_plateNo1) {
                                    bus.setPlateNo1(parser.getText());
                                    b_plateNo1 = false;
                                } else if (b_routeId) {
                                    bus.setRouteId(parser.getText());
                                    //doInBackground2(parser.getText(), args[1]);
                                    b_routeId = false;
                                } else if (b_stationId) {
                                    bus.setStationId(parser.getText());
                                    b_stationId = false;
                                }
                                break;
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Myadapter adapter = new Myadapter(getActivity().getApplicationContext(), arrayList);
            myRecyclerView.setAdapter(adapter);
        }
    }

//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}
//    void  getextradata(String dat) {
//        StringBuffer buffer = new StringBuffer();
//        String str = edit2.getText().toString();//EditText에 작성된 Text얻어오기
//        String queryUrl = end2 + key + "&arsId=" + dat;
//        String tag;
//        try {
//            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
//            InputStream is = url.openStream(); //url위치로 입력스트림 연결
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기
//            xpp.next();
//            int eventType = xpp.getEventType();
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                switch (eventType) {
//                    case XmlPullParser.START_DOCUMENT:
//                        buffer.append("파싱 시작...\n\n");
//                        break;
//                    case XmlPullParser.START_TAG:
//                        tag = xpp.getName();//태그 이름 얻어오기
//                        if (tag.equals("itemList")) ;// 첫번째 검색결과
//                        else if(tag.equals("adirection")){
//                            xpp.next();
//                            adir = xpp.getText();
//                            xpp.getAttributeValue(2);
//                        }
//                        else if (tag.equals("arrmsg1")) {
//                            xpp.next();
//                            armsg1 = xpp.getText();
//                            xpp.getAttributeValue(3);
//                        } else if (tag.equals("arrmsg2")) {
//                            xpp.next();
//                            armsg2 = xpp.getText();
//                            xpp.getAttributeValue(4);
//                        } else if (tag.equals("rtNm")) {
//                            xpp.next();
//                            if(str.equals(xpp.getText())) {
//                                rtNm = xpp.getText();
//                                xpp.getAttributeValue(5);
//                                item1 it = new item1();
//                                it.setStId(xpp.getAttributeValue(2));
//                                it.setStNm(xpp.getAttributeValue(3));
//                                it.setStId(xpp.getAttributeValue(4));
//                                it.setStNm(xpp.getAttributeValue(5));
//
//                            }
//                            else if(str.equals("")){
//                                rtNm = xpp.getText();
//                                xpp.getAttributeValue(5);
//                                item1 it = new item1();
//                                it.setStId(xpp.getAttributeValue(2));
//                                it.setStNm(xpp.getAttributeValue(3));
//                                it.setStId(xpp.getAttributeValue(4));
//                                it.setStNm(xpp.getAttributeValue(5));
//                            }
//                        }
//                        break;
//                    case XmlPullParser.TEXT: break;
//                    case XmlPullParser.END_TAG:
//                        tag = xpp.getName(); //태그 이름 얻어오기
//                        if (tag.equals("itemList")) break;
//                }
//                eventType = xpp.next();
//            }
//        } catch (Exception e) {}
//    }