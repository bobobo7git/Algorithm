def P(n, r):
    ans = 1
    for i in range(r):
        ans *= (n-i)
        ans //= (i+1)
    return ans

T = int(input())
for tc in range(1, T+1):
    N, M = map(int, input().split())
    ans = P(M,N )
    print(ans)