/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra;

import java.awt.EventQueue;

/**
 *
 * @author s384080
 */
public class Glowna 
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new szkielet();
            }
        });
    }}
