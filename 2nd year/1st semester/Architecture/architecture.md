<h1 align="center">
  <a target="_blank" href="">
    <img src="https://img.icons8.com/ios/1600/smartphone-cpu.png" alt="cpu" height="200px" width="200px">
  </a>
</h1>

# Purpose
I'm mainly writing these notes as a way of studying and making things a bit clearer in my mind. At the same time I hope that these notes help doing the same in other peoples' minds.

# Notes

## Pipeline 

At the processor level the execution of an instruction goes through several stages:

1. **Reading (Fetch)**
2. **Decoding / Reading of operands**
3. **Execution (ALU)**
4. **Writing of results**

Pipeline consists of in dividing instructions in simpler chunks in order to be able to start a new instruction in every clock cycle as well as finishing the execution of one instruction in every clock cycle.

Let's agree that we're calling CPI to the number of clock cycles that are necessary to complete the full execution of one instruction, in average.

In what concerns to performance this mechanism represents a significant performance boost even though the CPI tends to remain the same. So, you may be asking what am I getting with pipeline then? Well, let me explain:

* As the chunks of instructions are way simpler the CPU is able to complete their execution in a shorter period of time, therefore the period of a clock cycle can be significantly smaller.

On the other hand, the clock cycle period is limited by the slowest stage of the pipeline. It has also been shown that some stages of the pipeline remain inactive for long periods of time. As you may have already figured the main challenge with this strategy is trying to balance the stages in wich the instructions are divided in order to capitalize as much as possible, timewise.

The pipeline concept also introduces a very important concept in computational performance: Instruction Level Paralelism.

Pipeline also raises a very important question: data dependencies.

To better understand this concept pay attention to the following Assembly code:

```
I1 : movl $10, %eax
I2 : addl %ebx, %eax
I3 : movl $20, %ebx
I4 : movl $20, %edx
```
If you pay attention you'll notice right away that we're dealing with a write after read dependency. In other words: for the CPU to execute I2 it has to make sure that I1 is finished once ``` addl %ebx, %eax``` sums the value of both registers and stores in the %eax register. It's easy to understand that if at the moment of the execution of the second instruction the first hasn't finished yet, we're going to have a problem. It gets pretty clear that the compiler has to come up with some clever mechanism to avoid misrunning our program. 

The strategy to correct this potencial program is inserting ```nop``` instructions that simply let time pass by without introducing any actual instruction to the pipeline. This way as the time goes by the preview instruction is completed and the program is able to go on and prevent potential errors. The strategy I have just refered to is commonly refered as [Stalling](https://en.wikipedia.org/wiki/Pipeline_stall).

## Multiple Issue

An approach that is both based and complementary to pipeline is multiple issue which basically consists of having several pipelines. It allows for the simultaneous execution of several instructions. As you may have already predicted at this point we can now potentially achieve a CPI that is smaller than 1.

Let's take the example a CPU with 4 functional unities, in which each one has a 5 stage pipeline. It's pretty straight forward to conclude that there can be 20 simultaneous instructions being executed. The most recent processors usually try to execute 3 to 6 instructions in each cycle.

This method has some limitations though:

* There can be both data and control dependencies.
* There can also be hardware limitations: number and type of functional unities, latency of different instructions and also delays in the access to memory.

Now, another problem arises: how does the CPU choose which instructions are ment to be executed at the same time?

There are two different ways to schedule instructions: Static issue and  Dynamic Issue.

When we're talking about Static Issue we're focusing ourselves on a compiler level, once  it is the compiler that gathers instructions in a sort of package that contains the instructions to be executed in each clock cycle. The compiler is responsible to ensure that there are dependencies inside this "package", or if there are: to solve them. It also to make sure that these instructions can be executed in the functional unities that are available.

## Super Escalarity 

There are two major ways of organising this:

* In-Order Scheduling

The instructions are selected respecting the order in which they appear in the program, respecting the availability of the operands and the appropriate functional unities.

Let's consider the following example:

Assuming that we have two pipes (1 and 0). Pipe 1 is capable of performing arithmetical and logical operations as well as branching operations while pipe 0 is only capable of loading and storing operations. Now, consider the following Assembly code:

```
lw $t0, 20($2)
addu $t1, $t0, $t2
sub $s4, $s4, $t3
addi $t5, $s4, 20
```
How do you think the CPU will spread the tasks?

| CC  | Pipe 0 (ALU/Branch)| Pipe 1 (LS)|
|-----|:-------------:|-----------:|
| 1   |  ```nop``` | ```lw $t0, 20($2)``` |
| 2   |  ```addu $t1, $t0, $t2```   |   ```nop``` |
| 3   | ```sub $s4, $s4, $t3``` | ```nop``` |
| 4   | ```addi $t5, $s4, 20``` |   ```nop```|
| CPI   | 4 / 4 = 1|

* Out-of-Order scheduling

The instructions are reorganizing by the CPU in order to maximize CPI and still garantee the correct execution of the program.

Now, consider the same code and repeat the exercise:

| CC  | Pipe 0 (ALU/Branch)| Pipe 1 (LS)|
|-----|:-------------:|-----------:|
| 1   |  ```sub $s4, $s4, $t3``` | ```lw $t0, 20($2)``` |
| 2   |  ```addu $t1, $t0, $t2```   |   ```nop``` |
| 3   |  ```addi $t5, $s4, 20```| ```nop``` |
| CPI   | 3 / 4 = 0.75|

## Vectorial Processing 

When we're talking about Scalar Processing it's fairly simple to understand that each instruction processes a single element of the data structure.

Now, when we consider Vectorial Processing it's a whole different scene. Each instruction processes ```n``` elements of the data structure.

Lets take a look at the benefits of this processing method:

* The number of instructions tends to be reduced by a fairly large amount due to fact that each instructions now takes care of ```n``` elements of the data structure.

* The number of clock cycles per instruction usually increases because each functional unity performs ```n``` parallel operations which contributs to mantain the amount of clock cycles spent to conclude a single instruction.

* The amount of data that has to be transfered per time unity also increases and as we already know, getting data from memory sucks up a lot of time.

We're now looking at a process that deals with single instructions. On the other hand each instruction usues multiple data.