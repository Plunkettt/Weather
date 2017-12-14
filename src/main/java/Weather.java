import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Weather {

    static String [] words;
    static Map <String, Integer> tMap = new HashMap ();


    private Weather () {

        try {

            //String urlEntry = "fail";  //todo catch fails :(

            //String urlEntry = "https://en.wikipedia.org/wiki/Java_(programming_language)";

            //String urlEntry = "http://www.sciencemag.org/news/2017/12/saturn-s-rings-are-recent-addition-solar-system-cassini-observations-show";

            //String urlEntry = "https://www.theguardian.com/politics/2017/dec/13/tory-brexit-rebels-inflict-major-defeat-on-theresa-may";

            //String urlEntry = "http://wiadomosci.gazeta.pl/wiadomosci/7,114884,22780022,krrit-zarzucila-tvn-celowe-opoznianie-informacji-stacja-przejrzala.html#MT2";

            //String urlEntry = "https://www.tvn24.pl/magazyn-tvn24/bankier-na-czele-rzadu-ma-stworzyc-uklad-ktory-bedzie-sluszny,129,2299";

            String urlEntry = "https://www.tvn24.pl/magazyn-tvn24/scs-staje-sie-faktem-ten-system-zmieni-wszystko,128,2284";

            Document doc = Jsoup.connect(urlEntry).get();


            Elements arti = doc.select("p");

            String proc = arti.text();

            //checkpoint entire article in a single line
            //System.out.println(proc);

            //entire www page
            //System.out.println(doc);

            Scanner scanny = new Scanner(proc);  //casting!

            while (scanny.hasNextLine()) {

                String line = scanny.nextLine();
                words = line.split(" ");

                for (String f : words) {

                    String s = cleanWithBroom(f);

                    String proceed = s.toLowerCase();

                    if (s.trim().equals("\n") || s.trim().equals("") || s.trim().equals("-")) {  // can't remove BLOODY hyphen :(
                        continue;
                    }

                    if (tMap.containsKey(proceed)) {
                        tMap.put(proceed, tMap.get(proceed) + 1);
                    } else {
                        tMap.put(proceed, 1);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "OMFG YAY");
        } catch (IOException e) {
            System.out.println("FAIL");
        }
    }

    private static void output(){
        //checkpoint -> all keys, all values, set view
        //System.out.println(tMap.keySet());
        //System.out.println(tMap.values());
        System.out.println(tMap.entrySet());
        //System.out.println("\n"+tMap.get("ten"));

    }

    private static String cleanWithBroom(String toClean){

            toClean=toClean.replaceAll(",$|^,|\\.$|-|:$|^\"|\"$|\".$|\\?$|;$|\\..$|\\...$|:\\)$|\\)$|^\\(|\\)\\.$|\\s|\\),$|^\\[|]$","");

        return toClean;
    }


    public static void main(String[] args) throws IOException {

        Weather w1 = new Weather();

       // acquire(); //got converted into a constructor :)

        output();

    }
}
