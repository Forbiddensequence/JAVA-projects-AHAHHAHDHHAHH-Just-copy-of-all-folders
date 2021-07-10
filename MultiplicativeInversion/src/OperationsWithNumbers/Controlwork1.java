package OperationsWithNumbers;

import java.util.ArrayList;
import java.util.List;

public class Controlwork1 {

    public static void task1(int n,String operation) {
        ArrayList<Integer> elements=new ArrayList<Integer>();
        if(operation.equals("+")){
            elements.add(Integer.valueOf(0));
        }
        if(operation.equals("*")){
            elements.add(Integer.valueOf(1));
        }
        if(elements.size()==0){
            return;
        }
        Integer zeroelement=elements.get(0);
        for(int i=zeroelement.intValue()+1;i<n;i++){
            elements.add(Integer.valueOf(i));
            if(operation.equals("*")){
                if(gcd(elements.get(elements.size()-1).intValue(),n)!=1){
                    elements.remove(elements.size()-1);
                }
            }
        }
        System.out.println("Группа Z<"+n+","+operation+">");
        System.out.print("Все элементы группы: ");
        for(Integer number :elements ){
            System.out.print(number.intValue()+", ");
        }
        System.out.println();
        System.out.print("Вывод всех пар обычных элементов и обратных: ");
        for(Integer number :elements ){
            if(number.equals(zeroelement)){
                System.out.print("( "+number.intValue()+", "+number.intValue()+") ");
                continue;
            }
            if(operation.equals("+")){
                System.out.print("( "+number.intValue()+", "+(n-number.intValue())+") ");
            }
            if(operation.equals("*")){
                System.out.print("( "+number.intValue()+", "+multiinv(number.intValue(),n)+") ");
            }
        }
        System.out.println();


        int power = elements.size();
        ArrayList<Integer> litleGroupnumber=new ArrayList<Integer>();
        for(int i=1;i<=power;i++){
            if(power%i==0){
                litleGroupnumber.add(i);
            }
        }
        System.out.println("Вывод мощности группы : "+ power);
        System.out.print("Вывод всех чисел на которые делится наша мощность (порядок всех подгрупп): ");
        for(Integer number :litleGroupnumber ){
            System.out.print(number.intValue()+", ");
        }
        System.out.println();
        System.out.println("Вывод всех подгрупп каждая с новой строки : " );
        for(Integer number :elements ){
            String result=new String("(");
            Integer temp=zeroelement;
            int numelem=0;
            do{
                result=result.concat(temp.toString()+",");
                if(operation.equals("+")){
                    temp=Integer.valueOf((temp.intValue()+number.intValue())%n);
                }
                if(operation.equals("*")){
                    temp=Integer.valueOf((temp.intValue()*number.intValue())%n);
                }
                numelem++;
            }
            while (!temp.equals(zeroelement));
            result=result.concat(")");
            if(litleGroupnumber.lastIndexOf(Integer.valueOf(numelem))!=(-1)){
                litleGroupnumber.remove(litleGroupnumber.lastIndexOf(Integer.valueOf(numelem)));
                System.out.println(result);
            }
        }
        System.out.println();

    }

    public static void task2(int n,String operation, int[] array) {
        int zeroelement=0;
        if(operation.equals("+")){
            zeroelement=0;
        }
        if(operation.equals("*")){
            zeroelement=1;
        }

        int a1=0,a2=0,b=0;
        boolean isWhole=true;
        for(int i=0;i<array.length;i++){

            for(int j=0;j<array.length;j++){
                boolean isHere=false;
                int ques=0;
                if(operation.equals("+")){
                    ques=(array[i]+array[j])%n;
                }
                if(operation.equals("*")){
                    ques=(array[i]*array[j])%n;
                }
                for(int k=0;k<array.length;k++){
                    if(ques==array[k]){
                        isHere=true;
                        break;
                    }
                }
                if(!isHere){
                    a1=array[i];
                    a2=array[j];
                    b=ques;
                    isWhole=false;
                }
            }
        }
        System.out.println("Группа Z<"+n+","+operation+">");
        for(int num:array){
            System.out.print(num+", ");
        }
        System.out.println();
        System.out.println("Целостность группы: " );
        if(isWhole){
            System.out.println("Группа замкнута" );
        }
        else {
            System.out.println("Группа незамкнута, так как "+a1 +" "+operation+" "+a2+" = "+b);
        }

        boolean isZeroelement=false;
        for(int num:array){
            if(num==zeroelement){
                isZeroelement=true;
                break;
            }
        }

        System.out.println("Наличие нулевого элемента: " );
        if(isZeroelement){
            System.out.println("Он есть" );
        }
        else {
            System.out.println("Его нет");
        }
        int a3=0;
        boolean isEveryhaveinvers=true;
        for(Integer number1 :array ){
            boolean isonehaveinvers=false;
            for(Integer number2 :array){
                int tmp=0;
                if(operation.equals("+")){
                    tmp=(number1+number2)%n;
                }
                if(operation.equals("*")){
                    tmp=(number1*number2)%n;
                }

                if(tmp==zeroelement){
                    isonehaveinvers=true;
                    continue;
                }
            }
            if(isonehaveinvers){
                continue;
            }
            else{
                a3=number1;
                isEveryhaveinvers=false;
            }
        }


        System.out.println("Наличие у каждого элемента обратного : " );
        if(isEveryhaveinvers){
            System.out.println("У всех есть" );
        }
        else {
            System.out.println("Не у всех есть, например у "+a3);
        }
        System.out.println("Является ли группой : " );
        if(isEveryhaveinvers&&isZeroelement&&isWhole){
            System.out.println("Да, является" );
        }
        else {
            System.out.println("Нет, не является" );
        }
        System.out.println();

    }


    public static int gcd(int a,int b){
        return OperationsWithNumbers.GrandCommmonDevisor(a,b);
    }

    public static int multiinv(int a,int b){
        return OperationsWithNumbers.MultiInvers(a,b);
    }


}
