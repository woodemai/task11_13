package ru.vsu.cs.savchenko_n_a.task11_13;

public class Solution {

    public static String convertIntToString(int number) {
        int placeIndex = 0;
        StringBuilder result = new StringBuilder();
        if (number == 0) {
            return "ноль";
        }
        if (number == 1000000000) {
            return "миллиард";
        }
        while (number != 0) {
            int num = number % 1000;

            String[] names;
            if (placeIndex == 0) {
                result.insert(0, intToStringFrom1To999(num, placeIndex));
            } else {
                if (placeIndex == 1) {
                    names = new String[]{"тысяча ", "тысячи ", "тысяч "};
                } else {
                    names = new String[]{"миллион ", "миллиона ", "миллионов "};
                }
                if (num % 10 == 1) {
                    result.insert(0, intToStringFrom1To999(num, placeIndex) + names[0]);
                } else if (num % 10 == 2 || num % 10 == 3 || num % 10 == 4) {
                    result.insert(0, intToStringFrom1To999(num, placeIndex) + names[1]);
                } else if (num != 0){
                    result.insert(0, intToStringFrom1To999(num, placeIndex) + names[2]);
                }
            }
            placeIndex++;
            number /= 1000;
        }
        return String.valueOf(result);
    }

    public static StringBuilder intToStringFrom1To999(int number, int plcIndex) {
        String[][] numbersNames = new String[][]{
                {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
                {"", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"},
                {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"},

        };
        if (plcIndex == 1) {
            numbersNames[0][1] = "одна";
            numbersNames[0][2] = "две";
        }
        String[] teens = new String[]{"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать ", "восемнадцать", "девятнадцать",};
        int placeIndex = 0;
        StringBuilder representation = new StringBuilder();
        while (number != 0) {
            int digit = number % 10;
            String currentDigitRepresentation;

            if (placeIndex == 0 && number % 100 < 20 && number % 100 > 9) {
                currentDigitRepresentation = teens[digit];
            } else {
                currentDigitRepresentation = numbersNames[placeIndex][digit];
            }
            String pref = " ";
            if (currentDigitRepresentation.length() == 0) {
                pref = "";
            }
            representation.insert(0, currentDigitRepresentation + pref);
            placeIndex++;
            number /= 10;
        }
        return representation;
    }
}
