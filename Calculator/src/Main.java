import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String calc(String input){
        String operator = "";
        if (input.contains(" + ")) operator = " + ";
        else if (input.contains(" - ")) operator = " - ";
        else if (input.contains(" * ")) operator = " * ";
        else operator = " / ";

        String[] splitExp = input.split(" ");
        String stringNum1 = splitExp[0], stringNum2 = splitExp[2];
        Integer num1, num2;

        try {
            num1 = Integer.valueOf(stringNum1);
            num2 = Integer.valueOf(stringNum2);

            if (num1 >= 1 && num1 <= 10 && num2 >= 1 && num2 <= 10) {
                switch (operator) {
                    case " + ":
                        return Integer.toString(num1 + num2);
                    case " - ":
                        return Integer.toString(num1 - num2);
                    case " * ":
                        return Integer.toString(num1 * num2);
                    case " / ":
                        return Integer.toString(num1 / num2);
                }
            }
            else{
                try {
                    throw new IOException();
                } catch(IOException e){
                    System.out.println("Введены некорректные числа");
                    System.exit(-1);
                }
            }
        } catch (Exception e){
            String [] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            if (Arrays.asList(romanNumbers).contains(stringNum1) && Arrays.asList(romanNumbers).contains(stringNum2)){
                num1 = Arrays.asList(romanNumbers).indexOf(stringNum1) + 1;
                num2 = Arrays.asList(romanNumbers).indexOf(stringNum2) + 1;

                int res = 0;
                switch (operator) {
                    case " + ":
                        res = num1 + num2;
                        break;
                    case " - ":
                        res = num1 - num2;
                        break;
                    case " * ":
                        res = num1 * num2;
                        break;
                    case " / ":
                        res = num1 / num2;
                        break;
                }
                if (res > 0) {
                    List<RomanNumber> listRomanNumbers = RomanNumber.getReverseSortedValues();
                    String strRes = "";
                    int i = 0;
                    while ((res > 0) && (i < listRomanNumbers.size())) {
                        RomanNumber currentSymbol = listRomanNumbers.get(i);
                        if (currentSymbol.getValue() <= res) {
                            strRes += currentSymbol.name();
                            res -= currentSymbol.getValue();
                        } else {
                            i++;
                        }
                    }
                    return strRes;
                }
                else{
                    try {
                        throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e2){
                        System.out.println("В римской системе нет отрицательных чисел");
                        System.exit(-1);
                    }
                }
            }
            else{
                try {
                    throw new IOException();
                } catch(IOException e2){
                    System.out.println("Введены некорректные числа");
                    System.exit(-1);
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.nextLine().trim();

        if (exp.contains(" + ") || exp.contains(" - ") || exp.contains(" * ") || exp.contains(" / ")){
            boolean rightForm = true;
            if (exp.contains(" + ")) {
                if (exp.indexOf(" + ") != exp.lastIndexOf(" + ")) rightForm = false;
            }
            else if (exp.contains(" - ")) {
                if (exp.indexOf(" - ") != exp.lastIndexOf(" - ")) rightForm = false;
            }
            else if (exp.contains(" * ")) {
                if (exp.indexOf(" * ") != exp.lastIndexOf(" * ")) rightForm = false;
            }
            else {
                if (exp.indexOf(" / ") != exp.lastIndexOf(" / ")) rightForm = false;
            }

            if (rightForm) {
                System.out.println(calc(exp));
                System.exit(0);
            }
            else{
                try {
                    throw new IOException();
                } catch(IOException e){
                    System.out.println("Неверный формат математической операции");
                    System.exit(-1);
                }
            }
        }
        else{
            try {
                throw new IOException();
            } catch(IOException e){
                System.out.println("Не было введено арифметическое выражение");
                System.exit(-1);
            }
        }
    }
}