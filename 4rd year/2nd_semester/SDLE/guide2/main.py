import networkx as nx
import random
import numpy as np

# Implements a graph edge
class Edge:

    def __init__(self, node1, node2):
        self.node1 = node1
        self.node2 = node2

    def toTuple(self):
        return (self.node1, self.node2)

# Implements a graph
# - Graph
# - Dictionary containing the weight of each of the nodes
class GraphAndWeights:

    def __init__(self, graph, node_weights):
        self.graph = graph
        print(self.graph)
        self.node_weights = node_weights

    def add_node(self, new_node):
        self.graph.add_node(new_node)
        self.node_weights[str(new_node)] = 1

    def add_edge(self, es, ee):
        self.graph.add_edge(es, ee)

    def increment_weight(self, node):
        previous_weight = self.node_weights[str[node]]
        new_weight = previous_weight + 1
        self.node_weights[str[node]] = new_weight
    
    def get_node_weights(self):
        return self.node_weights

    def get_graph(self):
        return self.graph

# Generates initial graph and weight structure for a graph with
# `node_count` initial nodes
def generate_graph_and_weights(node_count):
    graph = nx.Graph()
    node_weights = {}
    for i in range(0, node_count):
        graph.add_node(i)
        node_weights[str(i)] = 1
    return GraphAndWeights(graph, node_weights)

# Generates random edge given the `new_node` and the existing nodes
# and their weights
def generate_weighted_random_edge(full_graph, new_node):
    full_graph.add_node(new_node)
    chosen_node = random.choices(list(full_graph.node_weights.keys()), list(full_graph.node_weights.values()), k = 1)[0]
    e = Edge(int(chosen_node), new_node)
    return e

def run_task(starting_nodes):
    graph = generate_graph_and_weights(starting_nodes)
    result = {}
    iterations = 0
    while not nx.is_connected(graph.get_graph()):
        new_edge = generate_weighted_random_edge(graph, iterations + starting_nodes) 
        (es, ee) = new_edge.toTuple() 
        graph.add_edge(es, ee)
        iterations = iterations + 1
    result['iterations'] = iterations
    result['node_weights'] = graph.get_node_weights()
    return result

def run_study(runs, starting_nodes_range):
    result = {}
    for i in range(1, starting_nodes_range):
        result[str(i)] = run_task(i)
        print("Acabei")
    return result
            
if __name__ == "__main__":
    result = run_study(1, 10)
    print(result)