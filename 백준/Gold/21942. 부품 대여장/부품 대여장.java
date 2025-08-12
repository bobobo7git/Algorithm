import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N L(DDD/hh:mm) F
        int N = Integer.parseInt(st.nextToken());
        String L = st.nextToken();
        int F = Integer.parseInt(st.nextToken());
        String[] li = L.split("/");
        int ddd = Integer.parseInt(li[0]);
        int hh = Integer.parseInt(li[1].split(":")[0]);
        int mm = Integer.parseInt(li[1].split(":")[1]);
        long limit = ddd * 1440L + hh * 60L + mm;

        ItemManager mg = new ItemManager();
        StringBuilder sb = new StringBuilder();
        Map<String, Long> fairs = new TreeMap<>();
        // N of records, asc
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String date = st.nextToken();
            String time = st.nextToken();
            String item = st.nextToken();
            String name = st.nextToken();
            int diff = mg.put(date, time, item, name);
            if (diff > limit) {
                if (!fairs.containsKey(name)) fairs.put(name, 0L);
                fairs.put(name, fairs.get(name)+(diff-limit)*F);
            }
        }
        for (String name: fairs.keySet()) {
            sb.append(name).append(' ').append(fairs.get(name)).append('\n');
        }
        if (sb.length() == 0) System.out.println(-1);
        else System.out.print(sb);

    }
    static class ItemManager {
        // user - records(item)
        Map<String, Map<String, Record>> dict;
        DateTimeParser parser;

        public ItemManager() {
            dict = new HashMap<>();
            parser = new DateTimeParser();
        }
        int put(String date, String time, String item, String name) {
            if (!dict.containsKey(name)) addUser(name);

            Map<String, Record> map = dict.get(name);
            Record record = new Record(item, date, time);
            if (map.containsKey(item)) {
                Record prev = map.get(item);
                int diff = parser.getDiff(prev, record);
                map.remove(item);
                return diff;
            } else {
                map.put(item, record);
            }
            return 0;
        }
        void addUser(String name) {
            dict.put(name, new HashMap<>());
        }
    }
    static class Record {
        String item;
        String date, time;
        public Record(String item) {
            this.item = item;
        }
        public Record(String item, String date, String time) {
            this.item = item;
            this.date = date;
            this.time = time;
        }

    }
    static class DateTimeParser {
        static final int[] month2days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        static int[] prefixSum;
        public DateTimeParser() {
            prefixSum = Arrays.copyOf(month2days, 13);
            for (int i=1; i<=12; i++) {
                prefixSum[i] += prefixSum[i-1];
            }
        }
        int getDiff(Record start, Record end) {
            return calculate(start.date, start.time, end.date, end.time);
        }
        private int calculate(String sd, String st, String ed, String et) {
            // y > m > d > h > m
            int[] sYMD = parseDate(sd);
            int[] eYMD = parseDate(ed);
            int[] sHM = parseTime(st);
            int[] eHM = parseTime(et);

            int diff = 0;
            // year: fixed
            // m d
            int eDays = prefixSum[eYMD[1]-1] + eYMD[2];
            int sDays = prefixSum[sYMD[1]-1] + sYMD[2];

            int eMins = eHM[0] * 60 + eHM[1] + eDays * 1440;
            int sMins = sHM[0] * 60 + sHM[1] + sDays * 1440;

            diff = eMins - sMins;

            return diff;
        }

        private int[] parseDate(String date) {
            String[] ymd = date.split("-");
            int[] intYMD = new int[3];
            intYMD[0] = Integer.parseInt(ymd[0]);
            intYMD[1] = Integer.parseInt(ymd[1]);
            intYMD[2] = Integer.parseInt(ymd[2]);
            return intYMD;
        }
        private int[] parseTime(String time) {
            String[] hm = time.split(":");
            int[] intHM = new int[2];
            intHM[0] = Integer.parseInt(hm[0]);
            intHM[1] = Integer.parseInt(hm[1]);

            return intHM;
        }
    }
}
