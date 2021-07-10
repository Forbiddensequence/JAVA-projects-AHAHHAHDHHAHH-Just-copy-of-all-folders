package RSAcipher;


import java.math.BigInteger;

public class keyobject {
    public BigInteger field1;
    public BigInteger field2;
    public keyobject(BigInteger n1, BigInteger n2){
        field1=n1;
        field2=n2;
    }

    /**
     * Показывает элементы ключа в формате строки в десятичном виде
     * @return строка из жлементов ключа
     */
     public String show_elements_in_key(){
        String s=new String(field1+" "+field2);
        return s;
    }

    public BigInteger getField1() {
        return field1;
    }

    public BigInteger getField2() {
        return field2;
    }


}