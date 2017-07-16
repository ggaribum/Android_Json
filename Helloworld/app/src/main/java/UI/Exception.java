package UI;

import java.util.ArrayList;

import Vo.MemberVo;

import static com.onetwopunch.helloworld.BaseActivity.dbmanager;

/**
 * Created by Administrator on 2017-07-11.
 */

public class Exception {

    ArrayList<MemberVo> memberList;

    public boolean idCheck(String id)
    {
        memberList=dbmanager.readAllData();
        for(int i=0; i<memberList.size();i++)
        {
            if(id.equals(memberList.get(i).getId()))
            {
                //데이터베이스에 해당 id가 있으면
                return false;
            }
        }
        return true;
    }
    public boolean pwCheck(String pw, String pw2)
    {
        if(pw.equals(pw2))
        {
            //비밀번호가 일치하면
            return true;
        }
        else
        {return false;}
    }

    public String logIn(String id, String pw)
    {
        memberList=dbmanager.readAllData();
        for(int i=0;i<memberList.size();i++)
        {
            if(id.equals(memberList.get(i).getId()))
            {
                if(pw.equals(memberList.get(i).getPw()))
                {
                    //해당 id가 있고 pw도 같다면
                    return memberList.get(i).getName();
                }
            }
        }
        //아니라면 f 리턴
        return "f";
    }
}
