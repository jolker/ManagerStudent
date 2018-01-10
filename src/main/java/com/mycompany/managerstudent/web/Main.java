/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managerstudent.web;

import java.io.File;

/**
 *
 * @author anhlnt
 */
public class Main {
    public static void main(String[] agrs) {
        
        
        JettyServer webserver = new JettyServer();
        String pidFile = System.getProperty("pidfile");

        try {
            if (pidFile != null) {
                new File(pidFile).deleteOnExit();
            }

            webserver.start();
        } catch (Exception e) {

            System.exit(3);
        }
    }
}
