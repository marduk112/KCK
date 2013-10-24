/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra;

import KCK.Gra.Szkielet;
import java.awt.EventQueue;





/**
 *
 * @author s384080
 */
public class Glowna 
{
  

	public static void main(String args[])
    {
		EventQueue.invokeLater(new Runnable() 
                {
                        @Override
			public void run() 
                        {
				try {
					Szkielet frame = new Szkielet();
					frame.setVisible(true);
				} catch (Exception e) 
                                {
				}
			}
		});
      
       //szkielet.getImg();
       
      // Plansza_podst plan = new Plansza_podst();
      // plan.getIm();
       
       
       
       
       
     
        
        
        
    }
    
   
    
    
}
