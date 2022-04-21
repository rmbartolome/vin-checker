package com.kata.checker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class KataApplication {

    public static void main(String[] args) {
        //String vin ="5YJ3E1EA7HF000337";
        String vin ="5YJ3E1EAXHF000347";
        var valid = checkVin(vin);
        log.info("se ha validado con exito VIN: {} , es valido: {} ", vin, valid);
    }

    public static boolean checkVin(String vin) {
        log.info("se invoca a validar VIN: {}", vin);
        if (invalidLetters(vin)){
            log.info("posee caracteres invalidos: {} ", vin);
            return false;
        }

        var numbers = convertedToNumbers(vin);

        log.info("1. transformado a numeros: {} ", Arrays.toString(numbers));

        var productos = calcularProducto(numbers);

        log.info("2. productos: {} ", Arrays.toString(productos));

        var suma = sumaProductos(productos);
        log.info("3. suma de productos: {} ", suma);

        log.info("4. modulo 11 de la suma de productos: {} ", suma % 11);
        log.info("5. check 9th character: {} ", character9th(suma % 11,vin));
        return character9th(suma % 11,vin);

    }

    private static boolean character9th(int mod, String vin){
        if(mod==10)
            return (vin.charAt(8)=='X');
        return (Character.getNumericValue(vin.charAt(8))==mod);
    }

    private static int[] calcularProducto(int[] numbers){
        int weights[] = new int[] {8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2};
        log.info("numbers length: {} ", numbers.length);
        int products[] = new int[numbers.length];
        for(int i=0; i<weights.length; i++) {
            products[i] = weights[i]*numbers[i];
        }
        return products;
    }

    private static int[] convertedToNumbers(String vin){
        Map<Character, Integer> mapValues = inicializarMap();
        List<Integer> numbers = new ArrayList<>();
        char[] ch = vin.toCharArray();
        for(Character c: ch){
            if(!Character.isDigit(c)){
                log.info("cambiar caracter: {} por numero:{} ", c, mapValues.get(c));
                numbers.add(mapValues.get(c));
            }else{
                numbers.add(Character.getNumericValue(c));
            }
        }
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static boolean invalidLetters(String vin){
        return vin.contains("I") || vin.contains("O") || vin.contains("Q");
    }

    private static Map<Character, Integer> inicializarMap(){
        Map<Character, Integer> mapValues = new HashMap<>();
        //A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
        //1 2 3 4 5 6 7 8   1 2 3 4 5   7   9 2 3 4 5 6 7 8 9
        mapValues.put('A', 1);		mapValues.put('J', 1);
        mapValues.put('B', 2);		mapValues.put('K', 2);
        mapValues.put('C', 3);	    mapValues.put('L', 3);
        mapValues.put('D', 4);	    mapValues.put('M', 4);
        mapValues.put('E', 5);		mapValues.put('N', 5);
        mapValues.put('F', 6);
        mapValues.put('G', 7);      mapValues.put('P', 7);
        mapValues.put('H', 8);

        mapValues.put('R', 9);      mapValues.put('W', 6);
        mapValues.put('S', 2);      mapValues.put('X', 7);
        mapValues.put('T', 3);      mapValues.put('Y', 8);
        mapValues.put('U', 4);      mapValues.put('Z', 9);
        mapValues.put('V', 5);
        return mapValues;
    }

    private static int sumaProductos(int [] productos){
        return Arrays.stream(productos).sum();
    }
}