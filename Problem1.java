import java.util.HashMap;
/*
Minimum Domino Rotations For Equal Row
approach:
1. we can get equal row only when we have an element which occurs>=len(arr)
2. consider a pair, if we're not able to find answer with those elements from pair, then definitely there is no answer
time: both: O(n)
space: both: O(1)
 */
public class Problem1 {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int max = -1;
        int n = tops.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i< n;i++) {
            map.put(tops[i], map.getOrDefault(tops[i], 0)+1);
            map.put(bottoms[i], map.getOrDefault(bottoms[i], 0)+1);
            if (map.get(tops[i])>=n) {
                max = tops[i];
                break;
            }
            if (map.get(bottoms[i])>=n) {
                max = bottoms[i];
                break;
            }
        }
        if (max==-1) return -1;

        int trots = 0;
        int brots = 0;
        for (int i=0;i< tops.length;i++) {
            if (tops[i]!=max && bottoms[i]!=max) return -1;

            if (tops[i]!=max) trots++;
            if (bottoms[i]!=max) brots++;
        }

        return Math.min(trots, brots);
    }

    public int minDominoRotationsOnePass(int[] tops, int[] bottoms) {
        int t = tops[0], b = bottoms[0];
        int ans;
        if (( ans = check(tops, bottoms, t))==-1) {
            return check(tops, bottoms, b);
        }
        return ans;
    }

    public int check(int[] tops, int []bottoms, int target) {
        int trots = 0;
        int brots = 0;

        for (int i=0;i< tops.length;i++) {
            if (tops[i]!=target && bottoms[i]!=target) return -1;

            if (tops[i]!=target) trots++;
            if (bottoms[i]!=target) brots++;
        }
        return Math.min(trots, brots);
    }
}
