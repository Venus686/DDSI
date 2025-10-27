/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Monitor;
import java.util.List;

/**
 *
 * @author leonl
 */
public class VistaMonitores {
    public void muestraNombre_dia_horaDeActividades(List<Object[]> actividades){
        for (Object[] a : actividades) {
                System.out.println(a[0] + "\t" + a[1] + a[2] );
        }
    }
}
