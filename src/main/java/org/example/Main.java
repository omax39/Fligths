package org.example;

import java.io.File;
import java.util.*;

import static org.example.Utils.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/tickets.json");

        //Функция parseJson из класса Utils для обработки JSON файла и преобразова его в список билетов
        List<Ticket> tickets = parseJson(file);

        System.out.println("Минимальное время перелета между Владивостоком и Тель-Авив для каждого перевозчика:");

        //Функция minFromFliesForAnyCarrier из класса Utils
        System.out.println(minFromFliesForAnyCarrier(tickets));
        System.out.println();

        System.out.println("Разница между средним арифметическим и медианой цен:");
        //Функция getDiffPriceAndAverage из класса Utils
        System.out.println(getDiffPriceAndAverage(tickets));
    }
}