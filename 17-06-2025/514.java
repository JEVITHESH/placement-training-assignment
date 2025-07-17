import java.util.*;

class Solution {
    Map<Character, List<Integer>> charToIndices = new HashMap<>();
    int[][] memo;
    int ringLen;

    public int findRotateSteps(String ring, String key) {
        ringLen = ring.length();

        
        for (int i = 0; i < ring.length(); i++) {
            charToIndices.computeIfAbsent(ring.charAt(i), k -> new ArrayList<>()).add(i);
        }

        
        memo = new int[ring.length()][key.length()];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dfs(0, 0, ring, key);
    }

    private int dfs(int pos, int index, String ring, String key) {
        if (index == key.length()) return 0;

        if (memo[pos][index] != -1) return memo[pos][index];

        char target = key.charAt(index);
        int minSteps = Integer.MAX_VALUE;

        for (int newPos : charToIndices.get(target)) {
            
            int diff = Math.abs(newPos - pos);
            int step = Math.min(diff, ringLen - diff);
            
            int total = step + 1 + dfs(newPos, index + 1, ring, key);
            minSteps = Math.min(minSteps, total);
        }

        memo[pos][index] = minSteps;
        return minSteps;
    }
}
