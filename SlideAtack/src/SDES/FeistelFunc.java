package SDES;

import java.math.BigInteger;

public class FeistelFunc{

    /**
     * Function F in study algorithm
     * @param R0int 8 bits number
     * @param Kint 12 bits key
     * @return f(R0,K)
     */
    public static int feistel(int R0int, int Kint) {
        String X=new String();
        String K=new String();
        String A=new String();
        String B=new String();
        String C1=new String();
        String C2=new String();
        String C=new String();
        String Y=new String();
        X = toBinar(R0int);
        K = toBinar(Kint);
        X = extentiontoFourBits(X);
        K=extentiontoByte(K);
        A=EandP(X);

        B=extentiontoByte(xor(A,K));

        String A1=new String(B.substring(0,4));
        String A2=new String(B.substring(4,8));

        C1=S1(A1);
        C2=S2(A2);
        C=C1.concat(C2);
        Y= P4(C);

        int  result= new BigInteger(Y,2).intValue();

        if (SDES.isPermissionToWrite()==true) {
            SDES.addStringinList("<-----------------------------> ");
            SDES.addStringinList("Половина для пребразования функцией Фейстеля "+R0int);
            SDES.addStringinList("Ключ для функции Фейстеля  "+Kint);
            SDES.addStringinList("Двоичное число для шифрования "+X);
            SDES.addStringinList("Двоичный ключ для шифрования "+K);
            SDES.addStringinList("Результат расширения и перестановки "+A);
            SDES.addStringinList("Результат взаимного исключения с Ключом "+B);
            SDES.addStringinList("Левая половина прошлого шага "+A1);
            SDES.addStringinList("Правая половина прошлого шага "+A2);
            SDES.addStringinList("Левая половина после преобразования "+C1);
            SDES.addStringinList("Правая половина после преобразования "+C2);
            SDES.addStringinList("Результат конкатенации двух половин "+C);
            SDES.addStringinList("Результат конечной перестановки "+Y);
            SDES.addStringinList("<-----------------------------> ");
        }
        return result;
    }

    public static String EandP(String R0){
        int[] E;
        E=SDES.EandP;
        String ER=new String();
        for (int i=0;i<8;i++){
            ER=ER.concat(String.valueOf(R0.charAt(E[i]-1)));
        }
        ER=extentiontoByte(ER);
        return ER;
    }

    public static int[] EandP(int[] R0){
        int[] E;
        E=SDES.EandP;
        int[] ER=new int[E.length];
        for (int i=0;i<8;i++){
            ER[i]=R0[E[i]-1];
        }
        return ER;
    }
    
    /*
    public static int[] S1(int[] L1){
        int[] S;
        S=SDES.S1;
        int[] result=new int[S.length];
        result=extentiontoThreeBits(toBinar(E[new BigInteger(L1,2).intValue()]));
        return result;
    }
    */

    public static String S2(String R1){

        int[] E;

        E=SDES.S2;
        String result=new String();

        int[] D={
                0,3,1,2
        };

        String DOP=new String();
        for(int i=0;i<4;i++){
            DOP=DOP.concat(String.valueOf(R1.charAt(D[i])));
        }
        int  R1int= new BigInteger(DOP,2).intValue();
        result=toBinar(E[R1int]);
        result= extentiontoTwoBits(result);
        return result;
    }

    public static String S1(String L1){

        int[] E;

        E=SDES.S1;
        String result=new String();

        int[] D={
                0,3,1,2
        };

        String DOP=new String();
        for(int i=0;i<4;i++){
            DOP=DOP.concat(String.valueOf(L1.charAt(D[i])));
        }
        int  R1int= new BigInteger(DOP,2).intValue();
        result=toBinar(E[R1int]);
        result= extentiontoTwoBits(result);
        return result;
    }

    public static String P4(String str){
        int[] E;
        E=SDES.P;
        String result=new String();
        for(int i=0;i<4;i++){
            result=result.concat(String.valueOf(str.charAt(E[i]-1)));
        }

        return result;
    }

