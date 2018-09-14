import random
import copy

graph = dict()

with open("kargerMinCut.txt", "r") as f:
    try:
        while True:
            vertices = [int(x) for x in f.readline().rstrip().split()]
            if vertices == []:
                break
            graph[vertices[0]] = vertices[1:]
    except(StopIteration):
        pass

def chooseEdge(graph):
    fromVertex = random.choice(list(graph.keys()))
    toVertex = random.choice(graph[fromVertex])
    return fromVertex, toVertex


def kargerMinCut(graph):
    while len(graph) > 2:
        fromVertex, toVertex = chooseEdge(graph)
        graph[fromVertex].extend(graph[toVertex])
        for x in graph[toVertex]:
            graph[x].remove(toVertex)
            graph[x].append(fromVertex)
        while fromVertex in graph[fromVertex]:
            graph[fromVertex].remove(fromVertex)
        del graph[toVertex]

    for key in graph.keys():
        return len(graph[key])


def operate(n):
    count = 100000
    for i in range(n):
        g = copy.deepcopy(graph)
        minCut = kargerMinCut(g)
        if minCut < count:
            count = minCut
    return count
    
if __name__ == '__main__':
    print(operate(50))


