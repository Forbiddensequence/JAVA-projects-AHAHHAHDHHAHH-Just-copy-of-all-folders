import java.io.*;
import java.math.BigInteger;


public class AES {
    //static String path="D:/Java projects/DES/src/";//Оам опять путь менять когда будут беды с расположением
    public static void main(String[]   args) throws IOException{
    PrintStream console = OutputStreamRedirection("Result.txt");
    OutputStreamRedirection("Result.txt");
    int[] arr=RoundClass.readarray("DATA.txt");
    int[] ard={ 7 , 3 , 0xe, 0xb };
    int[] K={ 3 , 0xf , 0xe , 0xa };

    for(int i=0;i<4;i++){
            ard[i]=arr[i];
            K[i]=arr[i+4];
    }
    ard=RotateMas(ard);
    K=RotateMas(K);
    RoundClass.showarrayinBin(SAES.saes(ard,K));
    //RoundClass.showarrayinHex(SAES.saes(SAES.saes(ard,K),K));

    OutputStreamReturn(console);
    }

    public static PrintStream OutputStreamRedirection(String Filename)throws IOException{
        PrintStream out = new PrintStream(new FileOutputStream(Filename));
        PrintStream res = System.out;
        System.setOut(out);
        return res;
    }

    public static void OutputStreamReturn(PrintStream console)throws IOException{
        System.out.close();
        System.setOut(console);
    }

    public static int[] RotateMas(int[] array){
        int[] result=new int[array.length];
        int square=0;
        for(int i=0;i*i<=array.length;i++){
            square=i;
        }
        int[][] postresult=new int[square][square];
        int[][] preresult=new int[square][square];
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                preresult[i][j]=array[i*square+j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                postresult[j][i]=preresult[i][j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                result[i*square+j]=postresult[i][j];
            }
        }
        return result;
    }

    public static String[] RotateMas(String[] array){
        String[] result=new String[array.length];
        int square=0;
        for(int i=0;i*i<=array.length;i++){
            square=i;
        }
        String[][] postresult=new String[square][square];
        String[][] preresult=new String[square][square];
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                preresult[i][j]=array[i*square+j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                postresult[j][i]=preresult[i][j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                result[i*square+j]=postresult[i][j];
            }
        }
        return result;
    }

}

