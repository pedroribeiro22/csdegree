package src;

import java.util.List;
import java.util.ArrayList;

public class Stack {

   private List<String> stack;

   /**
    * Construtor vazio
    */
   public Stack() {
      this.stack = new ArrayList<>();
   }

   /**
    * Construtor parametrizado
    * @param stack Lista contendo os elementos da stack
    */
   public Stack(List<String> stack) {
      for(String s : stack)
         this.stack.add(s);
   }

   /**
    * Construtor parametrizado
    * @param stack Stack exemplo
    */
   public Stack(Stack stack) {
      this.stack = stack.getStack();
   }

   /**
    * Método que permite obter a stack
    * @return Stack
    */
   public List<String> getStack() {
      List<String> res = new ArrayList<>();
      stack.stream().forEach(e -> {res.add(e);});
      return res;
   }

   /**
    * Método que permite definir uma stack
    * @param stack Stack
    */
   public void setStack(List <String> stack) {
      this.stack = new ArrayList<>();
      for(String s : stack)
         this.stack.add(s);
   }

   /**
    * Método que permite comparar dois objetos do tipo `Stack`
    * @param o Objeto a comparar
    * @return True caso sejam iguais ou False caso contrário
    */
   public boolean equals(Object o) {
      if(o == this) return true;
      if(o == null || o.getClass() != this.getClass()) return false;
      Stack s = (Stack) o;
      return this.stack.equals(s.getStack());
   }

   /**
    * Método que fornece uma representação textual da classe `Stack`
    * @return Representação textual da classe `Stack`
    */
   public String toString() {
      return this.stack.toString();
   }


   /**
    * Devolve o elemento no topo da stack
    * Devolve uma String vazia se a stack estiver vazia
    */
   String top() {
      String s = "";
      if(!this.stack.isEmpty())
         s = this.stack.get(0);
      return s;
   }

   /**
    * @param s String a adicionar
    */
   void push(String s) {
      this.stack.add(0, s);
   }

   /**
    * Retira o elemento no topo da stack
    */
   void pop() {
      if(!this.stack.isEmpty()) this.stack.remove(0);
   }

   /**
    * @return 1 se a stack estiver vazia
    */
   boolean empty() {
      return this.stack.isEmpty();
   }

   /**
     * @return O tamanho da stack
    */
   int length() {
      return (int) stack.stream().count();
   }
}