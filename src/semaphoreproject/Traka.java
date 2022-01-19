package semaphoreproject;

import java.awt.Color;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Traka implements KruzniBaffer {
    private static final int brojproizvoda= 20;
    public int[] traka;
    public JButton[] dugmici;
    public Semaphore mutex;
    private Semaphore proveramesta;
    private Semaphore proveraproizvoda;
    private int in, out;
    private int ukupanbrpr;
    
    Traka() {}
    
    public Traka(JButton[] button, JLabel labels) { 
        traka = new int[brojproizvoda];
        dugmici = button;
        for(int i=0; i<brojproizvoda; i++) {
            traka[i] = 0;
        }
        in = 0;
        out = 0;
        ukupanbrpr=0;
        mutex=new Semaphore(1);
        proveramesta = new Semaphore(1);
        proveraproizvoda = new Semaphore(0);
    }
    
    public void Stampaj()
    {
        for(int i=0;i<this.brojproizvoda;i++)
        {
            int text=traka[i];
            dugmici[i].setText(""+text);
        }
    }

    public void Dodaj(int proizvod) 
    { 	
        while(ukupanbrpr>=brojproizvoda-4)
        {
            try {
                    proveramesta.acquire();
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
        }
        try {
                mutex.acquire();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        for(int i=0;i<5;i++)
        {
        traka[in] = proizvod;
        dugmici[in].setBackground(Color.GREEN);
        dugmici[in].setOpaque(true);
        in = (in + 1) % brojproizvoda;
        ukupanbrpr++;
        }
        //JOptionPane.showMessageDialog(null, "Radnik je doneo 5 pa ima " + ukupanbrpr + " objekata u baferu");
        System.out.println("Radnik je doneo 5 pa ima " + ukupanbrpr + " objekata u baferu");        
        proveraproizvoda.release();
        Stampaj();
        mutex.release();
        
    }

    public void Ukloni() { 

        while(ukupanbrpr<3)
        {
        try {
                proveraproizvoda.acquire();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        }

        try {
                mutex.acquire();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        for(int i=0;i<3;i++)
        {
                traka[out]=0;
                dugmici[out].setBackground(Color.PINK);
                out = (out + 1) % brojproizvoda;
                ukupanbrpr--;
        }
        //JOptionPane.showMessageDialog(null, "Aparat uzeo 3 proizvoda pa ima " +ukupanbrpr+ " objekata u baferu"); 
        System.out.println("Štampač je uzeo 3 proizvoda pa ima " +ukupanbrpr+ " objekata u baferu");
        if(ukupanbrpr<=15)
        {
        proveramesta.release();
        }
        Stampaj();
        mutex.release();
    }

    public void Dodaj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
