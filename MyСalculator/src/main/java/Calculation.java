import java.util.*;

public class Calculation {

    public void calc(String str){
        String string = str;
        int indMultiply = string.indexOf("*");
        int indSplit = string.indexOf("/");
        int indSum = string.indexOf("+");
        int indSubtract = string.indexOf("-");
        if(indMultiply != -1 && indSplit != -1){
            if(indMultiply < indSplit){
                calc(multiply(string, indMultiply,"*"));
            }else {
                calc(split(string, indSplit,"/"));
            }
        }else if(indMultiply != -1){
            calc(multiply(string, indMultiply,"*"));
        }else if(indSplit != -1){
            calc(split(string, indSplit,"/"));
        }else{
            if(indSum != -1 && indSubtract != -1){
                if(indSum < indSubtract){
                    calc(sum(string, indSum,"+"));
                }else {
                    calc(subtract(string, indSubtract, "-"));
                }
            }else if(indSum != -1){
                calc(sum(string, indSum,"+"));
            }else if(indSubtract > 0){
                calc(subtract(string, indSubtract, "-"));
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
             while(!res.equals("+") && !res.equals("-") && !res.equals("*") && !res.equals("/") && from != 0){
                 res = str.substring(--from, --to);
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
         while(!res.equals("+") && !res.equals("-") && !res.equals("*") && !res.equals("/") && to != str.length()){
             res = str.substring(++from, ++to);
         }
         if(to == str.length()){
             return new int[] {++indVal, to};
         }else{
             return new int[] {++indVal, --to};
         }
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
