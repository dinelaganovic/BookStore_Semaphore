package semaphoreproject;

public class Stampac implements Runnable
{
    private Traka traka;
    public Stampac(Traka t) { 
        this.traka = t;
    }
    public void run() { 
        while (true) { 
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            traka.Ukloni();
            }
        }
}
