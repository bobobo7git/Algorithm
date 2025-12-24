#include <iostream>
#include <unordered_map>
using namespace std;

int main() {
    cin.tie(NULL);
    ios::sync_with_stdio(false);
    
    int N, M;
    cin >> N >> M;
    unordered_map<string, string> hmap;
    for (int i=0; i<N; i++) {
        string k, v;
        cin >> k >> v;
        hmap[k] = v;
    }
    for (int i=0; i<M; i++) {
        string k;
        cin >> k;
        cout << hmap[k] << '\n';
    }
    
}