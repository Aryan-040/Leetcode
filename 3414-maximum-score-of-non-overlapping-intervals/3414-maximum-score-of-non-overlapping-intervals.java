class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (a, b) -> !intervals.get(a).get(0).equals(intervals.get(b).get(0)) 
            ? Integer.compare(intervals.get(a).get(0), intervals.get(b).get(0)) 
            : Integer.compare(intervals.get(a).get(1), intervals.get(b).get(1)));
        
        int[] S = new int[n];
        for (int i = 0; i < n; i++) S[i] = intervals.get(p[i]).get(0);

        long[][] W = new long[n + 1][5];
        List<Integer>[][] Ids = new List[n + 1][5];
        for (int i = 0; i <= n; i++) for (int k = 0; k <= 4; k++) Ids[i][k] = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            int r = intervals.get(p[i]).get(1), w = intervals.get(p[i]).get(2), id = p[i];
            int l = 0, h = n;
            while (l < h) { int m = (l + h) / 2; if (S[m] > r) h = m; else l = m + 1; }
            
            for (int k = 1; k <= 4; k++) {
                long tw = W[l][k-1] + w;
                List<Integer> tids = new ArrayList<>(Ids[l][k-1]);
                tids.add(id); Collections.sort(tids);

                long bw = W[i+1][k]; List<Integer> bids = Ids[i+1][k];

                if (tw > bw || (tw == bw && isLex(tids, bids))) {
                    W[i][k] = tw; Ids[i][k] = tids;
                } else {
                    W[i][k] = bw; Ids[i][k] = bids;
                }
            }
        }
        return Ids[0][4].stream().mapToInt(x -> x).toArray();
    }

    private boolean isLex(List<Integer> a, List<Integer> b) {
        if (b.isEmpty()) return true;
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) return a.get(i) < b.get(i);
        }
        return a.size() < b.size();
    }
}