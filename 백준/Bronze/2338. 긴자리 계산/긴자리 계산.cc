#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

string a, b;
bool neg_a, neg_b;
void preprocess(string& a, string& b) {
    neg_a = a[0] == '-';
    neg_b = b[0] == '-';

    // 절댓값으로 변경
    if (neg_a) a = a.substr(1);
    if (neg_b) b = b.substr(1);
    // 자릿수를 맞추기 위해 0 추가하기
    reverse(a.begin(), a.end());
    reverse(b.begin(), b.end());
    while (a.length() < b.length()) a.push_back('0');
    while (b.length() < a.length()) b.push_back('0');
    reverse(a.begin(), a.end());
    reverse(b.begin(), b.end());
}
int compare(string a, string b) {
    if (a.length() > b.length()) return 1;
    if (a.length() < b.length()) return -1;
    
    for (int i=0; i<a.length(); i++) {
        int v = (a[i] - '0') - (b[i] - '0');
        if (v > 0) return 1;
        else if (v < 0) return -1;
    }
    return 0;
}
string add(string a, string b) {
    int carry = 0;
    int n = a.length();
    string ret = "";
    for (int i=n-1; i>=0; i--) {
        int val = carry + (a[i] - '0') + (b[i] - '0');
        if (val > 9) {
            carry = 1;
            ret.push_back((val-10) + '0');
        } else {
            carry = 0;
            ret.push_back(val + '0');
        }
    }
    if (carry == 1) ret.push_back('1');
    reverse(ret.begin(), ret.end());
    return ret;
}
string get_complement(string x) {
    // 10의 보수 = 9의 보수 + 1
    // 9의 보수: 123 -> 876
    int n = x.length();
    string comp_nine = "";
    // 9의 보수 구하기
    for (int i=0; i<n; i++) {
        comp_nine.push_back('9' - x[i] + '0');
    }
    string one = "";
    for (int i=0; i<comp_nine.length()-1; i++) {
        one.push_back('0');
    }
    one.push_back('1');
    return add(comp_nine, one);
}
string subtract(string a, string b) {
    string ret = add(a, get_complement(b));
    // 길이가 길면 1을 버림
    if (ret.length() > a.length()) {
        ret.erase(ret.begin());
        if (ret[0] == '0') ret.erase(ret.begin());
        return ret;
    }
    // 올림수가 생기지 않으면 음수 -> 10의 보수 return
    string comp_ten = get_complement(ret);
    int i = 0;
    while (i < comp_ten.length() && comp_ten[i] == '0') {
        i++;
    }
    ret = "";
    for (; i<comp_ten.length(); i++) {
        ret.push_back(comp_ten[i]);
    }
    return ret;
}
string multiply(string a, string b) {
    /*
         123
       x  45
    --------
         615
        492
    --------
        5535
    O(N*M)
    */
    int n = a.length();
    int m = b.length();
    vector<int> sum(n+m, 0);
    for (int i=a.length()-1; i>=0; i--) {
        for (int j=b.length()-1; j>=0; j--) {
            int val = (a[i]-'0') * (b[j]-'0');
            int s = sum[i+j+1] + val;
            sum[i+j+1] = s % 10;
            sum[i+j] += s /10;   
        }
        
    }
    string ret = "";
    int i = 0;
    while (i < sum.size() && sum[i] == 0) i++;
    for (; i<sum.size(); i++) {
        ret.push_back(sum[i] + '0');
    }
    
    return ret.empty() ? "0" : ret;
}
int main() {
    cin >> a;
    cin >> b;
    preprocess(a, b);
    int cp = compare(a, b);
    string res_add, res_sub, res_mul;
    if (!neg_a && !neg_b) {
        res_add = add(a, b);
        if (cp > 0) res_sub = subtract(a, b);
        else if (cp < 0) res_sub = "-"+subtract(a, b);
        else res_sub = "0";
        res_mul = multiply(a, b);
    } else if (neg_a && neg_b) {
        res_add = "-" + add(a, b);
        if (cp > 0) res_sub = "-" + subtract(a, b);
        else if (cp < 0) res_sub = subtract(a, b);
        else res_sub = "0";
        res_mul = multiply(a, b);
    } else if (!neg_a && neg_b) {
        if (cp > 0) res_add = subtract(a, b);
        else if (cp < 0) res_add = "-" + subtract(a, b);
        else res_add = "0";
        res_sub = add(a, b);
        res_mul = "-" + multiply(a, b);
    } else {
        // - +
        if (cp > 0) res_add = "-" + subtract(a, b);
        else if (cp < 0) res_add = subtract(a, b);
        else res_add = "0";
        res_sub = "-" + add(a, b);
        res_mul = "-" + multiply(a, b);
    }
    cout << res_add << "\n";
    cout << res_sub << "\n";
    cout << res_mul << "\n";
    return 0;
}

