/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ActividadDAO;
import java.util.Scanner;
import org.hibernate.SessionFactory;

/**
 *
 * @author Lucía Zamudio
 */
public class ControladorActividad {
    private final SessionFactory sessionFactory;
    private ActividadDAO actividadDAO = null;

    public ControladorActividad(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        actividadDAO= new ActividadDAO();
        menuActividad();
    }
    
     private void menuActividad() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            Vista.VistaMenu.MenuActividades();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el ID de la actividad: ");
                    String idActividad = sc.nextLine();
                    ActividadDAO.InfoSociosInscritosActividadPorIDdeActividadHQL(sessionFactory, idActividad);
                    break;
                case 2:
                    System.out.println("Volviendo al menú principal...");
                    break;
            }
        } while (opcion != 2);
    }
}
