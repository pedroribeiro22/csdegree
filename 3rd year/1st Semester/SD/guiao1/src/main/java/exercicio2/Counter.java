package exercicio2;

public class Counter {

    public int counter;

    public Counter() {
        this.counter = 0;
    }

    // A solução para este exercício seria colocar aqui um `synchronized`, para que o objeto partilhado seja manipulado
    // de forma a manter a veracidade do resultado.
    public void increment() {
        this.counter++;
    }

    public int get() {
        return this.counter;
    }
}
