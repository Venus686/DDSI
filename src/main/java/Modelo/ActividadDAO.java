/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Lucia Zamudio
 */
public class ActividadDAO {
    public static void InfoSociosInscritosActividadPorIDdeActividadHQL(SessionFactory sessionfactory, String idActividad) {
        Session sesion = sessionfactory.openSession();
        try {
            Query consulta = sesion.createQuery("FROM Actividad a WHERE a.idActividad=:idActividad", Actividad.class);
            consulta.setParameter("idActividad", idActividad);
            Actividad a = (Actividad) consulta.getSingleResult();

            List<Socio> socios = new ArrayList<>(a.getSocioSet());
            Vista.VistaSocios vs= new Vista.VistaSocios();
            vs.muestraNúmeroNumeroTelefonoCorreo(socios);
            
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }
}
