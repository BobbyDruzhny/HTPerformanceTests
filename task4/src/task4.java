import java.util.*;
import java.io.*;

public class task4 {
    public static void main(String[] args) {
        int median = 0;
        int moves = 0;
        String fileName = args[0];
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {  /// инициализация считывания с файла
            String line;
            while ((line = reader.readLine()) != null) {
                int num = Integer.parseInt(line.trim());
                numbers.add(num);                                                      /// занчение из строки файла записывается в лист numbers
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] numbsArray = new int[numbers.size()];                                     /// задается размер массива значением размера листа
        for (int i = 0; i < numbers.size(); i++) {
            numbsArray[i] = numbers.get(i);                                             /// записывается занчение массива из значений листа
        }

        median = numbsArray[numbers.size() / 2];                                        ///рассчитывается медиана

        for (int num : numbsArray) {
            moves += Math.abs(num - median);                                            /// приводятся все значения массива к медиане и считаем кол-во ходов
        }

        if (moves > 20){
            System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
        } else {
            System.out.println(moves);
        }
    }
}