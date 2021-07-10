package OperationsWithNumbers;

public class Controlwork4 {

    public static void task1(int[] ar1, int[] ar2){
        System.out.println("Задание 1");
        System.out.println("Изначальное Система уравнений");
        int tmp=1;
        for(int i=0;i<ar1.length;i++){
            System.out.println("x"+i+" \u2261 "+ar1[i]+" mod "+ar2[i]);
            tmp*=ar2[i];
        }

        int res=CRTfindInteger(ar1,ar2);

        System.out.println("X \u2261 "+res+" mod "+tmp);
    }

    public static void task4(int a, int p,int N){
        System.out.println("Задание 4");
        System.out.println("Изначальное Система уравнений");
        System.out.println("x^2 = "+a+" mod "+ p);
        int k=twocounter(p-1);
        int h=oddpartcounter(p-1);
        System.out.println("p = 2^"+k+"*"+h+"+1");
        System.out.println("h = "+h+" k = "+k);
        int a1=OperationsWithNumbers.ModularExponentiation(a,(h+1)/2,p);
        System.out.println("a1="+a+" ^("+h+"+1)/2 mod "+p+" = "+a+" ^("+(h+1)/2+") mod "+p+"="+a1+" mod "+p);
        int a2=OperationsWithNumbers.MultiInvers(a,p);
        System.out.println("a2="+a+" ^(-1) mod "+p+" = "+a2+" mod "+p);
        int N1=OperationsWithNumbers.ModularExponentiation(N,h,p);
        System.out.println("N1="+N+" ^("+h+") mod "+p+"="+N1+" mod "+p);
        int N2=1,j=0;
        System.out.println("N2 = "+N2+" j = "+j);

        for(int i=0;i<k-1;i++){
            int b=a1*N2%p;
            System.out.println("b="+a1+"*"+N2+" mod "+p+"="+b+" mod "+p);
            int C=(a2*(b*b))%p;
            System.out.println("C="+a2+"*"+b+"^2 mod "+p+"="+C+" mod "+p);
            int tmp=poweroftwo(k-2-i);
            System.out.println("2^"+(k-2-i)+"="+tmp);
            int D=OperationsWithNumbers.ModularExponentiation(C,tmp,p);
            System.out.println("D="+C+"^"+tmp+" mod "+p+"="+D+" mod "+p);
            if(D==1){
                j=0;
            }
            else{
                j=1;
            }
            int step=poweroftwo(i)*j;
            int tmnpr=0;
            if(step==0){
                tmnpr=1;
            }
            else{
                if(step==1){
                    tmnpr=N1%p;
                }
                else{
                    tmnpr=OperationsWithNumbers.ModularExponentiation(N1,step,p);
                }
            }
            System.out.println("2^"+(i)+"*"+j+"="+step);
            System.out.println(N1+"^"+step+"="+tmnpr);
            System.out.println("N2="+N2+"*"+N1+"^"+tmnpr+" mod "+p+"="+N2*tmnpr%p+" mod "+p);
            N2=N2*tmnpr%p;
        }

        int result= (a1*N2)%p;
        System.out.println("x=\u00B1"+a1+"*"+N2+" mod "+p+" = \u00B1"+result+" mod "+p);
        System.out.println("x=\u00B1"+result+" mod "+p);

    }

    public static int task3(int a, int p){
        System.out.println("Задание 3");
        System.out.println("Изначальное сравнение ");
        System.out.println("x^2 = "+a+" mod "+ p);
        int a1=1;
        int g=1;
        while(a!=0||a!=1||a1==1){
            System.out.println("g="+g);
            if (a==0){
                System.out.println("result = 0");
                return 0;
            }
            if(a==1){
                System.out.println("result = "+g);
                return g;
            }
            int k=twocounter(a);
            a1=oddpartcounter(a);
            System.out.println("a = 2^"+k+" * "+ a1);
            System.out.println("k = "+k+" a1 = "+ a1);
            int s=1;
            if(k%2==0){
                s=1;
            }
            else {
                if (p % 8 == 1 || p % 8 == 7) {
                    s = 1;
                }
                if (p % 8 == 3 || p % 8 == 5) {
                    s = -1;
                }
            }
            System.out.println("s = "+s);

            if(a1==1){
                System.out.println("result = "+g*s);
                return g*s;
            }
            if(p%4==3&&a1%4==3){
                s*=(-1);
                System.out.println("s = "+s+" Потому что n=3 mod 4 и n=3 mod 4");
            }
            a=p%a1;
            System.out.print("a \u2261 "+p+" mod "+a1+" = "+a +" mod "+a1);
            p=a1;
            System.out.print(" n = "+a1);

            System.out.println(" g = "+g+"*"+s+"="+g*s);
            g=g*s;
        }
        return 3;
    }

    public static int twocounter(int a){
        int k=0;
        while(a%2==0){
            a/=2;
            k++;
        }
        return k;
    }
    public static int oddpartcounter(int a){
        int k=0;
        while(a%2==0){
            a/=2;
            k++;
        }
        return a;
    }
    public static int CRTfindInteger(int[] R,int[] M){
        int N=1;
        int result=0;
        System.out.print("P=");
        for(int i=0;i<M.length;i++){
            N*=M[i];
            if(i!=0){
                System.out.print("*");
            }
            System.out.print(M[i]);
        }
        System.out.println("="+N);

        int[] P=new int[M.length];
        for(int i=0;i<M.length;i++){
            P[i]=(N/M[i]*(OperationsWithNumbers.MultiInvers(N/M[i],M[i])))%N;
            System.out.println("P"+i+" = ("+N+"/"+M[i]+")^(-1)"+" mod "+M[i]+" = ("+N/M[i]+")^(-1)"+" mod "+M[i]+"=("+N/M[i]%M[i]+")^(-1)"+" mod "+M[i]+" = "+OperationsWithNumbers.MultiInvers(N/M[i]%M[i],M[i])+" mod "+M[i]);
            result+=P[i]*R[i];
            result%=N;
        }
        System.out.print("X=");
        for(int i=0;i<M.length;i++){
            if(i!=0){
                System.out.print("+");
            }
            System.out.print(R[i]+"*"+N/M[i]+"*"+OperationsWithNumbers.MultiInvers(N/M[i]%M[i],M[i]));
        }
        System.out.print(" = "+result+" mod "+N);
        System.out.println();
        return result;
    }
    public static int poweroftwo(int a){
        int k=1;
        while(a!=0){
            k*=2;
            a--;
        }
        return k;
    }
    public static int[] CRTfindRemainders(int X,int[] M){
        int[] result=new int[M.length];
        for(int i=0;i<M.length;i++){
            result[i]=X%M[i];
        }
        return result;
    }
}
