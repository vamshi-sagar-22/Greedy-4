import java.util.*;

/*
Shortest Way to Form String
approach:
we can see that, if source doesn't contain any character of target then we cannot form target
everytime we reach end of source, reset it to 0 that implies, one subsequence is done
optimization: once we reach end of source, place the pointer to the index of target element in source
time: brute force: O(mxn) optimized: O(len(s)+len(t) log k)
space: O(1) optimized: O(len(s))
 */
public class Problem2 {
    private int shortestWayToString(String s, String t) {
        int i = 0, j = 0;
        int minPath = 0;
        HashSet<Character> set = new HashSet<>();

        for (int l = 0; l<s.length();l++) {
            set.add(s.charAt(l));
        }
        while (j<t.length()) {
            char schar = s.charAt(i);

            if (!set.contains(schar)) return -1;
            char tchar = t.charAt(j);

            if (schar==tchar) {
                i++;
                j++;
            }
            else {
                i++;
            }
            if (i==s.length())
            {
                i = 0;
                minPath++;
            }
        }
        return minPath;
    }

    private int shortestWayToStringOptimized(String s, String t) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        int minPath = 1;

        for (int i=0;i<s.length();i++) {
            char schar = s.charAt(i);
            if (!map.containsKey(schar)) {
                map.put(schar, new ArrayList<>());
            }
            map.get(schar).add(i);
        }
        int j=0;
        for (int i=0;i<t.length();i++) {
            char tchar = t.charAt(i);
            if (!map.containsKey(tchar)) return -1;
            List<Integer> li = map.get(tchar);
            int k = Collections.binarySearch(li, j);
            if (k<0) {
                k = (-k)-1;
            }
            if (k==li.size()) {
                j = li.get(0);
                minPath++;
            }
            else {
                j = li.get(k);
            }
            i++;j++;
        }
        return minPath;
    }

    public static void main(String[] args) {
        Problem2 problem2 = new Problem2();
        problem2.shortestWayToStringOptimized("abc", "abcbc");
        problem2.shortestWayToString("abc", "abcbc");
    }
}
