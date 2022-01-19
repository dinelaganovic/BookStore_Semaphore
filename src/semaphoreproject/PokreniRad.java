package semaphoreproject;
public class PokreniRad {
    
    public static void main(String[] args) throws Throwable { 
        IzgledStamparije proizvodfabrike = new IzgledStamparije();
        proizvodfabrike.setVisible(true);
        proizvodfabrike.t1.start();
        proizvodfabrike.t2.start();
    }
}
