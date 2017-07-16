package UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onetwopunch.helloworld.R;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2017-07-17.
 */

public class JsonView extends LinearLayout {

    TextView tv_JsonRank;
    TextView tv_JsonName;
    public ImageView iv_Json;

    public JsonView(Context context) {
        super(context);
        init(context);
    }

    public JsonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_json,this,true);
        //새로 생성한 xml을 여기다가 갖다붙이는 개념인가..?

        tv_JsonRank =(TextView) findViewById(R.id.tv_JsonRank);
        tv_JsonName=(TextView) findViewById(R.id.tv_JsonName);
        iv_Json =(ImageView) findViewById(R.id.iv_Json);

    }

    public void setTv_JsonRank(String rank) {
        tv_JsonRank.setText(rank);
    }

    public void setTv_JsonName(String name) {
        tv_JsonName.setText(name);
    }

    public void setIv_Json(String imageURL) {

    new DownloadImageTask(iv_Json).execute(imageURL);
       // iv_Json.setImageBitmap();
    }

    //////////imageURL -> bitmap으로 파싱하는 클래스 ////////////////
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (java.lang.Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

