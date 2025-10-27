/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.util.Scanner;

/**
 *
 * @author leonl
 */
public class VistaMenu {
    public static void MenuPrincipal(){
        System.out.println("************************");
        System.out.println("GESTION DEL GIMNASIO");
        System.out.println("************************");
        System.out.println("1. Socios");
        System.out.println("2. Monitores");
        System.out.println("3. Actividades");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
    }
    
    public static void MenuSocio(){
            System.out.println("************************");
            System.out.println("SOCIOS");
            System.out.println("************************");
            System.out.println("1. Alta de un socio");
            System.out.println("2. Salir");
            System.out.print("Elige una opcion: ");
    }
    
    public static void MenuMonitores(){
            System.out.println("************************");
            System.out.println("Monitores");
            System.out.println("************************");
            System.out.println("1. Actividades de un monitor");
            System.out.println("2. Salir");
            System.out.print("Elige una opcion: ");
    }
    
    public static void MenuActividades(){
            System.out.println("************************");
            System.out.println("Actividades");
            System.out.println("************************");
            System.out.println("1. Inscripciones");
            System.out.println("2. Salir");
            System.out.print("Elige una opcion: ");
    }
    
    
}
