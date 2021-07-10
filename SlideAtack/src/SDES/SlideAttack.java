package SDES;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.module.FindException;
import java.security.Key;
import java.util.ArrayList;

public class SlideAttack {
    public static String[][] AnalysingCipherText= {{"00110101", "10110110", "01010001", "00011011"},
            {"01010101", "10111001", "01010001", "00011011"},
            {"10100101", "11011111", "01011000", "00011101"},
            {"11000101", "11010000", "01011000", "00011101"},
            {"11110101", "01111001", "01011101", "10000111"}
    };

    public static void startSlideAttack(){
        showS();
        String PossibleKeys[] = analysisofcipher(AnalysingCipherText);


        System.out.println("Possible variants of key are equal to");
        for(int i=0;i<PossibleKeys.length;i++){
            System.out.println(PossibleKeys[i]);
        }
        for(int i=0;i<PossibleKeys.length;i++){
            //System.out.println(PossibleKeys[i]);
            for(int j=0;j<256;j++){
                String Data=FeistelFunc.extentiontoByte(FeistelFunc.toBinar(j));
                SDES.Data=j;
                SDES.Key=FeistelFunc.toInt(PossibleKeys[i]);
                String result=FeistelFunc.extentiontoByte(FeistelFunc.toBinar(SDES.SDES()));
                //System.out.println(Data+" "+result);
            }
        }
        //System.out.println(lookupKeywithAnalysis(PossibleKeys));
        //System.out.println(lookupKeywithoutAnalysis());
    }

    public static String lookupKeywithoutAnalysis(){
        String result=null;
        int Data=0;

        //SDES.Data=Data;
        int boundary=FeistelFunc.toInt("100000000");
        for(int i=254;i<boundary;i++){
            String Key=FeistelFunc.extentiontoNBit(FeistelFunc.toBinar(i),8);
            int TestKey=i;
            SDES.Key=TestKey;
            boolean isKey=true;
            for (int j = 0; j< SlideAttack.AnalysingCipherText.length; j++){
                Data=FeistelFunc.toInt(SlideAttack.AnalysingCipherText[j][0]);
                SDES.Data=Data;
                int ciphertext=SDES.SDES();
                int rs=FeistelFunc.toInt(SlideAttack.AnalysingCipherText[j][1]);
                if(rs==ciphertext){
                    continue;
                }
                else{
                    isKey=false;
                    break;
                }
            }
            if(isKey){
                result=Key;
                break;
            }
            //System.out.println("TestKey is equal to "+Key);
        }
        return result;
    }

    public static String[] analysisofcipher(String[][] AnalysingText){
        int[] KeyArray=new int[256];
        for(int i=0;i<AnalysingText.length;i++){
            System.out.println("Анализ "+(i+1)+" пары текстов");
            System.out.print("XL="+AnalysingText[i][0].substring(0,4)+", XR="+AnalysingText[i][0].substring(4,8));
            System.out.println(", XL\'="+AnalysingText[i][2].substring(0,4)+", XR\'="+AnalysingText[i][2].substring(4,8));
            String X=AnalysingText[i][0].substring(4,8);
            String Y=FeistelFunc.extentiontoFourBits(FeistelFunc.xor(AnalysingText[i][0].substring(0,4),AnalysingText[i][2].substring(4,8)));
            System.out.println("Вход функции F будет равен X=XR="+AnalysingText[i][0].substring(4,8));
            System.out.println("Выход функции F будет равен Y=XR\'\u2295XL="+AnalysingText[i][2].substring(4,8)+"\u2295"+AnalysingText[i][0].substring(0,4)+"="+Y);
            String[] K1=analysisofround(X,Y);


            System.out.print("YL="+AnalysingText[i][1].substring(0,4)+", YR="+AnalysingText[i][1].substring(4,8));
            System.out.println(", YL\'="+AnalysingText[i][3].substring(0,4)+", YR\'="+AnalysingText[i][3].substring(4,8));
            X=AnalysingText[i][3].substring(4,8);
            Y=FeistelFunc.extentiontoFourBits(FeistelFunc.xor(AnalysingText[i][3].substring(0,4),AnalysingText[i][1].substring(4,8)));
            System.out.println("Вход функции F будет равен X=YR\'="+AnalysingText[i][3].substring(4,8));
            System.out.println("Выход функции F будет равен Y=YL\'\u2295YR="+AnalysingText[i][3].substring(0,4)+"\u2295"+AnalysingText[i][1].substring(4,8)+"="+Y);
            String[] K2=analysisofround(X,Y);

            String[] K=KeyFilter(K1,K2);

            for(int j=0;j<K.length;j++){
                KeyArray[FeistelFunc.toInt(K[j])]++;
            }

            if (K.length==0){
                System.out.println("В данном наборе значения ключа при анализе по Y и по X не совпали");
            }
            else {
                System.out.print("Выберем те вероятные ключи, которые встретились и при анализе по X, и при анализе по Y, тогда для данного текста они будут равны : ");
                for(int j=0;j<K.length;j++){
                    System.out.print(K[j]+" ");
                }
                System.out.println();
            }
        }
        for(int i=0;i<KeyArray.length;i++){
            //System.out.println(FeistelFunc.extentiontoByte(FeistelFunc.toBinar(i))+"  "+ KeyArray[i]);
        }
        String[] resultVariants=findingpossiblevariantsofKey(KeyArray);
        System.out.println("Наиболее часто встречаемые подключи : ");
        for(int i=0;i<resultVariants.length;i++){
            System.out.println(resultVariants[i]+" - Частота появления "+KeyArray[FeistelFunc.toInt(resultVariants[i])]+" текстов из "+AnalysingText.length);
        }

        return resultVariants;
    }

