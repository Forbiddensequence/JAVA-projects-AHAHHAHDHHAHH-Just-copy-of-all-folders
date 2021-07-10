package OperationsWithNumbers;

public class Controlwork3 {

    public static void task7(int a, int mod){
        System.out.println("Задание 7");
        System.out.println("Изначальное равенство");
        System.out.println( a +" mod "+mod);
        do{
            a+=mod;
        }
        while (a<0);
        a = a % mod;
        int b=a-mod;
        //System.out.println( a );
        //System.out.println( abs(a) );
        //System.out.println( b );
        //System.out.println( abs(b) );
        if(abs(a)>abs(b)){
            System.out.println("Абсолютно наименьший вычет это " + b );
        }
        else{
            System.out.println("Абсолютно наименьший вычет это " + a );
        }

        System.out.println();
        System.out.println();
    }

    public static void task8(int a, int b, int mod){
        System.out.println("Задание 8");
        int x=a;
        int y=b;
        a=((a%mod)+mod)%mod;
        b=((b%mod)+mod)%mod;
        if(a==b){
            System.out.println("Да, сравнение "+x+"\u2261"+y+"mod"+mod+" верно");
            System.out.println("Потому что "+a+"\u2261"+b+"mod"+mod);
            System.out.println();
        }
        else{
            System.out.println("Нет, сравнение "+x+"\u2261"+y+"mod"+mod+" не верно");
            System.out.println("Потому что "+a+"\u2262"+b+"mod"+mod);
            System.out.println();

        }

    }

    public static void task9(int a, int b,int c, int mod){
        System.out.println("Задание 9");
        System.out.println();
        int x=a;
        int y=b;
        int z=c;
        System.out.println("Изначальное уравнение");
        System.out.println(a+"*x + "+b+" = "+c+" mod "+mod);
        c=((c-b)%mod+mod)%mod;
        System.out.println(a+"*x = "+c+" mod "+mod);
        int GCD=OperationsWithNumbers.GrandCommmonDevisor(a,mod);
        if(c%GCD==0){
            if(GCD==1){
                int sol=MI(a,mod);
                System.out.println(a+"^(-1) = "+sol+" mod "+mod);
                System.out.println(a+"^(-1)*"+a+"*x="+a+"^(-1)*"+c+" mod "+mod);

                sol=(sol*c)%mod;
                System.out.println("Решение "+" - "+sol +" mod "+mod);
                System.out.println();
                return;
            }
            int basesol=(c/GCD);
            int basemod=mod/GCD;
            System.out.println(a/GCD+"*x = "+basesol+" mod "+basemod);
            System.out.println(a/GCD+"^(-1) = "+OperationsWithNumbers.MultiInvers(a/GCD,basemod)+" mod "+basemod);
            System.out.println(a/GCD+"^(-1)*"+a/GCD+"*x="+a/GCD+"^(-1)*"+basesol+" mod "+basemod);
            basesol=(basesol*OperationsWithNumbers.MultiInvers(a/GCD,basemod))%basemod;

            System.out.println("Базовое решение "+" - "+basesol +" mod "+basemod);
            for(int i=0;i<GCD;i++){
                int respr=basesol+i*basemod;
                System.out.println("Решение № "+(i+1)+" - "+respr +" mod "+mod);
            }
            System.out.println();
        }
        else{
            System.out.println("Решений нет, потому что число справа не делится нацело на НОД числа слева и модуля");
            System.out.println();
        }

    }

    public static void task6(String oper, int modulus, int[] H){
        System.out.println("Задание 6");
        Group G1=new Ring(modulus,oper);
        G1.showGroup();
        G1.showsubGroup(H);
        G1.showfactorGroupleft(H);
        System.out.println("Количество смежных классов "+G1.findumberofclasses(H));
        String[] FG=new String[G1.findumberofclasses(H)];
        for(int i=0;i<G1.findumberofclasses(H);i++){
            FG[i]=G1.elements.get(i).toString()+"H";
        }
        System.out.println("Фактор группа");
        System.out.print("FG ={ ");
        for(int i=0;i<G1.findumberofclasses(H);i++){
            System.out.print(FG[i]+" ");
        }
        System.out.println("}");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void task3(String oper,int modulus,int[] H){
        System.out.println("Задание 3");
        Group G1=new Group(modulus,oper);
        G1.showGroup();
        G1.showsubGroup(H);
        System.out.println("Количество смежных классов "+G1.findumberofclasses(H));
        System.out.println("И следовательно оно же и индекс группы G по подгруппе H");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void task4(String[] N,String[] H){
        System.out.println("Задание 4");
        String op=new String("permutation");
        int rannum=5;
        Group G1=new Group(rannum,op);
        G1.showGroup();
        G1.showsubGroupPermutations(H);
        G1.setPermutationNames(N);
        G1.showPermutationGroupWithNames();
        G1.showKellyTablePermutation();
        G1.showfactorGroupleftPermutations(H);
        G1.showfactorGrouprightPermutations(H);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static int abs(int a){
        int res=a;
        if(a<0){
            res=-a;
        }
        return res;
    }

    public static int MI(int x,int mod){
        if(OperationsWithNumbers.GrandCommmonDevisor(x,mod)!=1){
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
        /*
        System.out.println("a[0]=0,a[1]=1, q[0]=-1,q[1]=-1, r[0]=mod,r[1]=a");
        System.out.println("q[i]=r[i-2]/r[i-1], a[i]=(a[i-2]-q[i]*a[i-1]) mod m, r[i]=r[i-2] mod r[i-1]");
        System.out.println(" if r[i] == 1 => result=a[i]");
        System.out.print("r[i] ");
        for(int i=0;i<matrixsize-1;i++){
            System.out.print(r[i]+" ");
        }
        System.out.println();
        System.out.print("q[i] ");
        for(int i=0;i<matrixsize-1;i++){
            System.out.print(q[i]+" ");
        }
        System.out.println();
        System.out.print("a[i] ");
        for(int i=0;i<matrixsize-1;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        */
        return result;
    }
}
