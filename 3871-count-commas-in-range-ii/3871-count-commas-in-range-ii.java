class Solution {
    public long countCommas(long n) {

        long commas = 0;
        long base_level = 1000;

        while ( base_level <= n){
            commas+= (n-base_level) + 1;
            base_level *= 1000;
        }
        return commas;
    }
}