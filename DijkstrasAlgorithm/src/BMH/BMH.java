package BMH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BMH {
    public static int lastindex(String el, String analstring){
        if(el.length()!=1||analstring.length()<1){
            return 0;
        }
        int resultindex=-1;
        for(int i=0;i<analstring.length()-1;i++) {
            if (analstring.substring(i, i + 1).equals(el)) {
                resultindex=i;
            }
        }
        resultindex=analstring.length()-resultindex-1;
        return resultindex;
    }


    public static int algorithm(String substring,String string){
        if (substring.length()<1||string.length()<substring.length()){
            return -1;
        }
        ArrayList<String> elementsinsubstring=new ArrayList<String>();
        Map<String,Integer> dictionary= new HashMap<String,Integer>();
        for(int i=0;i<substring.length()-1;i++){
            String letter=substring.substring(i,i+1);
            if(!elementsinsubstring.contains(letter)){
                elementsinsubstring.add(letter);
                dictionary.put(letter,lastindex(letter,substring));
            }

        }

        System.out.println("Dictionary of shifts is equal to");
        System.out.println(dictionary);
        int i=substring.length();
        while (i<string.length()) {
            String checkstring=string.substring(i - substring.length(), i);
            if (checkstring.equals(substring)) {
                return i-substring.length();
            }
            int shift = substring.length();
            String letter = string.substring(i-1, i);
            if (dictionary.containsKey(letter)) {
                shift = dictionary.get(letter);
            }
            i+=shift;
        }
        return -1;

    }


}