    public static String[] findingpossiblevariantsofKey(int[] keyarray){
        int localmax=0;
        int numberofelements=0;
        int localmaxminusone=0;

        for(int j=0;j<keyarray.length;j++){
            if(keyarray[j]>=localmax){
                localmaxminusone=localmax;
                localmax=keyarray[j];
                localmaxminusone=localmax;
            }
        }
        ArrayList<String> list=new ArrayList<String>();
        for(int j=0;j<keyarray.length;j++){
            if(keyarray[j]>=localmaxminusone){
                numberofelements++;
                list.add(FeistelFunc.extentiontoByte(FeistelFunc.toBinar(j)));
            }
        }
        String[] result=new String[numberofelements];
        for(int j=0;j<result.length;j++){
            result[j]=list.get(j);
        }
        return result;
    }


    public static String[] KeyFilter(String[] K1,String[] K2){
        int counter=0;
        for(int i=0;i<K1.length;i++){
            for(int j=0;j<K2.length;j++){
                if(FeistelFunc.toInt(K1[i])==FeistelFunc.toInt(K2[j])){
                    counter++;
                }
            }
        }
        String[] Kclear=new String[counter];
        if(counter==0){
            return Kclear;
        }
        counter=0;
        for(int i=0;i<K1.length;i++){
            for(int j=0;j<K2.length;j++){
                if(FeistelFunc.toInt(K1[i])==FeistelFunc.toInt(K2[j])){
                    Kclear[counter]=K1[i];
                    counter++;
                }
            }
        }
        return Kclear;
    }


    public static String[] analysisofround(String X,String Y){
        //System.out.println("На входе функции F "+X+",на выходе - "+Y);
        System.out.println("На входе функции F "+X+",на выходе - "+Y);
        String A=FeistelFunc.EandP(X);
        String C=FeistelFunc.invP(Y);
        System.out.println("После применения обратной перстановки на выходе F получится значение "+C +", на входе после применения перестановки с расширением - "+A);
        String C1=C.substring(0,2);
        String C2=C.substring(2,4);
        String[] B1=FeistelFunc.invS1(C1);
        String[] B2=FeistelFunc.invS2(C2);
        System.out.print("После прохождения блока S1 вероятные значения будут равны : ");
        for(int i=0;i<B1.length;i++){
            System.out.print(B1[i]+" ");
        }
        System.out.println();
        System.out.print("После прохождения блока S2 вероятные значения будут равны : ");
        for(int i=0;i<B2.length;i++){
            System.out.print(B2[i]+" ");
        }
        System.out.println();

        String[] resultK=new String[0];
        String[] B=new String[B1.length*B2.length];
        int counter =0;
        for(int i=0;i<B1.length;i++){
            for(int j=0;j<B2.length;j++){
                B[counter]=B1[i].concat(B2[j]);
                counter++;
            }
        }
        if(counter==0){
            return resultK;
        }
        resultK=new String[B.length];
        for(int i=0;i<B.length;i++){
            resultK[i]= FeistelFunc.extentiontoByte(FeistelFunc.xor(B[i],A));
        }
        System.out.print("Наиболее вероятные ключи для данного набора :");
        for(int i=0;i<resultK.length;i++){
            System.out.print(resultK[i]+" ");
        }
        System.out.println();
        return resultK;
    }

    public static void showS(){
        for(int i=0;i<16;i++){
            String X1=FeistelFunc.extentiontoFourBits(FeistelFunc.toBinar(i));
            String X2=FeistelFunc.extentiontoFourBits(FeistelFunc.toBinar(i));
            String Y1=FeistelFunc.extentiontoThreeBits(FeistelFunc.S1(X1));
            String Y2=FeistelFunc.extentiontoThreeBits(FeistelFunc.S2(X2));
            System.out.println(X1+";"+Y1+";;"+X2+";"+Y2);

        }
    }


}
