package RSAcipher;

import OperationsWithNumbers.OperationsWithNumbers;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Keys {

    keyobject public_key;
    keyobject private_key;

    public Keys(){
        BigInteger zero =new BigInteger("0");
        generate_keys();
        System.out.println("Генерация пустых ключей");
    }

    /**
     * Заполнение открытый ключ public_key (e,n)
     * Заполнение секретный ключ private_key (d,n)
     * Если не получается создать то пытаемся снова с выводом в консоль информации о допольнительной генерации ключей
     * Диапазон верхней границы максимального простого числа меняется в данный момент от 1024 до 8192
     * Диапазон верхней границы максимального простого числа меняется в данный момент от 65536 до 131072
     */
    public void generate_keys(){
        BigInteger D=new BigInteger("0");
        BigInteger E=new BigInteger("0");
        BigInteger P=new BigInteger("0");
        BigInteger Q=new BigInteger("0");
        BigInteger N=new BigInteger("0");
        BigInteger FN=new BigInteger("0");
        BigInteger one=new BigInteger("1");
        while(P.bitLength()<2){
            P=OperationsWithNumbers.generator(ThreadLocalRandom.current().nextInt(24,1000));
        }
        while(Q.bitLength()<2){
            Q=OperationsWithNumbers.generator(2048-P.bitLength());
        }

        N=P.multiply(Q);
        FN=(P.subtract(one)).multiply(Q.subtract(one));

        E=OperationsWithNumbers.generator(ThreadLocalRandom.current().nextInt(24,2047));

        if(OperationsWithNumbers.GrandCommmonDevisor(E,FN).compareTo(one)!=0){
            E=OperationsWithNumbers.generator(ThreadLocalRandom.current().nextInt(24,2047));
        }
        D=OperationsWithNumbers.MultiInvers(E,FN);
        if(D.multiply(E).remainder(FN).compareTo(BigInteger.valueOf(1))==0){

        }
        private_key=new keyobject(D,N);
        public_key=new keyobject(E,N);
        BigInteger test=OperationsWithNumbers.generator(2048);
        if(!test.equals(decrypt(encrypt(test)))){
            System.out.println("Дополнительная генерация ключей");
            generate_keys();
        }
    }

    /**
     * Реализации
     * @param message входное число для возведения в степень
     * @param exponent степень
     * @param module модуль (математика остатков по модулю)
     * @return возвращает остаток от деления числа в степени по модулю
     */
    static public BigInteger fast_power(BigInteger message,BigInteger exponent,BigInteger module){
        BigInteger Bigresult=OperationsWithNumbers.ModularExponentiation(message,exponent,module);
        return Bigresult;
    }

    /**
     * Вывод и показ ключей
     */
    public void show_keys(){
        System.out.println("<--------------------------------------->");
        System.out.println("Открытый ключ "+public_key.show_elements_in_key());
        System.out.println("Закрытый ключ "+private_key.show_elements_in_key());
        System.out.println("<--------------------------------------->");
    }

    /**
     * Шифрование сообщения
     * @param message исходный текст
     * @return широфанный текст
     */
    public BigInteger encrypt(BigInteger message){
        return fast_power(message,public_key.field1,public_key.field2);
    }

    /**
     * Шифрование сообщения
     * @param message широфанный текст
     * @return дешифрованный текст
     */
    public BigInteger decrypt(BigInteger message){
        return fast_power(message,private_key.field1,private_key.field2);
    }

    /**
     * Тестировочное шифрование
     * @param text Текст для шифрования
     */
     public void process_of_work(BigInteger text){
        System.out.println("<--------------------------------------->");
        BigInteger m=text;
        System.out.println("Исходный текст "+m);
        BigInteger t=encrypt(m);
        System.out.println("Шифротекст "+t);
        BigInteger r=decrypt(t);
        System.out.println("Расшифрованный текст "+r);
        System.out.println("<--------------------------------------->");
    }

    /**
     * Проверка статистическая на ошибку при шифровке и расшифровке хэша от 2 до 65537 ключами существующего объяекта Keys
     */
    public void checkforworkofalgorithm(){
         int ct=0;
         for(int i=0;i<100;i++){
             BigInteger message=OperationsWithNumbers.generator(ThreadLocalRandom.current().nextInt(1,2047));
             BigInteger enmessage=encrypt(message);
             BigInteger decmessage=decrypt(enmessage);
             if(decmessage.compareTo(message)!=0){
                 process_of_work(message);
                System.out.println("ЛОХ");
                ct++;
             }
         }

        System.out.println("Статистика ошибок в процентах равна "+ct+"%");
    }


    public keyobject getPrivate_key() {
        return private_key;
    }

    public keyobject getPublic_key() {
        return public_key;
    }
}



