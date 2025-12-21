#include <iostream>
using namespace std;

int main() {
	int t;
	cin >> t;
	while (t--> 0) {
		int a, b;
		cin >> a >> b;
		int c = 1;
		for (int i=0; i<b; i++) {
			c = (a*c) % 10;
		}
		int res = c == 0 ? 10 : c;
		cout << res << "\n";
	}
}