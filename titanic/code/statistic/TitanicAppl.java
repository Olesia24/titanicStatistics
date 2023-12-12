package statistic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TitanicAppl {
    //Считать файл tarin.csv, сделать расчеты:
    //Задание 1. Подсчитайте общую стоимость проезда (выручка от рейса).
    //Задание 2. Посчитайте средний тариф для 1,2,3 классов путешествия.
    //Задание 3. Подсчитайте общее количество выживших и погибших пассажиров.
    //Задание 4. Подсчитайте общее количество выживших и погибших мужчин, женщин и детей (до 18 лет).
    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("titanic/code/statistic/train.csv"))) {
            String str = br.readLine();
            String[] cells = str.split(",");
            printCells(cells);
            List<Model> list = new ArrayList<>();
            int counter = 0;
            str = br.readLine();
            while (str != null) {
                counter++;
                cells = str.split(",");
                double fare = Double.parseDouble(cells[10]);
                boolean isSurvived = cells[1].equals("1");
                int pclass = Integer.parseInt(cells[2]);
                boolean isFemale = cells[5].equals("female");
                double age = cells[6].length() == 0 ? 19 : Double.parseDouble(cells[6]);
                list.add(new Model(fare, pclass, isSurvived, isFemale, age));
                str = br.readLine();
                printCells(cells);
            }
            double totalSum = list.stream()
                    .mapToDouble(n -> n.getFare())
                    .sum();
            System.out.println("Sum of fare: " + totalSum); //sum of fare
            double avgFare = list.stream()
                    .mapToDouble(n -> n.getFare())
                    .average().orElse(0);
            System.out.println("Average price of a fare: " + avgFare);//avg price
            double avg1Class = list.stream()
                    .filter(f -> f.getpClass() == 1)
                    .mapToDouble(Model::getFare)
                    .average().orElse(0);
            System.out.println("Average price of 1 class: " + avg1Class); //avg tariff 1 class
            double avg2Class = list.stream()
                    .filter(f -> f.getpClass() == 2)
                    .mapToDouble(Model::getFare)
                    .average().orElse(0);
            System.out.println("Average price of 2 class: " + avg2Class);//avg tariff 2 class
            double avg3Class = list.stream()
                    .filter(f -> f.getpClass() == 3)
                    .mapToDouble(Model::getFare)
                    .average().orElse(0);
            System.out.println("Average price of 3 class: " + avg3Class);//avg tariff 3 class
            int sumSurv = list.stream()
                    .filter(s->s.isSurvived())
                    .collect(Collectors.toList())
                    .size();
            System.out.println("Sum of survivors: " + sumSurv); //sum of survivors(1)
            System.out.println("Sum of death: " + (list.size() - sumSurv));//sum of death (0)
            long sumSurvMale= list.stream()
                    .filter(s->s.isSurvived)
                    .filter(s->!s.isFemale && s.getAge()>=18)
                    .count();
            System.out.println("Sum of survivors male: " + sumSurvMale); //sum of survivors male
            long sumDeathMale= list.stream()
                    .filter(s->!s.isSurvived)
                    .filter(s->!s.isFemale && s.getAge()>=18)
                    .count();
            System.out.println("Sum of death male: " + sumDeathMale);//sum of death male(0)
            long sumSurvFemale= list.stream()
                    .filter(s->s.isSurvived)
                    .filter(s->s.isFemale && s.getAge()>=18)
                    .count();
            System.out.println("Sum of survivors Female: " + sumSurvFemale);//sum of survivors female
            long sumDeathFemale= list.stream()
                    .filter(s->!s.isSurvived)
                    .filter(s->s.isFemale && s.getAge()>=18)
                    .count();
            System.out.println("Sum of death female: " + sumDeathFemale);//sum of death female(0)
            long sumSurvChildr= list.stream()
                    .filter(s->s.isSurvived)
                    .filter(s->s.getAge()<18)
                    .count();
            System.out.println("Sum of survivors children: " + sumSurvChildr);//sum of survivors children
            long sumDeathChild= list.stream()
                    .filter(s->!s.isSurvived)
                    .filter(s->s.getAge()<18)
                    .count();
            System.out.println("Sum of death children: " + sumDeathChild);//sum of death children(0)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void printCells(String[] cells) {
        for (String cell : cells) {
            System.out.print(cell + " - " + "\t");
        }
        System.out.println();
    }
}




