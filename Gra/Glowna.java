/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gra;

import java.awt.EventQueue;

import Gra.Plansze.Plansza_podst;




/**
 *
 * @author s384080
 */
public class Glowna 
{
  

	public static void main(String args[])
    {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Szkielet frame = new Szkielet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
      
       //szkielet.getImg();
       
      // Plansza_podst plan = new Plansza_podst();
      // plan.getIm();
       
       
       
       
       
     
        
        
        
    }
    
   
    
    
}
