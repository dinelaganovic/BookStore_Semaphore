package semaphoreproject;
public class Radnik implements Runnable
{
    private Traka traka;
    public Radnik(Traka traka) { 
                    this.traka = traka;
    }
    public void run() { 
        int i=0;
        while (true) { 
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            traka.Dodaj(1);
        }
    }

}
