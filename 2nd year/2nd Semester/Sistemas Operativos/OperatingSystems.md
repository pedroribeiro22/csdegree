<h1 align="center">
  <a target="_blank" href="">
    <img src="https://image.flaticon.com/icons/png/512/39/39385.png" alt="cpu" height="200px" width="300px">
  </a>
</h1>


### 1.1  What Operating Systems Do

A computer system can be divided roughly into 4 components: the *hardware*, the *operating system*, the *application programs* and the *users*.

The *hardware* (the central processing unit, the memory and the input/output devices) - provides the basic computing resources for the system.

We can view a computer system as consisting of hardware, software and data. The operating system provides the means for proper use of these resources in the operation of the computer system. An operating system is similar to a *government*. Like a government, it performs no useful function by itself. It simply provides an *environment* within which other programs can do useful work.

An *operating system* can be viewed as a **resource allocator**. A computer system has many resources that may be required to solve a problem: CPU time, memory space, file-storage space, I/O devices, and so on. The operating system acts as the manager of the resources, the operating system must decide how to allocate them to specific programs and users so that it can operate the computer system efficiently and fairly. An operating system is also a **control program** which manages the execution of user programs to prevent errors and improper use of the computer. 

**Side note: Moore's Law**: first announced in the 1960s, this law predicted that the number of transistors on an integrated circuit would double every eighteen months, and that prediction has held true.

A more common definition (of an *operating system*), and that we usually follow, is that the operating system is the one program running at all times on the computer - usually called the **kernel**. (Along with *kernel*, there are two other types of programs: *system programs*, which are associated with the operating system but are not necessarily part of the kernel, and application programs, which include all programs not associated with the operation of the system.)

### 1.2  Computer-System Organization

General-purpose computers run most of their programs from rewritable memory, called main memory (also called **random access memory**, or **RAM**). Main memory commonly is implemented in a semiconductor technology called **dynamic random-access memory** (**DRAM**).

Because **ROM** cannot be changed, only static programs, such as the bootstrap program described earlier, are stored here.

### 1.4 Operating-System structure

An operating system provides the environment within which programs are executed. Internally, operating systems vary greatly in their makeup, since they are organized along many different lines.

The idea is as follows: The operating system keeps several jobs in memory simultaneously. Since, in general, main memory is too small to accommodate all jobs, the jobs are kept initially on the disk in the **job pool**. This pool consists of all processes residing on disk awaiting allocation of main memory.

The operating system picks and begins to execute one of the jobs in memory. Eventually, the job may have to wait for some task, such as an I/O operation, to complete. In a non-multiprogrammed system, the CPU would sit idle. In a multiprogrammed system, the operating system simply switches to, and executes, another job. When that job has to wait, the CPU switches to another job, and so on. Eventually, the first job finishes waiting and gets the CPU back. As long as at least one job needs to execute, the CPU is never idle.

