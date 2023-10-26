import java.util.*;

public class Main {
    public static long Euclid(long a, long b) {
        if (b == 0) return a;
        return Euclid(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long K = sc.nextLong();
        long anw = 0;

        HashMap<Long, Long> dic = new HashMap<>();
        ArrayList<Pair<Long, Long>> v = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long n = sc.nextLong();
            long k = Euclid(n, K);

            if (!dic.containsKey(k)) dic.put(k, 0L);
            dic.put(k, dic.get(k) + 1);
        }

        for (Map.Entry<Long, Long> entry : dic.entrySet()) {
            v.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).first * v.get(i).first * v.get(i).first % K == 0 && v.get(i).second >= 3)
                anw += (v.get(i).second - 2) * (v.get(i).second - 1) * v.get(i).second / 6;
        }

        for (int i = 0; i < v.size(); i++) {
            for (int j = i + 1; j < v.size(); j++) {
                if (v.get(i).first * v.get(i).first * v.get(j).first % K == 0 && v.get(i).second >= 2)
                    anw += (v.get(i).second - 1) * v.get(i).second * v.get(j).second / 2;
                if (v.get(j).first * v.get(j).first * v.get(i).first % K == 0 && v.get(j).second >= 2)
                    anw += (v.get(j).second - 1) * v.get(j).second * v.get(i).second / 2;
            }
        }

        for (int i = 0; i < v.size(); i++) {
            for (int j = i + 1; j < v.size(); j++) {
                for (int k = j + 1; k < v.size(); k++) {
                    if (v.get(i).first * v.get(j).first * v.get(k).first % K == 0)
                        anw += v.get(i).second * v.get(j).second * v.get(k).second;
                }
            }
        }

        System.out.println(anw);
    }

    static class Pair<T1, T2> {
        T1 first;
        T2 second;

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }
}