
public class task1 {
    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int m1 = Integer.parseInt(args[1]);
        int n2 = Integer.parseInt(args[2]);
        int m2 = Integer.parseInt(args[3]);

        String path1 = getPath(n1, m1);
        String path2 = getPath(n2, m2);

        System.out.println("Путь: " + path1 + path2);

    }

    private static String getPath(int n, int m) {           /// метод для расчета пути, выводит String значения
        StringBuilder path = new StringBuilder();
        int current = 1;

        do {
            path.append(current);
            current = ((current + m - 2) % n) + 1;

        } while (current != 1);

        return path.toString();
    }
}