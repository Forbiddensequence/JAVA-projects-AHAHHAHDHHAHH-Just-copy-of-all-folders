package SDES;

import java.util.ArrayList;


public class SDES {

    public static int Data=192;
    public static int Key=127;
    public static int[] EandP={3,4,2,1,1,4,2,3};
    public static int[] P={1,3,4,2};
    public static int[] S1={1,0,3,2,
            3,1,2,0,
            0,2,1,3,
            2,3,0,1};
    public static int[] S2={2,3,0,1,
            3,2,1,3,
            0,1,2,0,
            1,3,0,2};    public static String[] resultDocument;
    public static ArrayList<String> list;

    private static boolean permissionToWrite=false;


    public static boolean isPermissionToWrite() {
        return permissionToWrite;
    }

    public static void setPermissionToWrite(boolean permissionToWrite) {
        SDES.permissionToWrite = permissionToWrite;
    }

    public static int SDES(){
        int K;
        int promkey=Key;
        int word=Data;
        list=new ArrayList<String>();
        K = SDES.Key;

        if (isPermissionToWrite()==true) {
            addStringinList("Данные для шифрования  "+ word+" - "+FeistelFunc.extentiontoByte(FeistelFunc.toBinar(word)));
            addStringinList("Ключ из которого извлекаем раундовые "+promkey+" - "+Alcorithme.extentiontoTwentyfourBits(FeistelFunc.toBinar(promkey)));
            addStringinList("1 раундовый ключ "+K+" - "+FeistelFunc.extentiontoTwelveBits(FeistelFunc.toBinar(K)));
            addStringinList("2 раундовый ключ "+K+" - "+FeistelFunc.extentiontoTwelveBits(FeistelFunc.toBinar(K)));

        }
        int[] Karr={K,K};
        int res = Alcorithme.sdes(word,Karr);

        SDES.resultDocument=new String[list.size()];
        for(int i=0;i<list.size();i++){
            SDES.resultDocument[i]=list.get(i);
        }

        /*
        int res2= AlCorithme.sdesinv(res,K);

        addStringinList("Результат алгоритма S-DES "+res+ " - "+Feistel.extentiontoByte(Feistel.toBinar(res)));

        addStringinList("Результат расшифровки алгоритма S-DES "+res2+ " - "+Feistel.extentiontoByte(Feistel.toBinar(res2)));
         */
        return res;
    }

    public static void addStringinList(String input){
        list.add(input);
    }

/*
    public static void addarrayinDec(int[] arr){
        String res=new String();
        for(int i=0;i<arr.length;i++){
            res=res.concat(String.valueOf(arr[i]).concat(" "));
        }
        list.add(res);
    };

    public static void addarrayinBin(int[] arr){
        String res=new String();
        for(int i=0;i<arr.length;i++){
            res=res.concat(Feistel.toBinar(arr[i]).concat(" "));
        }
        list.add(res);
    };


 */

}



