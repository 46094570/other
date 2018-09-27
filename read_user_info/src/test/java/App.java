import com.hs.datatrans.utils.qianpen.utils.HttpTools;

import java.util.HashMap;

public class App {
    {
        System.out.println("这是代码块");
    }
    public App(){
        System.out.println("这是构造方法");
    }
    public static void main(String[] args) throws  Exception{
//        try {
//        String s = HttpTools.doFormPost("http://116.1.201.186:28188/webapp/auth/handshakeOne", new HashMap<String, String>());
//        System.out.println("s:"+s);
//        String s1 = HttpTools.doFormPost("http://116.1.201.186:9902/webapp/auth/handshakeOne", new HashMap<String, String>());
//        System.out.println("s1:"+s1);
//        String s2 = HttpTools.doFormPost("http://116.1.201.186:28188/webapp/out/dxiang/account/bd/list/131638", new HashMap<String, String>());
//        System.out.println("s2:"+s2);
//        String s3 = HttpTools.doFormPost("http://116.1.201.186:9902/out/dxiang/account/bd/list/131638", new HashMap<String, String>());
//        System.out.println("s3:"+s3);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        new App();
    }
}
