package Gra;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author s384080
 */
public class Glowna 
{
    private static String wybor="3 pietro";
    private static Szkielet szkielet;
    public static void main(String args[]) throws IOException
    {
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(Szkielet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }       
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    szkielet=new Szkielet();
                } catch (IOException ex) 
                {
                    Logger.getLogger(Glowna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });   
        while (true)
        {            
            switch (wybor)
            {
                case "1 pietro":
                    break;
                case "2 pietro":
                    break;
                case "3 pietro":
                    break;
                default:
                    szkielet.wypiszInfo.append("Źle wpisales polecenie");
            }
        }
    }    
}
