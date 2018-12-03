import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculation {
    private static final Logger logger = LoggerFactory.getLogger(Calculation.class);

    public String сalculate(String str){
        int[] operIndexes = getIndexes(str);
        try {
            while (operIndexes[0] > 0 || operIndexes[1] > 0 || operIndexes[2] >  0 || operIndexes[3] > 0){
                str = processingCalc(str,operIndexes[0],operIndexes[1],operIndexes[2],operIndexes[3]);
                operIndexes = getIndexes(str);
            }
            return str;
        }catch (Exception ex){
            logger.error("An error occurred during the calculation. Error: " + ex.getMessage());
           return "";
        }

    }

    private int[] getIndexes(String str){
        int indMultiply = str.indexOf("*",1);
        int indSplit = str.indexOf("/", 1);
        int indSum = str.indexOf("+", 1);
        int indSubtract = str.indexOf("-", 1);
        return new int[] {indMultiply, indSplit, indSum, indSubtract};
    }

    private String processingCalc(String str, int indMultiply, int indSplit, int indSum, int indSubtract){
        if(indMultiply != -1 && indSplit != -1){
            if(indMultiply < indSplit)
                return multiply(str, indMultiply);
            else
                return split(str, indSplit);
        }else if(indMultiply != -1){
            return multiply(str, indMultiply);
        }else if(indSplit != -1){
            return  split(str, indSplit);
        }else if(indSum != -1 && indSubtract != -1){
            if(indSum < indSubtract)
                return sum(str, indSum);
            else
                return subtract(str, indSubtract);
        }else if(indSum != -1){
            return sum(str, indSum);
        }if(indSubtract != -1){
            return subtract(str, indSubtract);
        }
       return str;
    }

    private String multiply (String str, int index){
        String[] values  = getValues(str, index);
        Double firstValue = Double.parseDouble(values[0]);
        Double secondValue = Double.parseDouble(values[1]);
        double result = firstValue * secondValue;
        String regExp = values[0] + "\\*"+ values[1];
        return str.replaceFirst(regExp, Double.toString(result));
    }

    private String split (String str, int index){
        String[] values  = getValues(str,index);
        Double firstValue = Double.parseDouble(values[0]);
        Double secondValue = Double.parseDouble(values[1]);
        double result = firstValue / secondValue;
        String regExp = values[0] + "\\/" + values[1];
        return str.replaceFirst(regExp, Double.toString(result));
    }

    private String sum (String str, int index){
        String[] values  = getValues(str,index);
        Double firstValue = Double.parseDouble(values[0]);
        Double secondValue = Double.parseDouble(values[1]);
        double result = firstValue + secondValue;
        String regExp = values[0] + "\\+"+ values[1];
        return str.replaceFirst(regExp, Double.toString(result));
    }
    private String subtract (String str, int index){
        String[] values  = getValues(str, index);
        Double firstValue = Double.parseDouble(values[0]);
        Double secondValue = Double.parseDouble(values[1]);
        double result = firstValue - secondValue;
        String regExp = values[0] + "\\-" + values[1];
        return str.replaceFirst(regExp, Double.toString(result));
    }

    private String[] getValues(String str,int index){
        int[] prevIndexes = getPrevIndexes(str, index);
        int[] nextIndexes = getNextIndexes(str, index);
        if(prevIndexes.length > 0 && nextIndexes.length > 0){
            String firstVal = str.substring(prevIndexes[0], prevIndexes[1]);
            String secondVal = str.substring(nextIndexes[0], nextIndexes[1]);
            return new String[] {firstVal,secondVal};
        }
        return new String[] {};
    }

    private int[] getPrevIndexes (String str, int indVal){
        int to = indVal;
        int from = indVal;
        try {
            String res = str.substring(--from, to);
            while(!res.equals("+") && !res.equals("*") && !res.equals("/") && from != 0){
                if(res.equals("-")){
                    if(!checkWithRegExp(str.substring(--from, --to))){
                        ++from;
                        ++to;
                    }
                    break;
                }else{
                    res = str.substring(--from, --to);
                }
            }
            if(from == 0){
                return new int[] {from,indVal};
            }else{
                return new int[] {++from,indVal};
            }
        }catch (ArrayIndexOutOfBoundsException ex){
            logger.error(ex.getMessage());
            return new int[]{};
        }
    }

     private int[] getNextIndexes (String str, int indVal){
         int to = 2 + indVal;
         int from = indVal;
         try {
             String res = str.substring(++from, to);
             if (res.equals("-")){
                 res = str.substring(++from, ++to);
             }
             while(!checkWithRegExp(res) && to < str.length()){
                 res = str.substring(++from, ++to);
             }
             if(to == str.length()){
                 return new int[] {++indVal, to};
             }else{
                 return new int[] {++indVal, --to};
             }
         }catch (ArrayIndexOutOfBoundsException ex){
             logger.error(ex.getMessage());
             return new int[]{};
         }
     }

    public static boolean checkWithRegExp(String symbol){
        Pattern p = Pattern.compile("[-+*/]");
        Matcher m = p.matcher(symbol);
        return m.matches();
    }
}
