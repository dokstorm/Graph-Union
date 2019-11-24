import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public static int[][] fromGraph6toMatrix() {
        Scanner scanner = new Scanner("input.txt");
        String graph6 = scanner.nextLine();
        List<Integer> d = new ArrayList<>();

        for (int i = 0; i < graph6.length(); i++) {
            d.add((int)graph6.charAt(i) - 63);
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < d.size(); i++) {
            StringBuilder sb = new StringBuilder(d.get(i));
            while (sb.length() % 6 != 0) {
                sb.append(0);
            }
            sb.reverse();
            s.append(sb);
        }
    }

 /*   void reading(int& n, vector<vector<int> >& v)
    {
        ifstream fin("input.txt");
        char c;
        fin>>c>>c;
        n = (int)c;
        n -= 63;

        vector<int> d;
        while(fin>>c)
            d.push_back((int)c-63);

        string s="";
        for (int i=0; i<(int)d.size(); i++)
            s+=get_0_and_1(d[i]);

        vector<vector<int> > vec(n, vector<int>());
        for (int i=0; i<n*n; i++)
            if ((int)s[i]-'0')
                vec[i/n].push_back(i%n);

        v = vec;
        fin.close();
    }*/


    /*public static String readMatrix() {
        Scanner scanner = new Scanner("input.txt");

        while (scanner.hasNextLine()) {
            List<Integer> result =
            String line = scanner.nextLine().split(" "));
        }
    }*/

    public static void main(String[] args) {
        System.out.println(N(460175067));
        System.out.println(R("1000101100011100"));

        int[][] matrix = {{0, 1, 1, 1, 1, 0, 0}, {1, 0, 1, 1, 1, 0, 0}, {1, 1, 0, 1, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 1, 0}};
        System.out.println(toGraph6(matrix));

    }
}
