class Solution {
    private record State(int time, int id) {}; 
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // servers that handled most no of requests 
        // (i % k) th server. If available, if not, then try (i + 1) % and so on... 
        TreeSet<Integer> available = new TreeSet<>(); 
        // initially all servers are free
        for(int i = 0; i < k; i++) available.add(i); 
        PriorityQueue<State> busy = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time)); 

        int cnt[] = new int[k]; // count of request handled by each server 
        int mx = 0; 
        for(int i = 0; i < arrival.length; i++) {
            while(!busy.isEmpty() && busy.peek().time <= arrival[i]) {
                // remove from pq and add to available list 
                int id = busy.poll().id; // remove 
                available.add(id);  // add 
            }

            if(available.isEmpty()) continue; // drop the request 

            Integer serverId = available.ceiling(i % k); 
            if(serverId == null) serverId = available.first(); 
            // find the next available server for the current 
            cnt[serverId]++; 
            mx = Math.max(mx, cnt[serverId]); 
            busy.add(new State(arrival[i] + load[i], serverId)); 
            available.remove(serverId); // remove from available list 
        }
        List<Integer> ans = new ArrayList<>(); 
        for(int i = 0; i < k; i++) if(cnt[i] == mx) ans.add(i); 
        return ans; 
    }
}