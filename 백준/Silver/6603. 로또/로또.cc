#include <iostream>
#include <vector>
using namespace std;

int k;
void recur(vector<int> a, vector<int> stack, int idx) {
	if (stack.size() == 6) {
		for (int v: stack) {
			cout << v << ' ';
		}
		cout << '\n';
		return;
	}
	for (int i=idx; i<k; i++) {
		stack.push_back(a[i]);
		recur(a, stack, i+1);
		stack.pop_back();
	}
	
}
int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	while (true) {
		cin >> k;
		if (k == 0) break;
		int n;
		vector<int> a;
		for (int i=0; i<k; i++) {
			cin >> n;
			a.push_back(n);
		}
		vector<int> stack;
		
		recur(a, stack, 0);
		cout << '\n';
	}
}