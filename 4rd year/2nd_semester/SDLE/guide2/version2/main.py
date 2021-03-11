import networkx as nx
import random
import numpy as np
import matplotlib.pyplot as plt
import os

threshold = 1000


class Edge:

    def __init__(self, node1, node2):
        self.node1 = node1
        self.node2 = node2

    def toTuple(self):
        return (self.node1, self.node2)


class GraphAndWeights:

    def __init__(self, graph, node_weights):
        self.graph = graph
        self.node_weights = node_weights

    def add_node(self, new_node):
        self.graph.add_node(new_node)
        self.node_weights[str(new_node)] = 1

    def add_edge(self, es, ee):
        self.graph.add_edge(es, ee)
        old_es_weight = self.node_weights[str(es)]
        old_ee_weight = self.node_weights[str(ee)]
        self.node_weights[str(es)] = old_es_weight + 1
        self.node_weights[str(ee)] = old_ee_weight + 1

    def increment_weight(self, node):
        previous_weight = self.node_weights[str(node)]
        new_weight = previous_weight + 1
        self.node_weights[str(node)] = new_weight

    def get_node_weights(self):
        return self.node_weights

    def get_graph(self):
        return self.graph


def generate_graph_and_weights(node_count):
    graph = nx.Graph()
    node_weights = {}
    for i in range(1, node_count + 1):
        graph.add_node(i)
        node_weights[str(i)] = 1
    return GraphAndWeights(graph, node_weights)


def generate_weighted_random_edge(full_graph, new_node):
    full_graph.add_node(new_node)
    chosen_node = random.choices(list(full_graph.node_weights.keys()), list(
        full_graph.node_weights.values()), k=1)[0]
    e = Edge(new_node, int(chosen_node))
    return e


def run_task(starting_nodes):
    graph = generate_graph_and_weights(starting_nodes)
    iterations = 0
    while (not nx.is_connected(graph.get_graph()) and iterations < threshold):
        new_edge = generate_weighted_random_edge(
            graph, iterations + starting_nodes + 1)
        (es, ee) = new_edge.toTuple()
        graph.add_edge(es, ee)
        iterations = iterations + 1
    return iterations, graph.get_node_weights()


def run_study(runs, starting_nodes_range):
    if not os.path.isdir("nodes_weight_graphs"):
        os.mkdir("nodes_weight_graphs")
    iterations_array = []
    for i in range(1, starting_nodes_range + 1):
        iterations, node_weights = run_task(i)
        iterations_array.append(iterations)
        plot_node_weights_graph(
            node_weights, "nodes_weight_graphs/" + str(i) + ".png")
    plot_iterations_graph(iterations_array)
    return


def plot_iterations_graph(result):
    xx = [i for i in range(1, len(result) + 1)]
    yy = result
    plt.plot(xx, yy)
    plt.xlabel("Número de nodos iniciais")
    plt.ylabel("Número de arestas adicionadas")
    plt.title(
        "Número de arestas adicionadas em função do número de nodos adicionais")
    plt.savefig("iterations.png")
    plt.clf()


def plot_node_weights_graph(result, output_file):
    xx = []
    yy = []
    for key in result:
        xx.append(int(key))
        yy.append(result[key])
    plt.bar(xx, yy, color='blue', width=0.4)
    plt.xlabel("Identificador do nodo")
    plt.ylabel("Peso do nodo")
    plt.title("Peso dos nodos depois de o grafo ter uma componente conectada")
    plt.savefig(output_file)
    plt.clf()


if __name__ == "__main__":
    run_study(1, 100)
