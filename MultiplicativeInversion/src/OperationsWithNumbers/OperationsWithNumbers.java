package OperationsWithNumbers;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class OperationsWithNumbers {


    public static int GrandCommmonDevisor(int x, int y){
        while(y!=0){
            int temp=x;
            x=y;
            y=temp%y;
        }
        return x;
    }

    public static BigInteger GrandCommmonDevisor(BigInteger x,BigInteger y){
        while((x.multiply(y)).compareTo(BigInteger.valueOf(0))==1){
            BigInteger temp=x;
            x=y;
            y=temp.remainder(y);
        }
        return x;
    }

    public static int MultiInvers(int x,int mod){
        if(GrandCommmonDevisor(x,mod)!=1){
            System.out.println("Error ты загрузил плохие данные");
            return 0;
        }
        if(x==1){
            return 1;
        }
        int matrixsize=2;
        int y=mod;
        int z=x;
        while(x>0){
            int temp=mod;
            mod=x;
            x=temp%x;
            matrixsize++;
        }
        int[] r=new int[matrixsize];
        int[] q=new int[matrixsize];
        int[] a=new int[matrixsize];
        r[0]=y;
        r[1]=z;
        a[0]=0;
        a[1]=1;
        q[0]=-1;
        q[1]=-1;
        int condit=0;
        int result=0;
        for(int i=2;condit!=(1);i++){
            q[i]=r[i-2]/r[i-1];
            a[i]=(a[i-2]-q[i]*a[i-1])%y;
            r[i]=r[i-2]%r[i-1];
            condit=r[i];
            result=a[i];
        }
        result=(result+y)%y;
        //System.out.println("r[i] q[i] a[i]");
        /*

        for(int i=0;i<matrixsize;i++){
            System.out.println(r[i]+" "+q[i]+" "+a[i]);
        }*/

        return result;
    }

    public static BigInteger MultiInvers(BigInteger x, BigInteger mod){
        if(!GrandCommmonDevisor(x,mod).equals(BigInteger.valueOf(1))){
            return BigInteger.valueOf(0);
        }

        int matrixsize=2;
        BigInteger y=mod;
        BigInteger z=x;
        while(!x.equals(BigInteger.valueOf(1))){
            BigInteger temp=mod;
            mod=x;
            x=temp.remainder(x);
            matrixsize++;
        }
        //System.out.println(matrixsize);
        BigInteger[] r=new BigInteger[matrixsize];
        BigInteger[] q=new BigInteger[matrixsize];
        BigInteger[] a=new BigInteger[matrixsize];
        r[0]=y;
        r[1]=z;
        a[0]=BigInteger.valueOf(0);
        a[1]=BigInteger.valueOf(1);
        q[0]=BigInteger.valueOf(-1);
        q[1]=BigInteger.valueOf(-1);
        BigInteger result= BigInteger.valueOf(0);
        BigInteger cond= BigInteger.valueOf(0);
        for(int i=2;cond.compareTo(BigInteger.valueOf(1))!=0;i++){
            q[i]=r[i-2].divide(r[i-1]);
            a[i]=a[i-2].subtract(q[i].multiply(a[i-1])).remainder(y);
            r[i]=r[i-2].remainder(r[i-1]);
            cond=r[i];
            result=a[i];
        }
        /*
        System.out.println("r[i] q[i] a[i])");
        for(int i=0;i<matrixsize;i++){
            System.out.println(r[i]+" "+q[i]+" "+a[i]);
        }

         */
        return result;
    }

    public static BigInteger ModularExponentiation(BigInteger x, BigInteger y, BigInteger n){
        int bitlenght=y.bitLength();
        //System.out.println(bitlenght);
        BigInteger[] bitsDecomposition=new BigInteger[bitlenght];
        String binRepresentation =y.toString(2);
        BigInteger result;
        if(String.valueOf(binRepresentation.charAt(bitlenght-1)).equals("1")){
            result=x;
        }
        else{
            result=new BigInteger("1");
        }
        bitsDecomposition[0]=x;
        //System.out.println(binRepresentation.length());
        for (int i=1;i<bitlenght;i++){
            bitsDecomposition[i]=bitsDecomposition[i-1].multiply(bitsDecomposition[i-1]).mod(n);
            if(String.valueOf(binRepresentation.charAt(bitlenght-1-i)).equals("1")){
                result=result.multiply(bitsDecomposition[i]).mod(n);
            }
        }

        return result;

    }

    public static int ModularExponentiation(int x,int y, int n){
        if (x>n){
            x=x%n;
        }
        BigInteger X=new BigInteger(Integer.toString(x));
        BigInteger Y=new BigInteger(Integer.toString(y));
        BigInteger N=new BigInteger(Integer.toString(n));
        int result=Integer.parseInt(ModularExponentiation(X,Y,N).toString());
        return result;
    }

    public static BigInteger totient(BigInteger n){
        BigInteger result=new BigInteger("0");
        BigInteger step=new BigInteger("1");
        BigInteger one=new BigInteger("1");
        for(BigInteger i= one;i.compareTo(n)==-1;i=i.add(step)){
            if(GrandCommmonDevisor(n,i).compareTo(one)==0){
                result=result.add(one);
            }
        }
        return result;
    }

    public static int totient(int n){
        BigInteger N=new BigInteger(String.valueOf(n));
        return Integer.parseInt(totient(N).toString());
    }

    public static int CRTfindInteger(int[] R,int[] M){
        int N=1;
        int result=0;
        for(int i=0;i<M.length;i++){
            N*=M[i];
        }
        int[] P=new int[M.length];
        for(int i=0;i<M.length;i++){
            P[i]=(N/M[i]*(MultiInvers(N/M[i],M[i])))%N;
            result+=P[i]*R[i];
            result%=N;
        }
        return result;
    }

    public static int[] CRTfindRemainders(int X,int[] M){
        int[] result=new int[M.length];
        for(int i=0;i<M.length;i++){
            result[i]=X%M[i];
        }
        return result;
    }

    public static boolean MillerRabinPrimality(BigInteger N){
        int rounds=1000;
        BigInteger minusone=new BigInteger("-1");
        BigInteger zero=new BigInteger("0");
        BigInteger one=new BigInteger("1");
        BigInteger two=new BigInteger("2");
        for(int i=0;i<rounds;i++){
            int Maxval=Integer.MAX_VALUE;
            if(N.compareTo(new BigInteger(String.valueOf(Maxval)))==-1){
                Maxval=N.intValue();
            }
            BigInteger a=new BigInteger(String.valueOf(ThreadLocalRandom.current().nextInt(2,Maxval)));
            BigInteger s=new BigInteger("0");
            BigInteger d=N.subtract(one);
            while(d.remainder(two).compareTo(zero)==0){
                d=d.divide(two);
                s=s.add(one);
            }
            /*if(GrandCommmonDevisor(a,N).compareTo(one)!=0){
                continue;
            }

             */
            BigInteger res=ModularExponentiation(a,d,N);
            if((res.compareTo(one)==0)||(res.compareTo(minusone.add(N))==0)){
                continue;
            }
            for(BigInteger j=zero;j.compareTo(s.subtract(one))==(-1);j=j.add(one)){
                res=ModularExponentiation(res,two,N);
                if((res.compareTo(minusone.add(N))==0)){
                   break;
                }
                if((res.compareTo(one)==0)){
                    System.out.println("Произошла ошибочка на a = "+a.intValue());
                    return false;

                }
            }

            if((res.compareTo((minusone.add(N)))==0)){
                continue;

            }
            if((res.compareTo(one)!=0)){
                System.out.println("Произошла ошибочка на a = "+a.intValue());
                return false;

            }
        }

        return true;

    }

    public static BigInteger generator(int Bitlength){
        BigInteger res;
        BigInteger mainPart;
        BigInteger resPart;
        String strinRepresentation=new String("");
        while(strinRepresentation.length()<(Bitlength-32)){
            int randnum=ThreadLocalRandom.current().nextInt(2,Integer.MAX_VALUE);
            strinRepresentation=strinRepresentation.concat(Integer.toBinaryString(randnum));
        }
        strinRepresentation=strinRepresentation.substring(0,(Bitlength-32));
        String promt=new String("");
        while (strinRepresentation.length()<Bitlength){
            strinRepresentation=strinRepresentation.concat("0");
            promt=promt.concat("0");
        }
        mainPart=new BigInteger(strinRepresentation,2);
        resPart=new BigInteger(promt,2);

        for(int i=0;i<Bitlength;i++){
            res=mainPart.add(resPart);
            if(MillerRabinPrimality(res)){
                return res;
            }
            resPart=resPart.add(new BigInteger("1"));
        }
        return new BigInteger("1");
    }
}
