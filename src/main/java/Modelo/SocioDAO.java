/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author leonl
 */
public class SocioDAO {
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
                    tr.commit();
                } else {
                    System.out.println("Error. Ya existe un socio con DNI: "+ dni);
                }
            } else {
                System.out.println("Error. Ese id, ya existe en la base de datos");
            }   
        } catch (Exception e) {
            System.out.println("Error." + e.getMessage()); // haría este catch porq es null
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }
}