    public static int[] P4(int[] C){
        int[] P;
        P=SDES.P;
        int[] result=new int[P.length];
        for(int i=0;i<4;i++){
            result[i]=C[P[i]-1];
        }
        return result;
    }

    public static int[] invP4(int[] C){
        int[] P;
        P=SDES.P;
        int[] result=new int[P.length];
        for(int i=0;i<4;i++){
            result[P[i]-1]=C[i];
        }
        return result;
    }

    public static String invP(String D){
        String dD=D;
        StringBuilder sb=new StringBuilder(FeistelFunc.extentiontoFourBits(FeistelFunc.toBinar(0)));
        for(int j=0;j<SDES.P.length;j++){
            sb.deleteCharAt(SDES.P[j]-1);
            sb.insert(SDES.P[j]-1,dD.charAt(j));
        }
        String dC=sb.toString();
        return dC;
    }

    public static String[] invS1(String C){
        int[] S1=SDES.S1;
        int[] D={
                0,2,3,1
        };
        String[] B;
        int[] Bint;
        int counter=0;
        int Cint=toInt(C);
        for(int j=0;j<S1.length;j++){
            if(Cint==S1[j]){
                counter++;
            }
        }
        Bint=new int[counter];
        B=new String[counter];
        counter=0;
        for(int j=0;j<S1.length;j++){
            if(Cint==S1[j]){
                Bint[counter]=j;
                counter++;
            }
        }
        for(int i=0;i<Bint.length;i++){
            String tmp=extentiontoFourBits(toBinar(Bint[i]));
            String res=new String();
            for(int j=0;j<tmp.length();j++){
                res=res.concat(String.valueOf(tmp.charAt(D[j])));
            }
            B[i]=res;
        }
        return B;
    }

    public static String[] invS2(String C){
        int[] S1=SDES.S2;
        int[] D={
                0,2,3,1
        };
        String[] B;
        int[] Bint;
        int counter=0;
        int Cint=toInt(C);
        for(int j=0;j<S1.length;j++){
            if(Cint==S1[j]){
                counter++;
            }
        }
        Bint=new int[counter];
        B=new String[counter];
        counter=0;
        for(int j=0;j<S1.length;j++){
            if(Cint==S1[j]){
                Bint[counter]=j;
                counter++;
            }
        }
        for(int i=0;i<Bint.length;i++){
            String tmp=extentiontoFourBits(toBinar(Bint[i]));
            String res=new String();
            for(int j=0;j<tmp.length();j++){
                res=res.concat(String.valueOf(tmp.charAt(D[j])));
            }
            B[i]=res;
        }
        return B;
    }

    public static String xor(String x,String y){
        int  X= new BigInteger(x,2).intValue();
        int  Y= new BigInteger(y,2).intValue();
        int result=X^Y;
        return toBinar(result);
    }

    public static int xor(int x,int y){
        return x^y;
    }

    public static int  swapString(int str){
        int a=str/16;
        int b=str%16;
        int result=b*16+a;
        return result;
    }

    public static String swapString(String str){
        String a=str.substring(0,4);
        String b=str.substring(4,8);
        String result =b.concat(a);
        return result;
    }

    public static String extentiontoByte(String result){

        while(result.length()<8){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoTwoBytes(String result){

        while(result.length()<16){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoThreeBits(String result){

        while(result.length()<3){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoFourBits(String result){

        while(result.length()<4){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoTwoBits(String result){

        while(result.length()<2){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoTenBits(String result){

        while(result.length()<10){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoTwelveBits(String result){

        while(result.length()<12){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoNBit(String result, int N){

        while(result.length()<N){
            result="0".concat(result);
        }
        return result;
    }

    public static int[] transfertoarray(String input){
        String[] sr= input.split(" ");
        int[] E=new int[sr.length];
        for(int i=0;i<sr.length;i++){
            E[i]= new BigInteger(sr[i]).intValue();
        }
        return E;
    }

    public static String toBinar(int number){
        return Integer.toBinaryString(number);
    }

    /**
     *
     * @param str binarystring
     * @return integer number
     */
    public static int toInt(String str){
        int res=new BigInteger(str,2).intValue();
        return res;
    }
}
