import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Integer> R(String x) {
        StringBuilder sb = new StringBuilder(x);
        while (sb.length() % 6 != 0) {
            sb.append(0);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < sb.length(); i += 6) {
            result.add(Integer.parseInt(sb.substring(i, i + 6), 2) + 63);
        }

        return result;
    }

    public static List<Integer> N(int n) {
        List<Integer> result = new ArrayList<>();
        if (n >= 0 && n <= 62) {
            result.add(n + 63);
            return result;
        }
        else if (n >= 63 && n <= 258047) {
            result.add(126);

            result.addAll(R(String.format("%18s", Integer.toBinaryString(n))
                    .replace(' ', '0')));
            return result;
        }
        else {
            result.add(126);
            result.add(126);
            result.addAll(R(String.format("%36s", Integer.toBinaryString(n))
                    .replace(' ', '0')));
            return result;
        }
    }

    public static String toGraph6(int[][] matrix)   {
        int n = matrix.length;
        StringBuilder x = new StringBuilder();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                x.append(matrix[j][i]);
            }
        }
        StringBuilder result = new StringBuilder();
        List<Integer> s = N(n);
        s.addAll(R(x.toString()));
        for (Integer c : s) {
            result.append(Character.toChars(c));
        }
        return result.toString();
    }

    public static int[][] fromGraph6toMatrix(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String graph6 = br.readLine();
        List<Integer> d = new ArrayList<>();

        for (int i = 0; i < graph6.length(); i++) {
            d.add((int) graph6.charAt(i) - 63);
        }

        int n = d.get(0);
        d.remove(0);

        StringBuilder s = new StringBuilder();
        for (Integer integer : d) {
            s.append(String.format("%8s", Integer.toBinaryString(integer))
                    .replace(' ', '0').substring(2));
        }
        int[][] result = new int[n][n];
        int count = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                result[j][i] = Integer.parseInt(String.valueOf(s.toString().charAt(count)));
                result[i][j] = Integer.parseInt(String.valueOf(s.toString().charAt(count)));
                count++;
            }
        }
        return result;
    }

    public static int[][] uniteGraphs(int[][] graph1, int[][] graph2) {
        int n1 = graph1.length;
        int n2 = graph2.length;
        int[][] result = new int[n1 + n2][n1 + n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n1; j++) {
                result[i][j] = graph1[i][j];
            }
        }
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) {
                result[i + n1][j + n1] = graph2[i][j];
            }
        }
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void out(String graph6) throws IOException {
        File file = new File("out.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(graph6);
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        int[][] graph1 = fromGraph6toMatrix("input1.txt");
        int[][] graph2 = fromGraph6toMatrix("input2.txt");
        int[][] result = uniteGraphs(graph1, graph2);
        printMatrix(result);
        String resultGraph6 = toGraph6(result);
        System.out.println("Graph6 result: " + resultGraph6);
        out(toGraph6(result));
    }
}
