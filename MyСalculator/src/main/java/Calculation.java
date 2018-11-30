import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculation {

    public void calc(String str, int startInexOf){
        String string = str;
        int indMultiply = string.indexOf("*",startInexOf);
        int indSplit = string.indexOf("/", startInexOf);
        int indSum = string.indexOf("+", startInexOf);
        int indSubtract = string.indexOf("-", startInexOf);
        if(indMultiply != -1 && indSplit != -1){
            if(indMultiply < indSplit){
                calc(multiply(string, indMultiply,"*"),0);
            }else {
                calc(split(string, indSplit,"/"),0);
            }
        }else if(indMultiply != -1){
            calc(multiply(string, indMultiply,"*"),0);
        }else if(indSplit != -1){
            calc(split(string, indSplit,"/"),0);
        }else{
            if(indSum != -1 && (indSubtract != -1 && indSubtract > 0)){
                if(indSum < indSubtract){
                    calc(sum(string, indSum,"+"),0);
                }else {
                    calc(subtract(string, indSubtract, "-"),0);
                }
            }else if(indSum != -1){
                calc(sum(string, indSum,"+"),0);
            }else if(indSubtract >= 0){
                if(indSubtract == 0){
                    calc(string,1);
                }else{
                    calc(subtract(string, indSubtract, "-"),0);

                }
            }else{
                System.out.println(string);
            }
        }
    }

    private String multiply (String string, int index, String prefix){
        String[] values  = getValues(string, index);
        String resCalc = executeMultiply(values[0], values[1]);
        string = string.replace(values[0] + prefix + values[1], resCalc);
        return string;
    }

    private String split (String string, int index, String prefix){
        String[] values  = getValues(string,index);
        String resCalc = executeSplit(values[0], values[1]);
        string = string.replace(values[0] + prefix + values[1], resCalc);
        return string;
    }

    private String sum (String string, int index, String prefix){
        String[] values  = getValues(string,index);
        String resCalc = executeSum(values[0], values[1]);
        string = string.replace(values[0] + prefix + values[1], resCalc);
        return string;
    }
    private String subtract (String string, int index, String prefix){
        String[] values  = getValues(string,index);
        String resCalc = executeSubtract(values[0], values[1]);
        string = string.replace(values[0] + prefix + values[1], resCalc);
        return string;
    }

    private String[] getValues(String string,int index){
        String firstVal = string.substring(getPrevIndexes(string, index)[0], getPrevIndexes(string,index)[1]);
        String secondVal = string.substring(getNextIndexes(string, index)[0], getNextIndexes(string,index)[1]);
        return new String[] {firstVal,secondVal};
    }

     private int[] getPrevIndexes (String str, int indVal){
        if(indVal != 0){
             int to = indVal;
             int from = indVal;
             String res = str.substring(--from, to);
             Boolean checkMinusVal = false;
             while(!res.equals("+") && !res.equals("*") && !res.equals("/") && from != 0){
                 if(res.equals("-")){
                     checkMinusVal = checkWithRegExp(str.substring(--from, --to));
                     if(checkMinusVal){
                        res = str.substring(from, to);
                        break;
                     }else{
                         ++from;
                         ++to;
                         break;
                     }
                 }else{
                     res = str.substring(--from, --to);
                 }
             }
             if(from == 0){
                 return new int[] {from,indVal};
             }else{
                return new int[] {++from,indVal};
             }
         }else{
             return new int[] {indVal,++indVal};
         }
     }

     private int[] getNextIndexes (String str, int indVal){
         int to = 2 + indVal;
         int from = indVal;
         String res = str.substring(++from, to);
         if (res.equals("-")){
             res = str.substring(++from, ++to);
         }
         Boolean checkVal = checkWithRegExp(res);
         while(!checkVal && to < str.length()){
             res = str.substring(++from, ++to);
             checkVal = checkWithRegExp(res);
         }
         if(to == str.length()){
             return new int[] {++indVal, to};
         }else{
             return new int[] {++indVal, --to};
         }
     }

    public static boolean checkWithRegExp(String userNameString){
        Pattern p = Pattern.compile("[+,\\-,*,\\/]");//("^[a-z0-9_-]{3,15}$");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }

    public String executeSum (String a, String b){
        Double firstValue = Double.parseDouble(a);
        Double secondValue = Double.parseDouble(b);
        double result = firstValue + secondValue;
        return Double.toString(result);
    }

    public String executeSubtract (String a, String b){
        Double firstValue = Double.parseDouble(a);
        Double secondValue = Double.parseDouble(b);
        double result = firstValue - secondValue;
        return Double.toString(result);
    }

    public String executeMultiply (String a, String b){
        Double firstValue = Double.parseDouble(a);
        Double secondValue = Double.parseDouble(b);
        double result = firstValue * secondValue;
        return Double.toString(result);
    }

    public String executeSplit (String a, String b){
        Double firstValue = Double.parseDouble(a);
        Double secondValue = Double.parseDouble(b);
        double result = firstValue / secondValue;
        return Double.toString(result);
    }
}
