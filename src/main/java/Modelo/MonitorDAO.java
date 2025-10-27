/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Lucía Zamudio
 */
public class MonitorDAO {
    public static void InfoActividadResponsableMonitorPorDNISQL(SessionFactory sessionFactory, String dni) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createNativeQuery("SELECT a.nombre, a.dia, a.hora FROM ACTIVIDAD a INNER JOIN MONITOR m  ON a.monitorResponsable = m.codMonitor WHERE m.dni =:dni", Object[].class);
            consulta.setParameter("dni", dni);
            List<Object[]> actividades = consulta.getResultList();
            Vista.VistaMonitores monitor= new Vista.VistaMonitores();
            monitor.muestraNombre_dia_horaDeActividades(actividades);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }
}
