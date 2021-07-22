package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class n399 {
        HashMap<String, HashMap<String, Double>> hm;
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            hm = new HashMap<>();
            int i = 0;
            for (List<String> l : equations) {
                hm.computeIfAbsent(l.get(0), k -> new HashMap<>()).put(l.get(1), values[i]);
                hm.computeIfAbsent(l.get(1), k -> new HashMap<>()).put(l.get(0), 1 / values[i]);
                i ++;
            }

            double[] res = new double[queries.size()];
            i = 0;
            for (List<String> q : queries) {
                res[i] = dfs(q.get(0), q.get(1), 1, new HashSet<String>());
                i ++;
            }
            return res;
        }

        double dfs(String s, String e, double value, HashSet<String> visit) {
            if (!hm.containsKey(s)) return -1;
            if (visit.contains(s)) return -1;
            if (s.equals(e)) {
                return value;
            }
            HashMap<String, Double> next = hm.get(s);
            visit.add(s);
            for (String k : next.keySet()) {
                double res = dfs(k, e, next.get(k) * value, visit);
                if (res != -1) return res;
            }
            visit.remove(s);
            return - 1;
        }

}
