/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Socio;
import java.util.List;

/**
 *
 * @author leonl
 */
public class VistaSocios {
    public void muestraNúmeroNumeroTelefonoCorreo(List<Socio> socios){
        for (Socio s : socios) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre()
                        + "\t" + s.getTelefono() + "\t" + s.getCorreo() );
            }
    }
}
