import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckAndCleanString {
    private static final Logger logger = LoggerFactory.getLogger(CheckAndCleanString.class);

    public String checksAndClean (String string){
        String str = string;
        String cleanString = removeExtraCharacters(str);
        if(cleanString.length()>0){
            Boolean checked = checkString(cleanString);
            if(checked){
                logger.info("Checking string success!");
                return cleanString;
            }else{
                logger.error("Checking string failure!");
                return  "";
            }
        }
        return "";
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
            logger.error("Checking string failure! The string contains invalid characters!");
            return false;
        }else if(regExp(string,"\\(-?\\d+((\\+|\\-|\\*|\\/)\\d*)+\\)")){
            logger.error("Checking string failure! In the line there is an expression with a priority in brackets!");
            return false;
        }else if(regExp(string,"[\\-|\\+|\\*|\\/][+\\*\\/]+")){
            logger.error("Checking string failure! The line contains two characters of the operation in a row!");
            return false;
        }else if(regExp(string,"\\-{2}")){
            logger.error("Checking string failure! The line contains two characters minus of the operation in a row!");
            return false;
        }else if(regExp(string,"\\(\\)")){
            logger.error("Checking string failure! The line contains empty brackets!");
            return false;
        }else if(regExp(string,"(\\/0+(\\/|\\*|\\+|\\-))")){
            logger.error("Checking string failure! The line contains division by zero!");
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
