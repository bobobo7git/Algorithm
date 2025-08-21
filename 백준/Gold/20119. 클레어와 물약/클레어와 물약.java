import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] inDegree;
    static Set<Integer> inventory;
    static Recipe[] recipes;
    static List<List<Integer>> ingre2Recipe;
    public static void main(String[] args) throws IOException {
        input();
        topology();

        StringBuilder sb = new StringBuilder();
        sb.append(inventory.size()).append('\n');
        for (int x: inventory) {
            sb.append(x).append(' ');
        }

        System.out.println(sb.toString().trim());
    }
    static void topology() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        for (int x: inventory) {
            q.offer(x);
        }

        while (!q.isEmpty()) {
            int ingredient = q.poll();
            if (visited[ingredient]) continue;
            visited[ingredient] = true;
            // 재료가 포함된 레시피 탐색
            for (int next: ingre2Recipe.get(ingredient)) {
                inDegree[next]--;   // 해당 레시피 진입차수-1
                if (inDegree[next] == 0) {
                    // 레시피를 쓸 수 있으면 제조
                    int potion = recipes[next].potion;
                    q.offer(potion);
                    inventory.add(potion);
                }
            }
        }
    }

    static void input() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ingre2Recipe = new ArrayList<>();
        recipes = new Recipe[M];

        for (int i=0; i<=N; i++) {
            ingre2Recipe.add(new ArrayList<>());
        }

        inDegree = new int[M];
        inventory = new TreeSet<>();

        for (int i=0; i<M; i++) {
            Recipe recipe = new Recipe(i);

            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            for (int j=0; j<k; j++) {
                int x = Integer.parseInt(st.nextToken());
                ingre2Recipe.get(x).add(i);
            }
            int r = Integer.parseInt(st.nextToken());
            recipe.setPotion(r);
            recipes[i] = recipe;
            inDegree[i] = k;    // i번 레시피의 진입차수
        }

        int L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<L; i++) {
            int y = Integer.parseInt(st.nextToken());
            inventory.add(y);
        }
    }
    static class Recipe {
        int id;
        int potion;
        public Recipe(int id) {
            this.id = id;
        }
        public Recipe(int id, int potion) {
            this.id = id;
            this.potion = potion;
        }
        void setPotion(int potion) {
            this.potion = potion;
        }
    }
}
