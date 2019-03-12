import java.util.List;

public class Stack {

   private List<String> stack;

   public Stack() {
      this.stack = new ArrayList<>();
   }

   public Stack(List<String> stack) {
      // this.stack = stack; -> errado
      setStack(stack);
   }

   public Stack(Stack stack) {
      this.stack = stack.getStack();
   }

   public boolean equals(Object o) {
      if(o == this) return true;
      if(o == null || o.getClass() != this.getClass()) return false;
      Stack s = (Stack) o;
      return this.stack.equals(s.getStack());
   }

   public String toString() {
      return this.stack.toString();
   }

   public int hashCode() {
      return Objects.hash(super.hashCode(), getStack());
   }

   public void setStack(List <String> stack) {
      this.stack = new ArrayList<>();
      for(String s : stack)
         this.stack.add(s);
   }

   public List<String> getStack() {
       List<String> res = new ArrayList<>();
       stack.stream().forEach(e -> {res.add(e);});
       return res;
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
    * @param s String adicionar
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