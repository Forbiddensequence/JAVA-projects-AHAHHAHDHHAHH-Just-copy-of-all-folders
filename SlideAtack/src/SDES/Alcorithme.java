package SDES;

import java.io.IOException;
import java.math.BigInteger;

public class Alcorithme {

    public static int round(int Word,int k) {

        String word = new String();
        word = Integer.toBinaryString(Word);
        word = FeistelFunc.extentiontoByte(word);
        String RS = new String(word.substring(4,8));
        String LS = new String(word.substring(0,4));
        String NK = new String(Integer.toBinaryString(k));
        NK = FeistelFunc.extentiontoByte(NK);
        int  R = new BigInteger(RS,2).intValue();
        int  L = new BigInteger(LS,2).intValue();
        int Fei = FeistelFunc.feistel(R,k);
        int NR = FeistelFunc.xor(L,Fei);
        int NL = R;
        String NRS = new String(FeistelFunc.extentiontoFourBits(Integer.toBinaryString(NR)));
        String NLS = new String(FeistelFunc.extentiontoFourBits(Integer.toBinaryString(NL)));
        String Result=new String();
        Result=NLS.concat(NRS);
        int result =new BigInteger(Result,2).intValue();

        if (SDES.isPermissionToWrite()==true) {
            SDES.addStringinList("|-----------------------------| ");
            SDES.addStringinList("Входное слово раунда " + Word);
            SDES.addStringinList("Входное слово раунда " + word);
            SDES.addStringinList("Входной ключ "+ k);
            SDES.addStringinList("Левая половина слова " + LS);
            SDES.addStringinList("Правая половина слова "+ RS);
            SDES.addStringinList("Входной ключ для функции учебного алгоритма "+ NK);
            SDES.addStringinList("Резульльтат функции фейстеля " +Fei);
            SDES.addStringinList("Новая левая половина " +NLS);
            SDES.addStringinList("Новая правая половина " +NRS);
            SDES.addStringinList("Итоговое слово раунда " +result);
            SDES.addStringinList("|-----------------------------| ");
        }

        return result;
    }

    public static int sdes(int Word, int[] Key){
        String wordS=new String();
        wordS=Integer.toBinaryString(Word);
        wordS=FeistelFunc.extentiontoByte(wordS);
        int Round1;
        int Round2;
        Round1=round(Word,Key[0]);
        Round2=round(Round1,Key[1]);
        Round2=FeistelFunc.swapString(Round2);
        int result=Round2;

        if (SDES.isPermissionToWrite()==true) {
            SDES.addStringinList("Слово перед работой алгоритма " + Word);
            SDES.addStringinList("Слово перед начальной перестановкой " + wordS);
            SDES.addStringinList("Слово после первого раунда " + Round1);
            SDES.addStringinList("Слово после второго раунда " + Round2);
            SDES.addStringinList("Слово после работы алгоритма " + result);
        }

        return result;
    }

/*
    public static int studyalgorithmeinversion(int Word, int[] Key){
        SDES.addStringinList("Слово перед работой обратного алгоритма " + Word);
        String wordS=new String();
        wordS=Integer.toBinaryString(Word);
        wordS=Feistel.extentiontoByte(wordS);
        SDES.addStringinList("Слово перед начальной перестановкой " + wordS);
        String wordIP=new String();
        wordIP=IP(wordS);
        SDES.addStringinList("Слово после начальной перестановки " + wordIP);
        //wordIP=Feistel.extentiontoByte(wordIP);
        int  PREV= new BigInteger(wordIP,2).intValue();
        int Round1;
        int Round2;
        Round1=round(PREV,Key[1]);
        //Round1=Feistel.swapString(Round1);
        SDES.addStringinList("Слово после первого раунда " + Round1);
        Round2=round(Round1,Key[0]);
        Round2=Feistel.swapString(Round2);
        SDES.addStringinList("Слово после второго раунда " + Round2);
        String wordIPprinv=new String();
        wordIPprinv=Integer.toBinaryString(Round2);
        wordIPprinv=Feistel.extentiontoByte(wordIPprinv);
        SDES.addStringinList("Слово перед конечной перестановкой " + wordIPprinv);
        String wordIPinv=new String();
        wordIPinv=IPinv(wordIPprinv);
        SDES.addStringinList("Слово после конечной перестановки " + wordIPinv);
        int result=new BigInteger(wordIPinv,2).intValue();
        SDES.addStringinList("Слово после работы обратного алгоритма " + result);
        return result;
    }
*/


    public static String extentiontoTwentyfourBits(String result){
        while(result.length()<24){
            result="0".concat(result);
        }
        return result;
    }

    public static String Shift(String input, int number){
        String result=input;
        String dop=result.substring(1,input.length());
        while((number<0)||(number>=input.length())){
            if(number<0){
                number+=input.length();
            }
            if(number>=input.length()){
                number-=input.length();
            }
        }
        for(int i=0;i<number;i++){
            result=dop.concat(String.valueOf(result.charAt(0)));
            dop=result.substring(1,input.length());
        }
        return result;
    }



}
