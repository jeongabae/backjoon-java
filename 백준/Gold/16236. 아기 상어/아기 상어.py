import heapq
from collections import deque

INF = 1e9
n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]

cur_size = 2
cur_x, cur_y = 0, 0
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for i in range(n):
    for j in range(n):
        if arr[i][j] == 9:
            cur_x, cur_y = i, j
            arr[cur_x][cur_y] = 0

def bfs():
    q = deque([(cur_x, cur_y, 0)])
    visited = [[False] * n for _ in range(n)]
    visited[cur_x][cur_y] = True
    fish = []

    while q:
        x, y, dist = q.popleft()

        if 1 <= arr[x][y] < cur_size:
            heapq.heappush(fish, (dist, x, y))

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and arr[nx][ny] <= cur_size:
                visited[nx][ny] = True
                q.append((nx, ny, dist + 1))

    return fish

ans = 0
ate = 0
while True:
    fish_list = bfs()
    if not fish_list:
        print(ans)
        break

    dist, nx, ny = heapq.heappop(fish_list)
    ans += dist
    cur_x, cur_y = nx, ny
    arr[nx][ny] = 0
    ate += 1

    if cur_size == ate:
        cur_size += 1
        ate = 0