# Study Notes

## Chapter 13: Secondary Storage Managment

### Storage types:

    1. Cache: Super high speed memory, usually within the CPU chip.
    2. Main Memory: system memory, which programs use to keep their execution
       context.
    3. Secondary Storage: nonvolatile storage that it's used to keep the
       computers' data.
    4. Tertiary Storage: stores enormous amount of data. Usually made up by
       large sums of magnetic disks. Access times may range from seconds to
       several minutes.

### Volatile and Nonvolatile Storage:

    The question of volatility is important, because one of the characteristic
    capabilities of a DBMS is the ability to retain its data even in the
    presence of erros such as power failures. A significant part of the
    complexity in a DBMS comes from the requirement that no change to the
    database can be considered final until it has migrated to nonvolatile,
    secondary storage.

### Disks (Magnetic Disks):

    The book has very detailed information on the way these disks work. The
    main take from this subsection regarding to DBMS is that accesses to these
    kinds of disks assumes the existence of a Disk Controller, which is a
    device that coordenates the access to the various disks that may build up
    to the database.

### Accelerating Access to Secondary Storage:

    Several accesses to disk may occur more frequently than the disk is able to
    handle, and in that case the *scheduling latency* becomes infinite.
    Therefore it is mandatory to come up with strategies to decrease the
    average time of a disk access. Let's point out some of the strategies:

        1. Place blocks that are accessed together on the same cylinder, so we
           can often avoid seek time, and possibly rotational latency as well.

        2. Divide the data among several smaller disks rather than one large
           one. Having more head assemblies that can go after blocks
           independently can increase the number of block accesses per unit
           time.

        3. "Mirror" a disk: making two or more or more copies of the data on
           different disks. In addition to saving the data in case one of the
           disks fails, this strategy, like dividing the data among several
           disks, lets us access several blocks at once.

        4. Use a disk-scheduling algorithm, either in the operating system, in
           the DBMS, or in the disk controller, to select the order in which
           several requested blocks will be read or writen.

        5. Prefetch blocks to main memory in anticipation of their later use.

### The I/O Model of Computation

    **Dominance of I/O cost**: The time taken to perform a disk access is much
    larger than the time likely to be used manipulating that data in main
    memory. Thus, the number of block accesses (Disk I/O's) is a good
    approximation to the time needed by the algorithm and should be minimized.

### Organizing Data by Cilinders

    Since seek time represents about half the time it takes to access a block,
    it makes sense to store data that is likely to be accessed together, such
    as relations, on a single cylinder, or on as many adjacent cylinders as are
    needed.

### Using Multiple Disks

    Instead of having the reading head of one disk that is locked in a certain
    region of the disk at all times, we have several heads of several disks
    which can be at the different regions. As long as the disk controller, bus,
    and main memory can handle *n* times the data-transfer rate, then *n* disks
    will have approximately the performance of one disk that operates *n* times
    as fast. If we have several disks, then the technique known as *stripping*
    will speed up access to large database objects - those that occupy a large
    number of blocks.

### Mirroring Disks

    The disks are said to be *mirrors* of each other (contain exactly the same
    data). The important take away is that data is able to survive the crash of
    several disks (all long as not all of them crash). If we have *n* disks,
    each holding the same data, then the rate at which we can read blocks goes
    up by a factor of *n*, since the disk controller can assign a read request
    to any of the *n* disks. In fact, the speedup could be even greater than
    *n*, if a clever controller chooses to read a block from the disk whose
    head is currently closest to that block. The write speeds, on the other
    hand, do not increase, since every change has to replicated accross all
    disk.

### Disk Scheduling and the Elevator Algorithm

### Prefetching and Large-Scale Buffering

## Disk Failures

    1. The most common form of failure is an *intermittent failure*, where an
       attempt to read or write a sector is unsuccessful, but with repeated
       tries we are able to read or write successfully.

    2. A more serious form of failures is one in which a bit or bits are
       permanently corrupted, and it becomes impossible to read a sector
       correctly no matter how many times we try. This form of error is called
       *media decay*.

    3. A related type of error is a *write failure*, where we attempt to write
       a sector, but we can neither write successfully nor can we retrieve the
       previously written sector. A possible cause is that there was a power
       outage during the writting of the sector.

    4. The most serious form of disk failure is a *disk crash*, where the
       entire disk becomes unreadable, suddenly and permanently.

### Intermittent Failures

    Go to page: 613
