<h1 align="center">
  <a target="_blank" href="">
    <img src="https://updatedcode.files.wordpress.com/2015/06/grafo.png" alt="cpu" height="200px" width="300px">
  </a>
</h1>

# Graphs

This section will feature algorithms to perform operations involving
graphs as well as their explanation.


## Data Strucutures

In order to perform operations in graphs we have to define some data structures in order to compile our data in a sistematic way. I am going to present 2 different structures. We can also create another structures these are mearly the ones I came up with.

In my perspective we can both store a graph as matrix in which the ```i``` index represents the origin vertex and the ```j``` index represents the destination vertex. With this information we can create the following structure:

```
#define N 6
typedef int matrixGraph[N][N];
```
In case you haven't figured it out already this structure stores the weight of the connection between the two vertex. In case the graph is of a simpler form (doesn't store the weight of the connection but stores the information of wether it exists or not) it will store a ```1``` if the connection exists and a ```0``` otherwise.

We can take another route and use an array of linked lists as follows:

```
typedef struct edge {
    int dest;
    int cost;
    struct edge *next;
} *List;

typedef struct List listGraph[N];
```

## Elementar operations

Lets convert a graph presented in the first data structure in a graph presented in the second structure:

```
void matrixToList(matrixGraph gOrigin, listGraph gDestination) {
  int i, j;
  struct edge tmp;
  for(i = 0; i < N; i++) {
    gDestination[i] = NULL;
    for(j = N - 1; j >= 0; j--)
      if(gOrigin[i][j] != 0) {
        tmp = malloc(sizeof(struct edge));
        tmp->dest = j;
        tmp->cost = gOrigin[i][j];
        tmp->next = gDestination[i];
        gDestination[i] = tmp;
      }
  }
}
```

## Crossings
### Depth First
There are two major ways of crossing a graph a graph. The most reknown algorithms are the Depth First and the Breadth First crossings. These 2 have different applications.

Lets start with Depth First crossing. In this one we have to keep an array of visited vertex in order to avoid going in circles in the case of the existence of closed circuits in the graph.

```
int depthFirst(listGraph g, int origin) {
  int visited[N];
  for(int i = 0; i < N; i++) visited[i] = 0;
  return(DepthFirstRecursive(g, origin, visited));
}

int depthFirstRecursive(listGraph g, int origin, int visited[]) {
  int count = 1;
  struct edge tmp;
  visited[origin] = 1;
  for(tmp = g[origin]; tmp != NULL; tmp = tmp->next)
    if(!visited[tmp->dest])
      count += depthFirstRecursive(g, tmp->dest, visited);
  return count;
}
```

We may want to keep track of we're coming from. In that case we have to make some subtle tweaks in this code in order to just that. We can create an array in which we store the previous vertex (in the first vertex, the origin, we can store ```-1``` once we know that number will never ocure since the vertex identifier is positive).

```
int dfFathers(listGraph g, int origin, int fathers[]) {
  int visited[N];
  for(int i = 0; i < N; i++) {
    visited[i] = 0;
    fathers[i] = -2;
  }
  fathers[origin] = -1;
  return(dfFathersRec(g, origin, visited, fathers));
}

int dfFathersRec(listGraph g, int origin, int visited[], int fathers[]) {
  int count = 1;
  struct edge tmp;
  visited[origin] = 1;
  for(tmp = g[origin]; tmp != NULL; tmp = tmp->next) 
    if(!visited[tmp->dest]) {
      fathers[tmp->dest] = origin;
      count += dfFathersRec(g, tmp->dest, visited, fathers);
    }
    return count;
}
```

### Breadth First

In order to accomplish this crossing I have to introduce the ideia of a priority queue. If you are not familiar with these kind of structure I strongly advise you to get in touch with it.

```
int breadthFirst(listGraph g, int origin) {
  int visited[N];
  int fringe[N];
  int start, end;
  int v;
  int count = 0;
  struct edge tmp;

  // We have initialize the priority queue

  start = end = 0;
  fringe[end++] = origin;
  visited[origin] = 1;

  // The crossing itself

  while(start < end) {
    v = fringe[start++];
    count++;
    for(tmp = g[v]; tmp != NULL; tmp = tmp->next) {
      if(!visited[tmp->dest])
        fringe[end++] = tmp->dest;
    }
  }
  return count;
}
```
We are going to do something similar to what we have done in the Depth First crossing, we're going to keep track of were we are coming from.
We are also going to do something that we haven't done above and you're going to notice that we don't actually need the visited array.

```
int bfFathers(listGraph g, int origin, int fathers[]) {
  int fringe[N];
  int start, end, i, v;
  int count = 0;
  struct edge tmp;

  // Initialize the fathers array
  // Initialize the fringe

  for(i = 0; i < N; i++) fathers[i] = -2;
  start = end = 0;
  fringe[end++] = origin;
  fathers[origin] = -1;
  while(start < end) {
    v = fringe[start++];
    count++;
    for(tmp = g[v]; tmp != NULL; tmp = tmp->next)
      if(ant[tmp->dest] == -2) {
        ant[tmp->dest] = v;
        fringe[end++] = tmp->dest;
      }
  }
  return count;
} 
```
