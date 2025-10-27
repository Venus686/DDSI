/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Socio;
import Modelo.SocioDAO;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author leonl
 */
public class ControladorSocio {
    private final SessionFactory sessionFactory;
    private SocioDAO socioDAO = null;

    public ControladorSocio(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        socioDAO= new SocioDAO();
        menuSocio();
    }
    
    public static void InsercionSocio(SessionFactory sessionFactory, String id, String nom, String dni, String fechaNac, String tel, String correo, String fechaEnt, char cate) {
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();

        try {
            Socio s = sesion.find(Socio.class, id);
            if (s == null) {
                Query consulta = sesion.createNamedQuery("Socio.findByDni", Socio.class);
                consulta.setParameter("dni", dni);
                List<Socio> resultados = consulta.getResultList(); // el single resoult te meteria en el try and catch
                if (resultados.isEmpty()) {
                    Socio socioNuevo = new Socio(id, nom, dni, fechaNac, tel, correo, fechaEnt, cate);
                    sesion.persist(socioNuevo);
                } else {
                    System.out.println("Error. Ese DNI ya existe en la base de datos");
                }
            } else {
                System.out.println("Error. Ese id, ya existe en la base de datos");
            }
            tr.commit();
        } catch (Exception e) {
            System.out.println("Error." + e.getMessage()); // haría este catch porq es null
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }
    
    private void menuSocio() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            Vista.VistaMenu.MenuSocio();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("Fecha Nacimiento (dd-mm-yyyy): ");
                    String fechaNac = sc.nextLine();
                    System.out.print("Telefono: ");
                    String tel = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Fecha Entrada (dd-mm-yyyy): ");
                    String fechaEnt = sc.nextLine();
                    System.out.print("Categoria (A/B/C): ");
                    char cate = sc.nextLine().charAt(0);

                    SocioDAO.InsercionSocio(sessionFactory, id, nom, dni, fechaNac, tel, correo, fechaEnt, cate);
                    break;
                case 2:
                    System.out.println("Volviendo al menú principal...");
                    break;
            }
        } while (opcion != 2);
    }
}
