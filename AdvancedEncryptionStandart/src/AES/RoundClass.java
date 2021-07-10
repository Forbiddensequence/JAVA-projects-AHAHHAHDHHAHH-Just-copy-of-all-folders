package AES;

import java.math.BigInteger;

public class RoundClass{

    public static int[][] SubNibbles(int[][] data)  {

        int[] SN = AES.Sbox;
        int[][] result=new int[data.length][data.length];
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                result[i][j]=SN[data[i][j]];
            }
        }
        return result;
    }

    public static int[][] InvSubNibbles(int[][] data) {

        int[] ISN = AES.ISbox;
        int[][] result=new int[data.length][data.length];
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                result[i][j]=ISN[data[i][j]];
            }
        }
        return result;
    }


    public static int[][] ShiftRows(int[][] data){
        int[][] result=new int[data.length][data.length];
        for(int i=0;i<data.length;i++){
            result[i]=ArrayShift(data[i], i);
        }
        return result;
    }


    public static int[][] InvShiftRows(int[][] data){
        int[][] result=new int[data.length][data.length];
        for(int i=0;i<data.length;i++){
            result[i]=ArrayShift(data[i], data.length-i);
        }
        return result;
    }


    public static int[][] MixColumns(int[][] data) {
        int[][] C = AES.C;
        int[][] result = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int tempdata=0;
                for (int k = 0; k < data.length; k++) {
                    tempdata = tempdata^Gmul(C[k][j],data[i][k]);
                }
                result[i][j] =tempdata;
            }
        }
        return result;
    }

    public static int[][] InvMixColumns(int[][] data) {
        int[][] Cinv = AES.Cinv;
        int[][] result = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int tempdata=0;
                for (int k = 0; k < data.length; k++) {
                    tempdata = tempdata^Gmul(Cinv[k][j],data[i][k]);
                }
                result[i][j] =tempdata;
            }
        }
        return result;
    }

    /**
     * Реализация функции примешивания раундового ключа - сложение по модулю два
     * @param data матрица входных данных в целых числах 16 шт по байту каждый
     * @param key матрица ключа 16 чисел по байту
     * @return результат добавления в данные ключа
     */

    public static int[][] AddRoundKey(int[][] data, int[][] key){
        int[][] result=new int[data.length][data.length];
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[0].length;j++){
                result[i][j]=xor(data[i][j],key[i][j]);
            }
        }
        return result;
    }


    public static String mod(String devisible,String devisor){
        String result=new String(devisible);
        while(result.length()>4) {
            String dopl=new String(devisor);
            while(dopl.length()<result.length()){
                dopl=dopl.concat("0");
            }
            result=xor(result,dopl);

        }
        return result;
    }

    public static String polymultipl(String x,String y){
    String result=new String("0");
    String dopl=new String(y);
    for(int i=0;i<x.length();i++){
        if(String.valueOf(x.charAt(x.length()-i-1)).equals("1")){
            result=xor(result,dopl);
        }
        dopl=dopl.concat("0");
    }
    return result;
}

    public static int Gmul(int x,int y){
        int result=0;
        int b=y;
        for(int i=0;i<8;i++){
            if((b&1)!=0){
                result^=x;
            }
            boolean bithit=(x&0x80)!=0;//Граничное условие
            x<<=1;
            if(bithit){
                x^=AES.moduly;//неприводимый полином x^8 + x^4 + x^3 + x + 1
            }
            b>>=1;

        }
        return result;
    }

    public static String xor(String x,String y){
        int  X= new BigInteger(x,2).intValue();
        int  Y= new BigInteger(y,2).intValue();
        int result=X^Y;
        return Integer.toBinaryString(result);
    }

    public static int xor(int x,int y){
        return x^y;
    }


/*
    public static String extentiontoByte(String result){

        while(result.length()<8){
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

    public static String extentiontoFiveBits(String result){

        while(result.length()<5){
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

    public static void showarrayinDec(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
            if(i==(arr.length/2-1)){
                System.out.print("\n");
            }
        }
        System.out.println(" ");
    }

    public static void showarrayinBin(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(extentiontoFourBits(toBinar(arr[i]))+" ");
            if(i==(arr.length/2-1)){
                System.out.print("\n");
            }
        }
        System.out.println(" ");
    }

    public static void showarrayinHex(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(toHex(arr[i])+" ");
            if(i==(arr.length/2-1)){
                System.out.print("\n");
            }
        }
        System.out.println(" ");
    }

*/

    public static String toBinar(int number){
        return Integer.toBinaryString(number);
    }

/*
    public static String toHex(int number){
        return Integer.toHexString(number);
    }

    public static int FromHextoDecimal(String number){
        int result = new BigInteger(number,16).intValue();
        return result;
    }

    public static String FromHextoDecimalS(String number){
        String result =new String(Integer.toString(new BigInteger(number,16).intValue()));
        return result;
    }

    public static String FromHextoBinary(String number){
        int result = new BigInteger(number,16).intValue();
        String rt=new String(Integer.toBinaryString(result));
        rt=extentiontoFourBits(rt);
        return rt;
    }

    public static int powerOfTwo(int number){
        int result =1;
        for (int i=0;i<number;i++){
            result*=2;
        }
        return result;
    }

 */
    public static String toHex(int number){
    String result=Integer.toHexString(number);
    while(result.length()<2){
        result="0".concat(result);
    }
    return result;
}

    public static int[] ArrayShift(int[] array,int n){
        int[] result=new int[array.length];
        for(int i=0;i<array.length;i++){
            int shiftnow=(i+array.length-n)%array.length;
            result[shiftnow]=array[i];
        }
        return result;
    }
}
