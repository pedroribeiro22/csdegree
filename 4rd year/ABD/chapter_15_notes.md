# Chapter 15 - Query Execution

Page 738

## Introduction to Physical-Query-Plan Operators

### Scanning Tables

    The most basic operation: read the entire contents of a relation R. There are two basic approaches to locating the tuples of a relation R:

    1. In many cases, the relation R is stored in an area of secondary memory,
       with its tuples arranged in blocks. The blocks containing the tuples of
       R are known to the system, and it is possible to get the blocks one by
       one. This operation is called *table-scan*.


    2. If there is an index on any attribute of R, we may be able to use this
       index to get all the tuples of R. For example, a sparse index on R can
       be used to lead us to all the blocks holding R, even if qwe don't know
       otherwise which blocks these are. This operation is called *index-scan*.

### Sorting While Scanning Tables

    There are various reasons to justify the need of sorting the result while
    we scan a table: for example, the query may include the `ORDER BY` clause.
    For another, some approaches to implementing relation-algebra operations
    require one or both arguments to be sorted relations.

    There are several ways that sort-scan can be implemented. If relation R
    must be sorted by attribute *a*, and there is a B-tree index on *a*, then a
    scan of the index allows us to produce R in the desired order. If R is
    small enough to fit in main memory, then we can retrieve its tuples using a
    table scan or index scan, and then we can retrieve is tuples using a table
    scan or index scan, and then use a main-memory sorting algorithm. If R is
    too large to fit in main memory, then we can use a multiway merge-sort.

### The Computation Model for Physical Operators

    Since choosing physical-plan operators wisely is an essential of a good
    query processor, we must be able to estimate the "cost" of each operator we
    use. We shall use the number of disk I/O's as our measure of cost for an
    operation. When comparing algorithms for the same operations, we shall make
    an assumption that may be surprising at first:

    * We assume that the arguments of any operator are found on disk, but the
    result of the operator is left in main memory.

    In many applications, the answer is not stored on disk at all, but printed
    or passed to some formatting program. The, he disk I/O cost of the output
    either is zero or dependes upon what some unknown application program does
    with the data. The cost of writing the answer does not influence our choice
    of algorithm for executing the operator.

### Parameters for Measuring Costs

    Estimates of cost are essential if the optimizer is to determine which of
    the many query plans is likely to execute fastest. We need a parameter to
    represent the portion of main memory that the operator uses, and we require
    other parameters to measure the size of its arguments. M will denote the
    number of main-memory buffers available to an execution of a particular
    operator. We shall make the simplifying assumption that data is accessed
    one block at a time from disk. In pratice, one of the techniques discussed
    before might be able to speed up the algorithm if we are able to read many
    blocks of relation at once, an they can be read from consecutive blocks on
    a track. There are 3 parameter familities, B, T and V:

    * When describing the size of a relation R, we most often are concerned
    with the number if blocks that are needed to hold all the tuples of R.
    This number of blocks will denoted B(R), or just B if we know that
    relation R is meant. Usually, we assume that R is *clustered*; that is,
    it is stored in B blocks or in approximately B blocks.

    * Sometimes, we also need to know the number of tuples in R, and we denote
    this quantity by T(R), or just T if R is understood. If we need the
    number of tuples of R that can fit in one block, we can use the ration
    T/B.

    * Finally, we shall sometimes want to refer to the number of distinct
    values that appear in a column of a relation, If R is a relation, and one
    of its attributes is *a*, then V(R, *a*) is the number of distinct values
    of the column for *a* in R.

### I/O Cost For Scan Operators

    If relation R is clustered, then the number of disks I/O's for the
    table-scan operator is approximately B. Likewise, if R fits in main-memory,
    then we can implement sort-scan by reading R into memory and performing an
    in-memory sort. again requiring only by B disks I/O's.

    However, if R is not clustered, then the number of required disks I/O's is
    generally much higher. If R is distributed among tuples of other relations,
    then a table-scan for R may require reading as many blocks as there are
    tuples of R; that is, the I/O cost is T. Similarly, if we want to sort R,
    but R fits in memory then T disk I/O's are what we need to get all of R
    into memory.

    Generally, an index on a relation R occupies many fewer than B(R) blocks.
    Therefore, a scan of the entire R, which takes as least B disk I/O's, will
    require significantly more I/O's than does examining the entire index.
    Thus, even though index-scan requires examining both the relation and its
    index, we continue to use B or T, respectively, to estimate the cost of
    accessing a clustered or unclustered relation in its entirety, using an
    index.

### Iterators for Implementation of Physical Operators

    1. Open()
    2. GetNext()
    3. Close()

## One Pass Algorithms


