import networkx as nx
import random
import json
import matplotlib.pyplot as plt


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


def generate_weighted_random_edge(full_graph):
    origin_node = random.sample(list(full_graph.node_weights.keys()), 1)[0]
    chosen_node = random.choices(list(full_graph.node_weights.keys()), list(
        full_graph.node_weights.values()), k=1)[0]
    return int(origin_node), int(chosen_node)


def generate_random_attached_graph(number_of_nodes):
    graph = nx.Graph()
    iterations = 0
    # Adiciona os nodos ao grafo
    for i in range(0, number_of_nodes):
        graph.add_node(i)
    # Só para de adicionar arestas quando o grafo tiver 4 componentes conectadas
    while not nx.is_connected(graph):
        nodes = random.sample(range(number_of_nodes), 2)
        edge = nodes[0], nodes[1]
        if not graph.has_edge(*edge):
            graph.add_edge(*edge)
            iterations = iterations + 1
    plus_iter = int(iterations / 2)
    for _ in range(0, plus_iter):
        nodes = random.sample(range(number_of_nodes), 2)
        edge = nodes[0], nodes[1]
        if not graph.add_edge(*edge):
            graph.add_edge(*edge)
            iterations = iterations + 1
    return graph, iterations


def generate_preferential_attachment_graph(number_of_nodes):
    graph = generate_graph_and_weights(number_of_nodes)
    iterations = 0
    while not nx.is_connected(graph.get_graph()):
        es, ee = generate_weighted_random_edge(graph)
        edge = es, ee
        if not graph.get_graph().has_edge(*edge):
            graph.add_edge(es, ee)
            iterations = iterations + 1
    plus_iter = int(iterations / 2)
    for _ in range(0, plus_iter):
        es, ee = generate_weighted_random_edge(graph)
        edge = es, ee
        if not graph.get_graph().has_edge(*edge):
            graph.add_edge(es, ee)
            iterations = iterations + 1
    return graph.get_graph(), iterations


def propagate_message(graph):
    message_source = random.sample(graph.nodes, 1)[0]
    received = set([message_source])
    sent = set([])
    messages_sent = 0
    while(len(received) < len(graph.nodes)):
        for r in received.copy():
            if(r not in sent):
                sent.add(r)
                for n in graph.neighbors(r):
                    received.add(n)
                    messages_sent += 1
    return messages_sent


def run_study(lower_bound, upper_bound, step, runs, random_or_preferential, messages_to_propagate):

    results = {}

    nodes = range(lower_bound, upper_bound, step)

    for i in nodes:

        message_count_results = []
        link_count_results = []
        diameter_results = []
        neighbor_results = []

        for _ in range(runs):
            if random_or_preferential == 0:
                graph, edges = generate_random_attached_graph(i)
            if random_or_preferential == 1:
                graph, edges = generate_preferential_attachment_graph(i)
            link_count_results.append(edges)
            neighbor_count_list = [len(list(graph.neighbors(node)))
                                   for node in graph.nodes]
            neighbor_results.append(
                sum(neighbor_count_list) / len(neighbor_count_list))
            diameter_results.append(
                nx.algorithms.distance_measures.diameter(graph))
            # Propagate messages
            for _ in range(messages_to_propagate):
                msgs_sent = propagate_message(graph)
                message_count_results.append(msgs_sent)

        neighbor_count_list = [len(list(graph.neighbors(r)))
                               for r in graph.nodes]
        results[i] = {
            'average_links': sum(link_count_results) / len(link_count_results),
            'average_diameter': sum(diameter_results) / len(diameter_results),
            'average_messages_sent': sum(message_count_results) / len(message_count_results),
            'average_neighbours': sum(neighbor_count_list) / len(neighbor_count_list)
        }
    return results


def export_results(results, lower_bound, upper_bound, step):
    with open('results/results.json', 'w') as f:
        json.dump(results, f)

    nodes = range(lower_bound, upper_bound, step)
    edgesArray = [n['average_links'] for n in results.values()]
    plt.xlabel("Number of Nodes")
    plt.ylabel("Average Number of Edges Inserted")
    plt.axis([min(nodes), max(nodes), min(edgesArray), max(edgesArray)])
    plt.plot(nodes, edgesArray)
    plt.savefig('results/edges.png')
    plt.clf()

    diameterArray = [n['average_diameter'] for n in results.values()]
    plt.xlabel("Number of Nodes")
    plt.ylabel("Average Diameter")
    plt.axis([min(nodes), max(nodes), min(diameterArray), max(diameterArray)])
    plt.plot(nodes, diameterArray)
    plt.savefig('results/diameter.png')
    plt.clf()

    neighborsArray = [n['average_neighbours'] for n in results.values()]
    plt.xlabel("Number of Nodes")
    plt.ylabel("Average Number of neighbors")
    plt.axis([min(nodes), max(nodes), min(neighborsArray), max(neighborsArray)])
    plt.plot(nodes, neighborsArray)
    plt.savefig('results/neighbors.png')
    plt.clf()

    avgMsgsArray = [n['average_messages_sent'] for n in results.values()]
    plt.xlabel("Number of Nodes")
    plt.ylabel("Average Number of messages sent")
    plt.axis([min(nodes), max(nodes), min(avgMsgsArray), max(avgMsgsArray)])
    plt.plot(nodes, avgMsgsArray)
    plt.savefig('results/msgs.png')
    plt.clf()


if __name__ == "__main__":

    results = run_study(1, 156, 5, 40, 0, 1000)
    export_results(results, 1, 156, 5)
