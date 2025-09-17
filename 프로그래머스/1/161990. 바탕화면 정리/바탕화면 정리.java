class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {};
        int lr = 50, lc = 50, rr = 0, rc = 0;
        for (int i=0; i<wallpaper.length; i++) {
            for (int j=0; j<wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    lr = Math.min(lr, i);
                    lc = Math.min(lc, j);
                    rr = Math.max(rr, i+1);
                    rc = Math.max(rc, j+1);
                }
            }
        }
        return answer = new int[]{lr, lc, rr, rc};
    }
}