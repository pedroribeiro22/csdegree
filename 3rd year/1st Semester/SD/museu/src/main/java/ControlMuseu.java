public class ControlMuseu implements Museu {

    private static int min = 10;
    private static int max = 15;
    private int waitingPT = 0;
    private int waitingEN = 0;
    private int waitingPoly = 0;
    private int waitingGuide = 0;
    private int inPT = 0;
    private int inEN = 0;
    private int inPoly = 0;

    public synchronized void enterPT() {
        this.waitingPT++;
        while((waitingPT + waitingPoly < min && inPT == 0) || this.waitingGuide == 0 || (this.inPT + this.inPoly >= max)) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        this.waitingPT--;
        this.inPT++;
        this.notifyAll();
    }

    public synchronized void enterEN() {
        this.waitingEN++;
        while((waitingEN + waitingPoly < min && inEN == 0) || this.waitingGuide == 0 || (this.inEN + this.inPoly >= max)) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        this.waitingEN--;
        this.inEN++;
        this.notifyAll();
    }

    public synchronized void enterPoly() {
        this.waitingPoly++;
        while(((waitingPT + waitingPoly < min && inPT == 0) && (waitingEN + waitingPoly < min && inEN == 0)) ||
                (waitingGuide == 0) || (inPT + inPoly >= max) || (inEN + inPoly >= max)) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        waitingPoly--;
        this.inPoly++;
        this.notifyAll();
    }

    public synchronized void enterGuide() {
        this.waitingGuide++;
        this.notifyAll();
        while((waitingPT + waitingPoly < min) && (waitingEN + waitingPoly <min)) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        this.waitingGuide--;
        this.notifyAll();
    }
}
