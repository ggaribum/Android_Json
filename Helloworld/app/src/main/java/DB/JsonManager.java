package DB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Vo.JsonVo;

/**
 * Created by Administrator on 2017-07-17.
 */

public class JsonManager {

    ArrayList<JsonVo> tempList=new ArrayList<JsonVo>();


    public ArrayList<JsonVo> runJson(String url)
    {
        String httpResult="";
        HttpManager httpManager = new HttpManager(url);
        httpManager.start();
        try{
            httpManager.join();
            httpResult=httpManager.result;
        }catch (Exception e)
        {

        }
        try {
            String temp="";
            JSONObject jsonObject = new JSONObject(httpResult);
            JSONObject json = (JSONObject) jsonObject.get("testCase");
            JSONArray jsonArray = (JSONArray) json.get("testList") ;
            for(int i=0 ; i<jsonArray.length();i++)
            {
                JSONObject entity = (JSONObject)jsonArray.get(i);
                String rank = (String)entity.get("rank");
                String name = (String)entity.get("Nm");
                String imageUrl = (String)entity.get("url");
                tempList.add(new JsonVo(rank,name,imageUrl));
            }
        }
        catch (Exception e)
        {

        }
        return tempList;
    }
}


