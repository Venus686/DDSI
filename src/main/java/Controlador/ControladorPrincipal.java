/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.Scanner;
import org.hibernate.SessionFactory;

/**
 *
 * @author leonl
 */
public class ControladorPrincipal {

    private final SessionFactory sessionFactory;

    public ControladorPrincipal(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        //Vista.VistaMenu.MenuPrincipal();
        menuPrincipal();
    }

    private void menuPrincipal() { // Suponemos que el programa se gestiona con este menú
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            Vista.VistaMenu.MenuPrincipal();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new ControladorSocio(sessionFactory);
                    break;
                case 2:
                    new ControladorMonitor(sessionFactory);
                    break;
                case 3:
                    new ControladorActividad(sessionFactory);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}
