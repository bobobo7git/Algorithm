import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 노선의 최초 상태
        Station[] dat = new Station[1_000_001];
        st = new StringTokenizer(br.readLine());
        Station prev = null;
        Station start = null;
        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            Station s = new Station(n);
            if (prev == null) {
                prev = s;
                start = s;
            } else {
                prev.next = s;
                s.prev = prev;
                prev = s;
            }
            dat[n] = s;
        }
        prev.next = start;
        start.prev = prev;
        // 쿼리
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int id = Integer.parseInt(st.nextToken());
            Station station = dat[id];
            int res;
            switch (cmd) {
                case "BN": {
                    int nextId = Integer.parseInt(st.nextToken());
                    Station next = new Station(nextId);
                    dat[nextId] = next;
                    res = station.buildNext(next);
                    break;
                }
                case "BP": {
                    int prevId = Integer.parseInt(st.nextToken());
                    Station prevStation = new Station(prevId);
                    dat[prevId] = prevStation;
                    res = station.buildPrev(prevStation);
                    break;
                }
                case "CP": {
                    res = station.closePrev();
                    dat[res] = null;
                    break;
                }
                case "CN": {
                    res = station.closeNext();
                    dat[res] = null;
                    break;
                }
                default: {
                    res = -1;
                }
            }   // end s-c
            bw.write(res+"\n");
        }   // end queries
        bw.flush();
        bw.close();
    }
    static class Station {
        int id;
        Station next, prev;
        Station(int id) {
            this.id = id;
        }
        int buildNext(Station next) {
            Station temp = this.next;
            this.next = next;
            next.prev = this;
            next.next = temp;
            temp.prev = next;
            return temp.id;
        }
        int buildPrev(Station prev) {
            Station temp = this.prev;
            this.prev = prev;
            prev.next = this;
            prev.prev = temp;
            temp.next = prev;
            return temp.id;
        }
        int closeNext() {
            Station temp = this.next;
            this.next = temp.next;
            temp.next.prev = this;
            return temp.id;
        }
        int closePrev() {
            Station temp = this.prev;
            this.prev = temp.prev;
            temp.prev.next = this;
            return temp.id;
        }
    }

}
