package OperationsWithNumbers;

import javax.crypto.spec.OAEPParameterSpec;

public class Controlwork2 {

    public static void task1(String oper, int modulus, int[] H){
        System.out.println("Задание 1");
        Group G1=new Group(modulus,oper);
        if(oper.equals("+")||oper.equals("*")){
            System.out.println("Полугруппа да , потому что ассоциативность у бинарной операции есть");
        }

        if(oper.equals("+")||oper.equals("*")){
            Integer falseelement= Integer.valueOf(0);
            boolean isGroup=true;
            for (int i=0;i<H.length;i++){
                if(oper.equals("*")){
                    if(OperationsWithNumbers.GrandCommmonDevisor(H[i],modulus)!=1){
                        isGroup&=false;
                        falseelement=H[i];
                    }
                    else{
                        Integer inv=G1.inverse(H[i]);
                        for (int j=0;j<H.length;j++){
                            if(inv.equals(Integer.valueOf(H[j]))){
                                isGroup^=true;
                            }
                        }
                        isGroup^=true;
                        if(isGroup){
                            falseelement=H[i];
                        }
                    }
                }
                if(oper.equals("+")){
                    Integer inv=G1.inverse(H[i]);
                    for (int j=0;j<H.length;j++){
                        if(inv.equals(Integer.valueOf(H[j]))){
                            isGroup^=true;
                        }
                    }
                    isGroup^=true;
                    if(isGroup){
                        falseelement=H[i];
                    }
                }
            }
            if(isGroup){
                System.out.println("Группа да , потому что всех элементов есть обратный");
            }
            else{
                System.out.println("Группа нет , потому что у элемента "+falseelement+" нет братного");
            }
            boolean isZero=false;
            int zeroelement=0;
            for (int i=0;i<H.length;i++){
                if(oper.equals("+")&&(H[i]==0)){
                    isZero|=true;
                    zeroelement=H[i];
                }
                if(oper.equals("*")&&(H[i]==1)){
                    isZero|=true;
                    zeroelement=H[i];
                }
            }
            if(isZero){
                System.out.println("Моноид да, потому что есть нейтральный элемент "+zeroelement);
            }
            else{
                System.out.println("Моноид нет, потому что нет нейтрального элемента");
            }

            boolean isCage=true;
            int noncage=0;
            for (int i=0;i<H.length;i++){
                Integer promt=Integer.valueOf(H[i]);
                if(!promt.equals(G1.operationfunc(promt,promt))){
                    isCage&=false;
                    noncage=H[i];
                }
            }

            if(isCage){
                System.out.println("Полурешетка да, потому что для всех выполняется a"+oper+"a=a");
            }
            else{
                System.out.println("Полурешетка нет, потому что не для всех выполняется a"+oper+"a=a, пример a="+noncage);
            }

        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void task2(String oper, int modulus, int[] H){
        System.out.println("Задание 2");
        Group G1=new Group(modulus,oper);
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


}
