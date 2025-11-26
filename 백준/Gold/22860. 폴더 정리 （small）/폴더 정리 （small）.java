import java.io.*;
import java.util.*;
/*
* 각 폴더별로 유니크한 파일의 종류 수, 파일의 총 개수
* 하위 폴더의 파일 종류를 전부 합쳐야 함
* 한 폴더 안에 같은 이름의 파일이 두 개 이상 x
* main 하위 디렉토리에 같은 이름의 폴더 x
* */
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Folder.main = Folder.mkdir("main");
        for (int i=0; i<N+M; i++) {
            st = new StringTokenizer(br.readLine());
            String prev = st.nextToken();
            String name = st.nextToken();
            boolean isFolder = Integer.parseInt(st.nextToken()) == 1;

            Folder p = Folder.mkdir(prev);
            if (isFolder) {
                Folder f = Folder.mkdir(name);
                p.add(f);
            } else {
                p.add(name);
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            String path = br.readLine();
            int[] res = Folder.find(path);
            sb.append(res[0]).append(' ').append(res[1]).append('\n');
        }
        System.out.print(sb);
    }
    static class Folder {
        String name;
        Map<String, Folder> folders;
        Map<String, Integer> files;

        static Map<String, Folder> FOLDERS = new HashMap<>();
        static Folder main;
        private Folder(String name) {
            this.name = name;
            folders = new HashMap<>();
            files = new HashMap<>();
        }
        static Folder mkdir(String name) {
            if (FOLDERS.containsKey(name)) {
                return FOLDERS.get(name);
            }
            Folder folder = new Folder(name);
            FOLDERS.put(name, folder);
            return folder;
        }
        void add(Folder o) {
            this.folders.put(o.name, o);
        }
        void add(String file) {
            if (files.containsKey(file)) {
                files.put(file, files.get(file)+1);
            } else files.put(file, 1);
        }
        static int[] find(String path) {
            String[] route = path.split("/");
            Map<String, Integer> fmap = new HashMap<>();

            /*
            * route의 끝까지 가서 dfs 시작
            * */
            Folder now = FOLDERS.get(route[0]);
            for (int i=1; i<route.length; i++) {
                now = now.folders.get(route[i]);
            }
            Deque<Folder> stack = new ArrayDeque<>();
            stack.push(now);
            while (!stack.isEmpty()) {
                now = stack.pop();
                for (String file: now.files.keySet()) {
                    if (fmap.containsKey(file)) {
                        fmap.put(file, fmap.get(file)+now.files.get(file));
                    } else {
                        fmap.put(file, now.files.get(file));
                    }
                }
                for (Folder next: now.folders.values()) {
                    stack.push(next);
                }
            }
            int unique = fmap.size();
            int cnt = 0;
            for (String file: fmap.keySet()) {
                cnt += fmap.get(file);
            }
            return new int[]{unique, cnt};

        }
        @Override
        public String toString() {return name;}
    }


}
