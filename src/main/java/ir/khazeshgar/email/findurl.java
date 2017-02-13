package ir.khazeshgar.email;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.List;
/**
 * Created by mghasemy on 2/13/17.
 */
public class findurl {
    public String geturl(String u){
        String result="";
        Document doc;
        try {
            doc = Jsoup.connect(u).timeout(10*1000).get();
            List<Element> links=doc.getElementsByTag("a");
            int c=0;
            for(Element l:links){
                String link="";
                if(l.hasAttr("href")) {
                     link=u+ l.attr("href");
                }else{
                    continue;
                }
                if(l.text().toLowerCase().contains("contact")||l.text().contains("تماس")||l.text().contains("ارتباط")||l.attr("href").contains("contact")){
                        return link;
                }
                if(l.text().toLowerCase().contains("about")||l.text().contains("درباره")||l.attr("href").contains("about")){
                    return link;
                }
            }
        }catch(Exception s){
        s.printStackTrace();
        }
        return  result;
    }
}
