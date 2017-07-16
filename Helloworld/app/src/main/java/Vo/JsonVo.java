package Vo;

import static android.R.attr.bitmap;

/**
 * Created by Administrator on 2017-07-16.
 */

public class JsonVo {
    String rank;
    String Nm;
    String imageURL;

    public JsonVo(String rank, String nm, String imageURL) {
        this.rank = rank;
        Nm = nm;
        this.imageURL= imageURL;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNm() {
        return Nm;
    }

    public void setNm(String nm) {
        Nm = nm;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL=imageURL;
    }

    @Override
    public String toString() {
        return "JsonVo{" +
                "rank='" + rank + '\'' +
                ", Nm='" + Nm + '\'' +
                ", bitMap='" + bitmap + '\'' +
                '}';
    }
}
