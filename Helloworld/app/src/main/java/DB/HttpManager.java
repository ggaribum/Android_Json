package DB;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017-07-16.
 */

public class HttpManager extends Thread
{
    public String result;
    //String inputURL = "http://ec2-52-26-144-160.us-west-2.compute.amazonaws.com:3000/jiminzzang";
    String inputURL;
    public HttpManager(String url)
    {
           inputURL=url;
    }
    @Override
    public void run() {
    //run()메서드는 return 타입이 무조건 void 이다.
        try {


            URL url = new URL(inputURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            InputStream in = null;
            ByteArrayOutputStream out = null;

            try{
                in = connection.getInputStream();
                out = new ByteArrayOutputStream();

                byte[] buf = new byte[1024 * 8];
                int length = 0;
                while((length = in.read(buf)) != -1){
                    out.write(buf, 0, length);
                }

                result = new String(out.toByteArray(), "UTF-8");
                setResult(result);
                //Log.v("\n결과값: ", result);
                //printrln(result);

                //이게 될진 모르겠는데.. 접근하기 위해 사용

            }finally {
                if(in != null) in.close();
                if(out != null) out.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //실험용 메서드
    public void setResult(String str)
    {
        this.result=str;
    }
    public String returnResult()
    {
        return result;
    }
}
