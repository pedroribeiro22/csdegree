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

## Operating System Structures

### 2.1 System Calls

_*System calls*_ provide an interface to the services made available by an operating system. These calls are generally available as routines written in C and C++, although certain low-level tasks (for example, tasks where hardware must be accessed directly) may have to be written using assembly-language instructions.
As we've seen in your classes examples, even simple programs may make heavy use of the operating system. Frequently, systems execute thousands of system calls per second. Most programmers never see this level of detail, however. Note that - unless specified - the system-call names used throughout this text are generic examples. Each operating system has its own name for each system call.
For most programming languages, the run-time support system (a set of functions built into libraries included with a compiler) provides a _*system-call interface*_ that serves as the link to system calls made available by the operating system. The system-call interface intercepts function calls in the API and invokes the necessary system calls within the operating system. Typically, a number is associated with each system call, and the system-call interface maintains a table indexed according to these numbers. The system call interface then invokes the intended system call in the operating-system kernel and returns the status of the system call and any return values.
The caller need know nothing about how the system call is implemented or what it does during execution. Rather, the caller need only obey the API and understand what the operating system will do as a result of the execution of that system call. Thus, most of the details of the operating-system interface are hidden from the programmer by the API and are managed by the run-time support library.
System calls occur in different ways, depending on the computer in use. Often, more information is required than simply the identity of the desired system call.

### 2.2 Types of System Calls

System calls can be grouped roughly into six major categories:
* Process control
* File Manipulation
* Device Manipulation
* Information Maintenance
* Communications
* Protection

**Process Control**
* end, abort
* load, execute
* create process, terminate process
* get process attributes, set process attributes
* wait for time
* wait event, signal event
* allocate and free memory

**File Management**
* create file, delete file
* open, close
* read, write, reposition
* get file attributes, set file attributes

**Device Management**
* request device, release device
* read, write, reposition
* get device attributes, set device attributes
* logically attach or detach devices

**Information Maintenance**
* get time or date, set time or date
* get system data, set system data
* get process, file, or device attributes
* set process, file, or device attributes

**Communications**
* create, delete communication connection
* send, receive messages
* transfer status information
* attach or detach remote devices

Having created new jobs or processes, we may need to wait for them to finish their execution. We may want to wait for a certain amount of a time to pass (`wait_time()`). More probably, we will want to wait for a specific event to occur(`wait_event()`). The jobs or processes should the signal when that event has occurred (`signal_event()`).
Quite often, two or more processes may share data. To ensure the integrity of the data being shared, operating systems often provide system calls allowing a process to **lock** shared data. Then, no other processes can access the data until the lock is released. Typically, such system calls include `acquire_lock()` and `release_lock()`.

**File Management**
We first need to be able to `create()` and `delete()` files. Once the file is created, we need to `open()` it and to use it. We may also `read()`, `write()`, or `reposition()` (rewind or skip to the end of the file, for example). Finally, we need to `close()` the file, indicating that we're no longer using it.
We may need these same sets of operations for directories if we have a directory structure for organizing files in the file system. File attributes include the file name, file type, protection codes, accounting information, and so on. At least two system calls, `get_file_attributes()` and `set_file_attributes()`, are required for this function. Some operating systems provide many more calls, such as calls for file `move()` and `copy()`.

**Device Management**
A process may need several resources to execute - main memory, disk drives, access to files, and so on. If the resources are available, they can be granted, and control can be returned to the user process. Otherwise, the process will have to wait until sufficient resources are available.
A system with multiple users may require us to first `request()` a device, to ensure exclusive use of it. After we are finished with the device, we `release()` it. Once the device has been requested (and allocated to us), we can `read()`, `write()`, and possibly `reposition()` the device, just as we can with files. In fact, the similarity between I/O devices and files is so great that many operating systems, including UNIX, merge the two into a combined file - device structure. In this case, a set of system calls is used on both files and devices. Sometimes, I/O devices are identified by special name files, directory placement, or file attributes.
