package com.onetwopunch.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import DB.JsonManager;
import UI.JsonView;
import Vo.JsonVo;

public class FunctionActivity extends AppCompatActivity {
    EditText editText;
    String urlStr;
    ListView listView;

    //Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuction);

        editText = (EditText) findViewById(R.id.et_URL);
        listView = (ListView) findViewById(R.id.listView);
        Button button =(Button) findViewById(R.id.button2);



        /*adapter.addItem(new JsonVo("소녀시대","010-1000-1000"));
        adapter.addItem(new JsonVo("걸스데이","010-2000-2000"));
        adapter.addItem(new JsonVo("여자친구","010-3000-3000"));
        adapter.addItem(new JsonVo("애프터스쿨","010-4000-4000"));*/



        button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                JsonAdapter adapter = new JsonAdapter();

                urlStr = editText.getText().toString();
                //jsonManger 클래스를 통해 JsonParsing된 ArrayList를 리턴받음.
                JsonManager jsonManager = new JsonManager();
                ArrayList<JsonVo> objList=jsonManager.runJson(urlStr);

                for(int i=0;i<objList.size();i++)
                {
                    adapter.addItem(objList.get(i));
                }
                listView.setAdapter(adapter);
            }
        });
    }

    //////////////ListView를 위해 Adapter 클래스 /////////////////

    class JsonAdapter extends BaseAdapter{
        //이번 과제에서는 getView 메서드 말고는 필요 없기 때문에 따로 오버라이드 하지 않겠음.
        ArrayList<JsonVo> objects = new ArrayList<JsonVo>();

        //JsonVo 타입 리스트 생성

        public  void addItem(JsonVo object)
        {
            objects.add(object);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Object getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View ConverView, ViewGroup parent) {
            JsonView view = new JsonView(getApplicationContext());
            //여기서 뷰를 생성함으로써
            // 이 엑티비티에 JsonView에서 만든 (activity_json.xml)뷰를 갖다 쓰겠다.
            JsonVo object = objects.get(position);
            view.setTv_JsonRank(object.getRank());
            view.setTv_JsonName(object.getNm());
            view.setIv_Json(object.getImageURL());

            return view;
        }
    }


}


