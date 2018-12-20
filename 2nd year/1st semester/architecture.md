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
