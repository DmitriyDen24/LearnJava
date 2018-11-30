import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Priority {
    public void test (){
      String s = "+";
      Boolean b = checkWithRegExp(s);
        if(b){
            System.out.print(s);
        }
    }

    public static boolean checkWithRegExp(String userNameString){
        Pattern p = Pattern.compile("[+,\\-,*,\\/]");//("^[a-z0-9_-]{3,15}$");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
//        Map<String, String> sta = new HashMap<String, String>();
//        sta.put("a1","1+1");
//        sta.put("a1","2+2");
//
//        List<List<String>> list = new ArrayList<>();
//        list.add(new ArrayList<>());
//        list.get(0).add("Blaha1!");
//        list.get(0).add("Blaha2!");
//        list.add(new ArrayList<>());
//        list.get(1).add("text");
////        list.get(1).add("text");
//        for (List i: list) {
//            System.out.print(i);
//        }
//    }
//
//    private String retVal(String string){
//        int indMultiply = string.indexOf("*");
//        int indSplit = string.indexOf("/");
//        int indSum = string.indexOf("+");
//        int indSubtract = string.indexOf("-");
//        return "";
//    }
}
