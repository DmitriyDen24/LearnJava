import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CheckAndCleanString {

    public String checksAndClean (String string){
        String str = string;
        String cleanString = removeExtraCharacters(str);
        Boolean checked = checkString(cleanString);
        if(checked){
            return cleanString;
        }else{
            return  "";
        }
    }

    private String removeExtraCharacters(String string){
        String resultStr = string.replaceAll(" ","");
        resultStr = resultStr.replaceAll("--","+");
        Pattern p = Pattern.compile("\\(-?\\d+\\)");
        Matcher m = p.matcher(resultStr);
        while (m.find()){
            int from = m.start();
            int to = m.end();
            resultStr = resultStr.replace(resultStr.substring(from,to),resultStr.substring(++from,--to));
            m = p.matcher(resultStr);
        }
        return resultStr;
    }

    private Boolean checkString(String string){
        if(regExp(string,"[^-+*/0-9\\(\\)\\s\\.]")){
            return false;
        }else if(regExp(string,"\\(-?\\d+((\\+|\\-|\\*|\\/)\\d*)+\\)")){
            return false;
        }else if(regExp(string,"[\\-|\\+|\\*|\\/][+\\*\\/]+")){
            return false;
        }else if(regExp(string,"\\-{2}")){
            return false;
        }else if(regExp(string,"\\(\\)")){
            return false;
        }else if(regExp(string,"\\(\\)")){
            return false;
        }else if(regExp(string,"(\\/0+(\\/|\\*|\\+|\\-))")){
            return false;
        }
        return true;
    }

    private Boolean regExp(String string, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);
        return m.find();
    }
}
