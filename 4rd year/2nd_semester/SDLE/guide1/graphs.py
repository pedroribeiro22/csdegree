import networkx as nx
import random
import numpy as np
import matplotlib.pyplot as plt


class Edge:

    def __init__(self, node1, node2):
        self.node1 = node1
        self.node2 = node2

    def toTuple(self):
        return (self.node1, self.node2)


def generate_graph(node_count):
    graph = nx.Graph()
    for i in range(0, node_count):
        graph.add_node(i)
    return graph


def generate_random_edge(node_count):
    nodes = random.sample(range(node_count), 2)
    e = Edge(nodes[0], nodes[1])
    return e


def run_task(graph, node_count):
    iterations = 0
    while not nx.is_connected(graph):
        done = False
        while not done:
            new_edge = generate_random_edge(node_count)
            test_tuple = new_edge.toTuple()
            if not graph.has_edge(*test_tuple):
                done = True
            if done:
                (es, ee) = new_edge.toTuple()
                graph.add_edge(es, ee)
        iterations = iterations + 1
    return iterations


def run_study(runs):
    results = {}
    for i in range(1, 150):
        results[str(i)] = []
        for j in range(0, runs):
            graph = generate_graph(i)
            iterations = run_task(graph, i)
            results[str(i)].append(iterations)
    return results


def normalize_results(results):
    for key in results:
        copy = results[key]
        results[key] = np.array(copy).mean()


def plot_data(results):
    new_results = sorted(results.items(), key=lambda kv: kv[1], reverse=True)
    plt.plot(results.keys(), results.values())
    plt.xlabel("Número de nodos do grafo")
    plt.ylabel("Número de arestas adicionadas até ser conectado (em média)")
    plt.savefig("results.png")


if __name__ == "__main__":

    results = run_study(20)
    normalize_results(results)
    plot_data(results)
