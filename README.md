# ThreeNetworks
Generate three different types of networks

The objective of this assignment is to find mathematical models that characterize real-world networks that can be used to generate new networks with
similar network characteristics. We will compare three models: (i) ER Graph Model, (ii) Small World Model and (iii) Preferential Attachment (BA model).
We will also compare these models to real world data from a collaboration network of scientific authors. All edges are assumed to be indirect.

• ER Random Network: Gn,m. Construct an undirected network with n = 5242 nodes and m = 14496 edges at random.

• Small-World Network. To generate this network we assume there exists 5242 nodes are arranged on a ring and each node is connected to its two adjacent neighbors i.e., node id 100 is connected to 101 and 99, leading to
5242 edges. We also connect each node to the neighbors of its neighbors i.e., node 100 is connected to nodes with identifiers, 102 and 98. This gives us another 5242 edges. Now we randomly select 4012 pairs of nodes not 
yet connected and add an edge between them. This will lead to a total of 14496 edges.

• Barabasi-Albert Preferential Attachment Network: In this model we start with 3 fully connected nodes. At each step we add new vertices one by
one. Each node can connect from 1 to 4 other nodes (number of edges is a random process). The choice of a node is proportional to the degree of
the nodes within the network. We will repeat this process for a total of 5242 nodes. However, we cannot control the total number of edges that
we will end up with in this model.
