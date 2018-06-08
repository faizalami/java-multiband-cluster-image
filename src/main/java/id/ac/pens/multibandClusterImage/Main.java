/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.pens.multibandClusterImage;

import com.github.underscore.$;
import com.github.underscore.Consumer;
import id.ac.pens.multibandClusterImage.view.LandingPage;

/**
 *
 * @author faizalami
 */
public class Main {
    public static void main(String[] args) {
//        $.each(asList(1, 2, 3), new Consumer<Integer>() {
//            public void accept(Integer item) {
//                System.out.println(item + ",");
//            }
//        });
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LandingPage().setVisible(true);
            }
        });
    }
}
