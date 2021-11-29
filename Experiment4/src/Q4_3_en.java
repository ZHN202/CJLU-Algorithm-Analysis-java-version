import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q4_3_en {
    public static void main(String[] args) {
        String filename = "/home/robert/Algorithm-Analysis-java-version/Experiment4/article.txt";
        int total=0;
        Map<String,Integer> map = new TreeMap<>();
        try (Scanner sc = new Scanner(new FileReader(filename))) {
            sc.useDelimiter(" ");  //分隔符

            while (sc.hasNext()) {   //按分隔符读取字符串
                String str = sc.next();
                str = str.toLowerCase();

                System.out.println(str);
                str = str.replaceAll("[^a-z]", "");
                if(map.containsKey(str)){
                    map.replace(str,map.get(str)+1);
                }
                else{
                    map.put(str,1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        map.forEach((key,value)->System.out.println(key+":"+value));

    }
}


