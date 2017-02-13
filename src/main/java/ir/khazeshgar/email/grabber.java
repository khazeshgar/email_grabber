package ir.khazeshgar.email;

import ir.khazeshgar.util.io;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class grabber{
    OkHttpClient client = new OkHttpClient();

    public String gettextofurl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String grab(String input){
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        String email="";
        while(matcher.find()) {
            email+=matcher.group()+",";
        }
        return  email;
    }
    public boolean start(){
        io i=new io();
        ClassLoader classLoader = getClass().getClassLoader();
        //File file = new File(classLoader.getResource("urls.csv").getFile());
        File file = new File("sites.txt");
        List<String> all=i.readfromfile(file);
        findurl fu=new findurl();
        for(String s:all){
            System.out.print("Start:"+s);
            try {
                String email=grab(gettextofurl(s));
                if(email.length()==0){
                    String nu=fu.geturl(s);
                    email=grab(gettextofurl(nu));
                    //System.out.println(nu);
                }
                i.savetocsv(s+","+email,"emails.csv");
            }catch (Exception as){as.printStackTrace();}
            System.out.println("FINISH.");
        }
        return false;
    }
    public static void main(String a[]){
        grabber g=new grabber();
        System.out.println("...Starting ir.khazeshgar.email.grabber main function...");
        g.start();
        System.exit(1);
    }
}