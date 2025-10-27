/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.MonitorDAO;
import java.util.Scanner;
import org.hibernate.SessionFactory;

/**
 *
 * @author leonl
 */
public class ControladorMonitor {
    private final SessionFactory sessionFactory;
    private MonitorDAO monitorDAO = null;

    public ControladorMonitor(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        monitorDAO= new MonitorDAO();
        menuMonitor();
    }
    
     private void menuMonitor() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            Vista.VistaMenu.MenuMonitores();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el DNI del monitor: ");
                    String dni = sc.nextLine();
                    MonitorDAO.InfoActividadResponsableMonitorPorDNISQL(sessionFactory, dni);
                    break;
                case 2:
                    System.out.println("Volviendo al menú principal...");
                    break;
            }
        } while (opcion != 2);
    }
}