class SAES {



public static int[] saes(int[] Word, int[] Key)throws IOException{
    int[] result =new int[4];
    int[][] DK=selextKeysfromKey(Key);
    int[] key1=new int[4];
    int[] key2=new int[4];
    int[] key3=new int[4];
    for(int i=0;i<4;i++){
        key1[i]=DK[0][i];
        key2[i]=DK[1][i];
        key3[i]=DK[2][i];
    }
    int[] prres1=new int[4];
    int[] prres2=new int[4];
    int[] prres3=new int[4];
    int[] prres4=new int[4];
    int[] prres5=new int[4];
    int[] prres6=new int[4];
    int[] prres7=new int[4];
    int[] prres8=new int[4];
    System.out.println("Вывод массива перед началом работы");
    RoundClass.showarrayinHex(Word);

    System.out.println("Вывод ключа 1");
    RoundClass.showarrayinHex(key1);

    prres1=RoundClass.AddRoundKey(Word,key1);
    System.out.println("Вывод массива после добавления первого ключа");
    RoundClass.showarrayinHex(prres1);

    prres2=RoundClass.SubNibbles(prres1);
    System.out.println("Вывод массива после 1 перестановки");
    RoundClass.showarrayinHex(prres2);
    prres3=RoundClass.ShiftRows(prres2);
    System.out.println("Вывод массива после 1 смещения строк");
    RoundClass.showarrayinHex(prres3);
    prres4=RoundClass.MixColumns(prres3);
    System.out.println("Вывод массива после перемешивания");
    RoundClass.showarrayinHex(prres4);
    prres5=RoundClass.AddRoundKey(prres4,key2);

    System.out.println("Вывод ключа 2");
    RoundClass.showarrayinHex(key2);

    System.out.println("Вывод массива после добавления второго ключа");
    RoundClass.showarrayinHex(prres5);
    prres6=RoundClass.SubNibbles(prres5);
    System.out.println("Вывод массива после 2 перестановки");
    RoundClass.showarrayinHex(prres6);
    prres7=RoundClass.ShiftRows(prres6);
    System.out.println("Вывод массива после 2 смещения");
    RoundClass.showarrayinHex(prres7);

    System.out.println("Вывод ключа 3");
    RoundClass.showarrayinHex(key3);

    prres8=RoundClass.AddRoundKey(prres7,key3);
    System.out.println("Вывод массива после добавления третьего ключа");
    RoundClass.showarrayinHex(prres8);
    result=prres8;
    System.out.println("Вывод массива после конца работы");
    RoundClass.showarrayinHex(result);
    return result;
    }

public static int[] saesinv(int[] Word, int[] Key)throws IOException{
    int[] result =new int[4];
    int[][] DK=selextKeysfromKey(Key);
    int[] key1=new int[4];
    int[] key2=new int[4];
    int[] key3=new int[4];
    for(int i=0;i<4;i++){
        key1[i]=DK[0][i];
        key2[i]=DK[1][i];
        key3[i]=DK[2][i];
    }
    int[] prres1=new int[4];
    int[] prres2=new int[4];
    int[] prres3=new int[4];
    int[] prres4=new int[4];
    int[] prres5=new int[4];
    int[] prres6=new int[4];
    int[] prres7=new int[4];
    int[] prres8=new int[4];

    prres1=RoundClass.AddRoundKey(Word,key3);

    prres2=RoundClass.ShiftRows(prres1);

    prres3=RoundClass.InvSubNibbles(prres2);

    prres4=RoundClass.AddRoundKey(prres3,key2);

    prres5=RoundClass.MixColumns(prres4);

    prres6=RoundClass.ShiftRows(prres5);

    prres7=RoundClass.InvSubNibbles(prres6);

    prres8=RoundClass.AddRoundKey(prres7,key1);

    result=prres8;

    return result;
    }

public static int[][] selextKeysfromKey(int[] Key)throws IOException{
    int[][] result=new int[3][4];

    int[] M = RoundClass.readarray("SN.txt");

    for(int i=0;i<Key.length;i++){
        result[0][i]=Key[i];
    }

    for(int i=1;i<=2;i++){
        result[i][0]=RoundClass.xor(M[result[i-1][3]],result[i-1][0]);
        result[i][2]=RoundClass.xor(RoundClass.xor(M[result[i-1][1]],result[i-1][2]),RoundClass.powerOfTwo(i-1));
        result[i][1]=RoundClass.xor(result[i][0],result[i-1][1]);
        result[i][3]=RoundClass.xor(result[i][2],result[i-1][3]);
    }

    return result;
    }


public static String extentiontoTenBits(String result){

        while(result.length()<10){
            result="0".concat(result);
        }
        return result;
    }

public static String extentiontFiveBit(String result){

        while(result.length()<5){
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


class RoundClass{
    static String path="D:/Java projects/AES/src/";//если беды с путем к файлу то нужно будет поменять

    public static int[] SubNibbles(int[] data) throws IOException{

        int[] SN = RoundClass.readarray("SN.txt");
        int[] result=new int[data.length];
        for(int i=0;i<data.length;i++){
            result[i]=SN[data[i]];
        }
        return result;
    }

    public static int[] InvSubNibbles(int[] data) throws IOException{

        int[] SN = RoundClass.readarray("ISN.txt");
        int[] result=new int[4];
        for(int i=0;i<4;i++){
            result[i]=SN[data[i]];
        }
        return result;
    }

    public static int[] ShiftRows(int[] data){
        int[] result=new int[4];
        for(int i=0;i<4;i++){
            result[i]=data[i];
        }
        result[2]=data[3];
        result[3]=data[2];

        return result;
    }

    public static int[] InvShiftRows(int[] data){
        return ShiftRows(data);
    }


    public static int[] MixColumns(int[] input) throws IOException{
        int[] C;
        C= RoundClass.readarray("C.txt");
        String Poly=new String();
        Poly=read("Polynomial.txt");
        int[] result=new int[4];
        result[0]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[0])),polymultipl(toBinar(C[1]),toBinar(input[2]))),Poly),2).intValue();
        result[1]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[1])),polymultipl(toBinar(C[1]),toBinar(input[3]))),Poly),2).intValue();
        result[2]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[0])),polymultipl(toBinar(C[3]),toBinar(input[2]))),Poly),2).intValue();
        result[3]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[1])),polymultipl(toBinar(C[3]),toBinar(input[3]))),Poly),2).intValue();
        return result;
    }

    public static int[] InvMixColumns(int[] input) throws IOException{
        int[] C;

        C= RoundClass.readarray("Cinv.txt");
        String Poly=new String();
        Poly=read("Polynomial.txt");
        int[] result=new int[4];
        result[0]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[0])),polymultipl(toBinar(C[1]),toBinar(input[2]))),Poly),2).intValue();
        result[1]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[1])),polymultipl(toBinar(C[1]),toBinar(input[3]))),Poly),2).intValue();
        result[2]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[0])),polymultipl(toBinar(C[3]),toBinar(input[2]))),Poly),2).intValue();
        result[3]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[1])),polymultipl(toBinar(C[3]),toBinar(input[3]))),Poly),2).intValue();
        return result;
    }


    /**
     * Реализация функции примешивания раундового ключа - сложение по модулю два
     * @param data массив входных данных в целых числах 4 шт по полубайту каждый
     * @param key массив ключа 2 числа по байту
     * @return результат добавления в данные ключа
     */
    public static int[] AddRoundKey(int[] data, int[] key){
        int[] result=new int[4];
        for(int i=0;i<result.length;i++){
            result[i]=key[i];
        }
        for(int i=0;i<result.length;i++){
            result[i]=xor(data[i],result[i]);
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

    public static String xor(String x,String y){
        int  X= new BigInteger(x,2).intValue();
        int  Y= new BigInteger(y,2).intValue();
        int result=X^Y;
        return Integer.toBinaryString(result);
    }

    public static int xor(int x,int y){
        return x^y;
    }

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

    public static String read(String Filename) throws IOException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path.concat(Filename)));
            String result = reader.readLine();
            //System.out.println(result);
            reader.close();
            return result;

        }
        catch (IOException e){
            return "Все плохо";
        }

    }

    public static int[] readarray(String Filename)throws IOException{
        String input = read(Filename);
        int[] E=transfertoarray(input);
        return E;
    }

    public static int[] transfertoarray(String input)throws IOException{
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

    public static String toBinar(int number){
        return Integer.toBinaryString(number);
    }

    public static String toHex(int number){
        return Integer.toHexString(number);
    }

    public static int FromHextoDecimal(String number){
        int result = new BigInteger(number,16).intValue();
        return result;
    }

    public String FromHextoDecimalS(String number){
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
}