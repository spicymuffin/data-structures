import random

NVERT = 100
NEDGE = 800

WEIGHT_MIN = 1
WEIGHT_MAX = 1050


def randint_excluding_n(a, b, n):
    if a == n - 1 or a == n:
        return random.randint(n + 1, b)
    if n + 1 == b or b == n:
        return random.randint(a, n - 1)
    return random.choice([random.randint(a, n - 1), random.randint(n + 1, b)])


if NEDGE > (NVERT) * (NVERT - 1):
    NEDGE = (NVERT) * (NVERT - 1)

edgemap = []
for i in range(NVERT):
    edgemap.append([0] * NVERT)

edgemap[2][0] = 0

for i in range(NVERT):
    for j in range(NVERT):
        print(edgemap[i][j], end=" ")
    print()

print(NVERT, NEDGE)

edges_generated = 0
for i in range(NVERT):
    origin = i
    destination = randint_excluding_n(0, NVERT - 1, i)
    weight = random.randint(WEIGHT_MIN, WEIGHT_MAX)
    while edgemap[origin][destination] != 0:
        origin = i
        destination = randint_excluding_n(0, NVERT - 1, i)
    print(origin, " ", destination, " ", weight, sep="")
    edges_generated += 1
    edgemap[origin][destination] = weight
    edgemap[destination][origin] = weight

for i in range(NEDGE - edges_generated):
    origin = random.randint(0, NVERT - 1)
    destination = randint_excluding_n(0, NVERT - 1, origin)
    weight = random.randint(WEIGHT_MIN, WEIGHT_MAX)
    while edgemap[origin][destination] != 0:
        # print(origin)
        origin = random.randint(0, NVERT - 1)
        destination = randint_excluding_n(0, NVERT - 1, origin)
        # for i in range(NVERT):
        #     for j in range(NVERT):
        #         print(edgemap[i][j], end=" ")
        #     print()

    print(origin, " ", destination, " ", weight, sep="")
    edges_generated += 1
    edgemap[origin][destination] = weight
    edgemap[destination][origin] = weight

for i in range(NVERT):
    for j in range(NVERT):
        print(edgemap[i][j], ", ", sep="", end="")
    print()
