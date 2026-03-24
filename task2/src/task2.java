import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    public static void main(String[] args) throws IOException {

        BufferedReader ellipseReader = new BufferedReader(new FileReader(args[0]));         /// Создаем переменную для считывания данных эллипса из txt файла
        String[] centerCoords = ellipseReader.readLine().trim().split(" ");
        String[] radiusCoords = ellipseReader.readLine().trim().split(" ");
        ellipseReader.close();

        double x0 = Double.parseDouble(centerCoords[0]);                                    /// Координаты центра эллипса
        double y0 = Double.parseDouble(centerCoords[1]);

        double a = Double.parseDouble(radiusCoords[0]);                                     /// Координаты радиуса эллипса
        double b = Double.parseDouble(radiusCoords[1]);

        BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]));          /// Создаем переменную для считывания точeк из points.txt
        String line;

        while ((line = pointsReader.readLine()) != null) {                                  /// Построчно идеь до конца файла, записывая значения точек
            String[] coords = line.trim().split(" ");
            double x = Double.parseDouble(coords[0]);
            double y = Double.parseDouble(coords[1]);
            double value = Math.pow(x - x0, 2) / (a * a) + Math.pow(y - y0, 2) / (b * b);   /// Уравнение эллипса: (x - x0)^2/a^2 + (y - y0)^2/b^2 = 1

            if (Math.abs(value - 1.0) < 1e-10) {
                System.out.println("○ " + 0 + " - точка лежит на окружности");
            } else if (value < 1) {
                System.out.println("○ " + 1 + " - точка внутри");
            } else {
                System.out.println("○ " + 2 + " - точка снаружи");
            }
        }
        pointsReader.close();
    }
}