import java.util.concurrent.locks.ReentrantLock;

public class MuseuController implements Museu {

    private static int max = 15;
    private static int bare_minimum = 10;
    private int portWaiting;
    private int englWaiting;
    private int polyWaiting;
    private int guidesWaiting;

    public MuseuController() {
        this.portWaiting = 0;
        this.englWaiting = 0;
        this.polyWaiting = 0;
        this.guidesWaiting = 0;
    }

    public synchronized void enterPT() {
        this.portWaiting++;
        if((this.portWaiting + this.polyWaiting >= bare_minimum) && this.guidesWaiting >= 1) this.notifyAll();
        while(this.portWaiting + this.polyWaiting < bare_minimum) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public synchronized void enterEN() {
        this.englWaiting++;
        if((this.englWaiting + this.polyWaiting >= bare_minimum) && this.guidesWaiting >= 1) this.notifyAll();
        while(this.englWaiting + this.polyWaiting < bare_minimum) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public synchronized void enterPoly() {
        this.polyWaiting++;
        if( ((this.polyWaiting + this.portWaiting >= bare_minimum ) ||
                (this.polyWaiting + this.englWaiting >= bare_minimum)) && this.guidesWaiting >= 1) {
            this.notifyAll();
        }
        while((this.englWaiting + this.polyWaiting < bare_minimum) && (this.portWaiting + this.polyWaiting < bare_minimum)) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public synchronized void enterGuide() {
        this.guidesWaiting++;
        int forPTvisit = this.polyWaiting + this.portWaiting;
        int forENvisit = this.polyWaiting + this.englWaiting;
        if( (forPTvisit >= 10) || (forENvisit >= 10)) {
            this.notifyAll();
            this.guidesWaiting--;
            if(forPTvisit >= 10) {
                if(this.portWaiting >= 15) this.portWaiting = 0;
                else {
                    this.polyWaiting = forPTvisit - this.portWaiting;
                    this.portWaiting = 0;
                }
            }
            if(forENvisit >= 10) {
                if(this.englWaiting >= 15) this.englWaiting = 0;
                else {
                    this.polyWaiting = forENvisit - this.englWaiting;
                    this.englWaiting = 0;
                }
            }
        }
        while((this.portWaiting + this.polyWaiting <= bare_minimum) && (this.englWaiting + this.polyWaiting <= bare_minimum)) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.portWaiting);
        s.append(this.englWaiting);
        s.append(this.polyWaiting);
        s.append(this.guidesWaiting);
        return s.toString();
    }
}