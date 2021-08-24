package leetcode.easy;

public class BestTimeForStockTransaction {
    public int maxProfit(int[] prices) {
        int maxCur=0, maxSoFar=0;
        for (int i=1; i<prices.length; i++) {
            maxCur += prices[i] - prices[i-1];
            maxCur = Math.max(0, maxCur);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }


    public static void main(String[] args) {
        BestTimeForStockTransaction b = new BestTimeForStockTransaction();
        System.out.println(b.maxProfit(new int[] {7,1,5,3,6,4}));
    }
}
