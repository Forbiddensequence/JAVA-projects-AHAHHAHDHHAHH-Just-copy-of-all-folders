package OperationsWithNumbers;

import javax.swing.*;
import java.util.ArrayList;

public class Group {
    public Integer modulus;
    public ArrayList<Integer> elements=new ArrayList<Integer>();
    public String operation=new String();
    public String[] PerNames=new String[6];
    public Integer size;
    public Integer zeroelement=null;

    public Group(int m,String op){
        this.modulus =Integer.valueOf(m);
        this.operation=op;
        this.zeroelement=zeroinit();
        this.elements.add(Integer.valueOf(zeroelement));
        fillingGroup();
        this.size=elements.size();
    }

    public Integer operationfunc(Integer a, Integer b){
        Integer result=null;
        if(operation.equals("permutation")){
            int[] x={1,2,3};
            int[] y=new int[3];
            int[] z=new int[3];
            int[] w=new int[3];
            int[] e=new int[3];
            y[0]=a%1000/100;
            y[1]=a%100/10;
            y[2]=a%10;
            z[0]=x[y[0]-1];
            z[1]=x[y[1]-1];
            z[2]=x[y[2]-1];
            w[0]=b%1000/100;
            w[1]=b%100/10;
            w[2]=b%10;
            e[0]=z[w[0]-1];
            e[1]=z[w[1]-1];
            e[2]=z[w[2]-1];
            result=e[0]*100+e[1]*10+e[2];
        }

        if(operation.equals("+")){
            result=(a+b)% modulus;
        }
        if(operation.equals("*")){
            result=(a*b)% modulus;
        }
        return result;
    }

    public Integer zeroinit(){
        Integer result=null;
        if(operation.equals("permutation")){
            result=Integer.valueOf(123);
        }
        if(operation.equals("+")){
            result=Integer.valueOf(0);
        }
        if(operation.equals("*")){
            result=Integer.valueOf(1);
        }
        return result;
    }

    public Integer inverse(Integer a){
        Integer result=null;
        if(operation.equals("permutation")){
            result=Integer.parseInt("-1");
            for(int i=0;i<elements.size();i++){
                Integer promt=operationfunc(a,elements.get(i));
                if(promt.equals(zeroelement)){
                    result=elements.get(i);
                }
            }
            // ОБратный элемент для перстановки это элемент по столбцу, с помощью которого и элемента а получается 0 элемент то есть 123
        }
        if(operation.equals("+")){
            result=(modulus-a)% modulus;
        }
        if(operation.equals("*")){
            result=OperationsWithNumbers.MultiInvers(a.intValue(), modulus.intValue());
        }
        return result;
    }

    public int gcd(int a,int b){
        return OperationsWithNumbers.GrandCommmonDevisor(a,b);
    }

    public void fillingGroup(){
        if(!operation.equals("permutation")){
            for(int i=zeroelement.intValue()+1;i<modulus.intValue();i++){
                elements.add(Integer.valueOf(i));
                if(operation.equals("*")){
                    if(gcd(elements.get(elements.size()-1).intValue(),modulus.intValue())!=1){
                        elements.remove(elements.size()-1);
                    }
                }
            }
        }
        else {
            elements.add(Integer.valueOf(132));
            elements.add(Integer.valueOf(213));
            elements.add(Integer.valueOf(231));
            elements.add(Integer.valueOf(312));
            elements.add(Integer.valueOf(321));
        }
    }

    public void showGroup(){
        System.out.println("Все элементы группы");
        System.out.print("( ");
        for(int i=0;i<elements.size();i++){
            System.out.print(elements.get(i)+" ");
        }
        System.out.println(" )");
    }

    public void showPermutationGroupWithNames(){
        System.out.println("Все элементы группы с соответствующими именами");
        System.out.print("( ");
        for(int i=0;i<elements.size();i++){
            System.out.print(PerNames[i]+" "+elements.get(i)+" ");
        }
        System.out.println(" )");
    }

