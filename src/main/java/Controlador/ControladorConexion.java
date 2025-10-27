/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Config.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 *
 * @author leonl
 */
public class ControladorConexion {

    public ControladorConexion() {
        SessionFactory sessionFactory = conectarBD();
        new ControladorPrincipal(sessionFactory);
    }

    public static SessionFactory conectarBD() {
    SessionFactory sessionFactory = null;
        try {
            // Se obtiene la SessionFactory definida en HibernateUtil
            sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("Conexión Correcta con Hibernate");
        } catch (ExceptionInInitializerError e) {
            Throwable cause = e.getCause();
            System.out.println("Error en la conexión. Revise el fichero .cfg.xml: " + cause.getMessage());
        }
        return sessionFactory;
    }
}
