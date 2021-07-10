
import AES.*;

import java.security.Key;

public class WorkclassWithEncryption {
    public static void main(String[] args) {

        String  strInput=new String("Ой а я люблю линукс");
        String keyS=new String("а военку не люблю");
        System.out.println(strInput);
        //System.out.println(strInput.length());
        System.out.println(keyS);
        //System.out.println(keyS.length());
        String enc=Encrypt(strInput,keyS);
        System.out.println(enc);
        //System.out.println(enc.length());
        System.out.println("<------------------------------------------------------------------------------------------->");
        String dec=Decrypt(enc,keyS);
        System.out.println(dec);
        //System.out.println(dec.length());
    }

    public static int[] fromStringtoInts(String strInput) {
        int[] inputdata=new int[2*strInput.length()];
        for(int i=0;i<strInput.length();i++){
            int temp=(int) strInput.charAt(i);
            inputdata[2*i]=temp/256;
            inputdata[2*i+1]=temp%256;
        }
        return inputdata;
    }

    public static String fromIntstoString(int[] InputInts) {
        String result=new String("");
        for(int i=0;i<InputInts.length/2;i++){
            result=result.concat(String.valueOf((char)(InputInts[2*i]*256+InputInts[2*i+1])));
        }
        return result;
    }

    public static int[] arrayCompletion(int[] InputInts){
        int[] resutl;
        int size = InputInts.length;
        while(size%16!=0){
            size++;
        }
        resutl=new int [size];
        for(int i=0;i<InputInts.length;i++){
            resutl[i]=InputInts[i];
        }
        for(int i=InputInts.length;i<resutl.length;i++){
            resutl[i]=0;
        }

        return resutl;
    }

    public static String stringCompletion(String a,String b){
        String result = new String("");
        int up=a.length()/8;
        boolean truth=(a.length()%8|0)!=0;
        while (a.length()>result.length()){
            result=result.concat(b);
        }
        if(truth){
            up++;
        }
        up*=8;
        while(result.length()<up){
            result=result.concat(b);
        }
        result=result.substring(0,up);
        return result;
    }

    public static String Encrypt(String data,String key){
        String result=new String("");
        key=stringCompletion(data,key);
        //System.out.println(key.length());
        int[] dataInts=arrayCompletion(fromStringtoInts(data));
        for(int num:dataInts){
            //System.out.print(num+" ");
        }
        //System.out.println();
        int[] keyInts=arrayCompletion(fromStringtoInts(key));
        for(int num:keyInts){
            //System.out.print(num+" ");
        }
        //System.out.println();
        //System.out.println(dataInts.length);
        //System.out.println(keyInts.length);
        for(int i=0;i<dataInts.length/16;i++){
            int[] tempd=new int[16];
            int[] tempk=new int[16];
            for(int j=0;j<16;j++){
                tempd[j]=dataInts[i*16+j];
                tempk[j]=keyInts[i*16+j];
            }
            String tempS=fromIntstoString(AES.AESencryption(tempd,tempk));
            result=result.concat(tempS);
        }
        return result;
    }

    public static String Decrypt(String data,String key){
        String result=new String("");
        key=stringCompletion(data,key);
        int[] dataInts=arrayCompletion(fromStringtoInts(data));

        int[] keyInts=arrayCompletion(fromStringtoInts(key));

        for(int i=0;i<dataInts.length/16;i++){
            int[] tempd=new int[16];
            int[] tempk=new int[16];
            for(int j=0;j<16;j++){
                tempd[j]=dataInts[i*16+j];
                tempk[j]=keyInts[i*16+j];
            }
            String tempS=fromIntstoString(AES.AESdecryption(tempd,tempk));
            result=result.concat(tempS);
        }
        return result;
    }

}