    public void showKellyTablePermutation(){
        System.out.println("---------------------------");
        System.out.println("Таблица Келли");
        System.out.print("\t");
        for(int i=0;i<elements.size();i++){
            System.out.print(PerNames[i]+" ");
        }
        System.out.println();
        System.out.println("______________________");
        for(int i=0;i<elements.size();i++){
            System.out.print(PerNames[i]+" |");
            for(int j=0;j<elements.size();j++){
                Integer a=elements.get(i);
                Integer b=elements.get(j);
                Integer c=operationfunc(a,b);

                System.out.print(PerNames[getpermutationid(c)]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public int getnameid(String name){
        int result=0;
        for(int i=0;i<elements.size();i++){
            if(name.equals(PerNames[i])){
                result = i;
            }
        }
        return result;
    }

    public int getpermutationid(Integer permut){
        int result=0;
        for(int i=0;i<elements.size();i++){
            if(permut.equals(elements.get(i))){
                result = i;
            }
        }
        return result;
    }

    public void setPermutationNames(String[] setOfNames){
        for(int i=0;i<elements.size();i++){
            this.PerNames[i]=setOfNames[i];
        }
    }

    public void showfactorGroupleftPermutations(String[] H){
        System.out.println("---------------------------");
        System.out.println("Вывод левых смежных классов (с повторами) ");
        for(int i=0;i<elements.size();i++){
            System.out.print(PerNames[i]+"H ");
            for(int j=0;j<H.length;j++){
                Integer a=elements.get(i);
                Integer b=elements.get(getnameid(H[j]));
                Integer c=operationfunc(a,b);
                System.out.print(PerNames[getpermutationid(c)]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public void showfactorGrouprightPermutations(String[] H){
        System.out.println("---------------------------");
        System.out.println("Вывод правых смежных классов (с повторами) ");
        for(int i=0;i<elements.size();i++){
            System.out.print("H"+PerNames[i]+" ");
            for(int j=0;j<H.length;j++){
                Integer a=elements.get(getnameid(H[j]));
                Integer b=elements.get(i);
                Integer c=operationfunc(a,b);
                System.out.print(PerNames[getpermutationid(c)]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public void showfactorGroupleft(int[] H){
        System.out.println("---------------------------");
        System.out.println("Вывод левых смежных классов (с повторами) ");
        for(int i=0;i<elements.size();i++){
            System.out.print(this.elements.get(i)+"H ");
            for(int j=0;j<H.length;j++){
                Integer a=elements.get(i);
                Integer b=Integer.valueOf(H[j]);
                Integer c=operationfunc(a,b);
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public void showfactorGroupright(int[] H){
        System.out.println("---------------------------");
        System.out.println("Вывод правых смежных классов (с повторами) ");
        for(int i=0;i<elements.size();i++){
            System.out.print("H"+PerNames[i]+" ");
            for(int j=0;j<H.length;j++){
                Integer a=Integer.valueOf(H[j]);
                Integer b=elements.get(i);
                Integer c=operationfunc(a,b);
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public void showsubGroupPermutations(String[] H){

        System.out.println("Вывод подгруппы ");
        for(int i=0;i<H.length;i++){
            System.out.print(H[i]+" ");
        }
        System.out.println();
    }

    public void showsubGroup(int[] H){

        System.out.println("Вывод подгруппы  ");
        for(int i=0;i<H.length;i++){
            System.out.print(H[i]+" ");
        }
        System.out.println();
    }

    public int findumberofclasses(String[] H){
        int result=this.elements.size()/H.length;
        return result;
    }

    public int findumberofclasses(int[] H){
        int result=this.elements.size()/H.length;
        return result;
    }

    public void showKellyTable(){
        System.out.println("---------------------------");
        System.out.println("Таблица Келли");
        System.out.print("\t");
        for(int i=0;i<elements.size();i++){
            System.out.print(PerNames[i]+" ");
        }
        System.out.println();
        System.out.println("______________________");
        for(int i=0;i<elements.size();i++){
            System.out.print(PerNames[i]+" |");
            for(int j=0;j<elements.size();j++){
                Integer a=elements.get(i);
                Integer b=elements.get(j);
                Integer c=operationfunc(a,b);

                System.out.print(PerNames[getpermutationid(c)]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}
