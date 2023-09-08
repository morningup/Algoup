m,n=map(int,input().split())

#코드를 짜기전 기본정의 값이 N의 약수는 무조건 sqrt(N)범위에 존재한다.

for i in range(m,n+1):
    if i==1:#1은 소수가 아니므로 제외
        continue
    for j in range(2,int(i**0.5)+1): #sqrt == 제곱근 ex int(sqrt(12))일때 3이나옴
        #3과 12를 나누면 약수가 발생하므로 처음부터 소수가 안됨 
        #int(i**0.5)+1 에서 +1을 한 이유는 for문임을 고려해서 int(i**0.5)값 까지 계산하기 위함임
        if i%j==0: #약수가 존재하므로 소수가 아님
            break   #더이상 검사할 필요가 없으므로 멈춤
    else: #이렇게 하는 이유는 시간초과를 막고 효율적이기 위함이다.
        print(i)