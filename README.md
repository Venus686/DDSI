# 🦋​🦄​ Hibernate Practica 0

> Proyecto de gestión de socios y actividades de un club deportivo usando **Java** y **Hibernate**.  
> Autor: **Lucía Zamudio**

---

## ✨ Descripción

Este proyecto permite gestionar un **club deportivo**, sus **socios**, **monitores** y **actividades**.  
Se realizan consultas usando:

- **HQL**  
- **SQL nativo**  
- **Consultas nombradas**  

Además, incluye operaciones **CRUD**, inscripción y baja de socios en actividades, y filtros por edad, categoría, cuota y día.

---

## 📋 Menú y Funcionalidades

| 🔢 Opción | ❓ Pregunta | 📝 Descripción |
|----------|------------|----------------|
| 1  | Información de los socios (HQL) | Muestra todos los campos de todos los socios usando HQL |
| 2  | Información de los socios (SQL) | Igual que la opción 1 usando SQL nativo |
| 3  | Información de los socios (Consulta nombrada) | Igual que las opciones 1 y 2 usando consulta nombrada |
| 4  | Nombre y teléfono de los socios | Recupera solo nombre y teléfono de los socios |
| 5  | Nombre y categoría de los socios | Muestra nombre y categoría de socios filtrando por categoría |
| 6  | Nombre de monitor por nick | Dado un nick, muestra el nombre del monitor correspondiente |
| 7  | Información de socio por nombre | Recupera información completa de un socio por nombre |
| 8  | Información de actividades por día y cuota | Muestra actividades de un día específico con cuota mayor a la indicada |
| 9  | Información de socios por categoría (HQL) | Muestra información de socios de una categoría usando HQL |
| 10 | Información de socios por categoría (SQL) | Muestra información de socios de una categoría usando SQL nativo |
| 11 | Inserción de socio | Inserta un socio verificando unicidad de ID y DNI |
| 12 | Borrado de socio por DNI | Elimina un socio según su DNI |
| 13 | Actividad de la que es responsable un monitor por DNI | Muestra actividades de las que un monitor es responsable |
| 14 | Actividades en las que está inscrito un socio por DNI | Muestra actividades en las que un socio está inscrito |
| 15 | Socios inscritos en una actividad por nombre | Lista socios inscritos en una actividad específica |
| 16 | Inscripción de un socio en una actividad | Inscribe un socio en una actividad (si no está inscrito) |
| 17 | Baja de un socio de una actividad | Da de baja a un socio de una actividad (si está inscrito) |
| 18 | Mostrar el horario de un monitor por DNI | Muestra horarios de actividades de un monitor |
| 19 | Mostrar la cuota que paga un socio | Lista la cuota de cada actividad de un socio |
| 20 | Mostrar los socios que sean mayores a una edad | Muestra socios mayores a la edad indicada |
| 0  | Salir | Termina la ejecución del programa |

---

## 🖥 Código Completo

```java
// ============================
// Practica0.java
// ============================

package Aplicacion;

import Config.HibernateUtil;
import Modelo.Actividad;
import Modelo.Monitor;
import Modelo.Socio;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Practica0 {

    public static SessionFactory conectarDB() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("Conexión Correcta con Hibernate");
        } catch (ExceptionInInitializerError e) {
            Throwable cause = e.getCause();
            System.out.println("Error en la conexión. Revise el fichero .cfg.xml: " + cause.getMessage());
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        SessionFactory sesion = Practica0.conectarDB();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Informacion de los socios(HQL)");
            System.out.println("2. Informacion de los socios(SQL)");
            System.out.println("3. Informacion de los socios(Consulta nombrada)");
            System.out.println("4. Nombre y telefono de los socios");
            System.out.println("5. Nombre y categoria de los socios");
            System.out.println("6. Nombre de monitor por nick");
            System.out.println("7. Informacion de socio por nombre");
            System.out.println("8. Informacion de actividades por dia y cuota");
            System.out.println("9. Informacion de socios por categoria(HQL)");
            System.out.println("10. Informacion de socios por categoria(SQL)");
            System.out.println("11. Inserción de socio");
            System.out.println("12. Borrado de socio por DNI");
            System.out.println("13. Información de la actividad de la que es responsable un monitor por DNI.");
            System.out.println("14. Información de las actividades en las que está inscrito un socio por DNI.");
            System.out.println("15. Información de los socios inscritos en una actividad por nombre de la actividad.");
            System.out.println("16. Inscripción de un socio en una actividad");
            System.out.println("17. Baja de un socio de una actividad");
            System.out.println("18. Mostrar el horario de un monitor por el DNI");
            System.out.println("19. Mostrar la cuota que paga un socio");
            System.out.println("20. Mostrar los socios que sean mayores a una edad.");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    ListarSociosHQL(sesion);
                    break;
                case 2:
                    ListarSociosSQL(sesion);
                    break;
                case 3:
                    ListarSocioC_Nombrada(sesion);
                    break;
                case 4:
                    NombreTelefonoSocioSQL(sesion);
                    break;
                case 5:
                    NombreCategoriaSocioHQL(sesion);
                    break;
                case 6:
                    System.out.println("Introduce un nick por teclado.");
                    String nick = sc.nextLine();
                    MonitorNick_HQL(sesion, nick);
                    break;
                case 7:
                    System.out.println("Introduce un nombre por teclado(ejemplo Iria Mosquera Gil).");
                    String nombre = sc.nextLine();
                    InfoSocioPorNombreSQL(sesion, nombre);
                    break;
                case 8:
                    System.out.println("Introduce un día(primera mayuscula): ");
                    String dia = sc.nextLine();
                    System.out.println("Introduce una cuota:(los Martes son 17 y 20 la cuota) ");
                    int cuota = sc.nextInt();
                    InfoDiaCuotaSQL(sesion, dia, cuota);
                    break;
                case 9:
                    System.out.println("Introduce la categoria: ");
                    char categoria = sc.next().charAt(0);
                    InfoDeSociosPorCategoriaHQL(sesion, categoria);
                    break;
                case 10:
                    System.out.println("Introduce la categoria: ");
                    char cat = sc.next().charAt(0);
                    InfoDeSociosPorCategoriaSQL(sesion, cat);
                    break;
                case 11:
                    System.out.println("Introduce el idSocio");
                    String id = sc.nextLine();
                    System.out.println("Introduce el nombre");
                    String nom = sc.nextLine();
                    System.out.println("Introduce el dni");
                    String dni = sc.nextLine();
                    System.out.println("Introduce la fecha de Nacimiento");
                    String fechaNac = sc.nextLine();
                    System.out.println("Introduce el telefono");
                    String tel = sc.nextLine();
                    System.out.println("Introduce el correo");
                    String correo = sc.nextLine();
                    System.out.println("Introduce la fecha de entrada");
                    String fechaEnt = sc.nextLine();
                    System.out.println("Introduce la categoria");
                    char cate = sc.next().charAt(0);
                    InsercionSocio(sesion, id, nom, dni, fechaNac, tel, correo, fechaEnt, cate);
                    break;
                case 12:
                    System.out.println("Introduce el dni");
                    String DNI = sc.nextLine();
                    BorradoSocioPorDNI(sesion, DNI);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 0);

    }

    // ============================
    // Métodos CRUD y consultas
    // ============================
    
    public static void ListarSociosHQL(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery("FROM Socio", Socio.class);
            List<Socio> socios = consulta.getResultList();
            for (Socio s : socios) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
        } catch (Exception e) {
            System.out.println("Error en la recuperacion. " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) sesion.close();
        }
    }

    public static void ListarSociosSQL(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createNativeQuery("SELECT * FROM SOCIO", Socio.class);
            List<Socio> socios = consulta.getResultList();
            for (Socio s : socios) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
        } catch (Exception e) {
            System.out.println("Error en la recuperacion. " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) sesion.close();
        }
    }

     public static void ListarSociosSQL(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        //Transaction tr= null;

        try {
            // tr= sesion.beginTransaction();
            Query consulta = sesion.createNativeQuery("SELECT * FROM SOCIO ", Socio.class);
            List<Socio> socios = consulta.getResultList();

            for (Socio s : socios) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
            //tr.commit();

        } catch (Exception e) {
            //tr.rollback();
            System.out.println("Error en la recuperacion. " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void ListarSocioC_Nombrada(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        //Transaction tr= null;

        try {
            // tr= sesion.beginTransaction();
            Query consulta = sesion.createNamedQuery("Socio.findAll", Socio.class);
            List<Socio> socios = consulta.getResultList();

            for (Socio s : socios) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
            // tr.commit();

        } catch (Exception e) {
            // tr.rollback();
            System.out.println("Error en la recuperacion. " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void NombreTelefonoSocioSQL(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createNativeQuery("Select s.nombre, s.telefono from SOCIO s", Object[].class);
            List<Object[]> socios = consulta.getResultList();
            for (Object[] s : socios) {
                System.out.println(s[0] + "\t" + s[1]);
            }
        } catch (Exception e) {
            System.out.println("Error en el rollback" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void NombreCategoriaSocioHQL(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery("Select s.nombre, s.categoria from Socio s", Object[].class);
            List<Object[]> socios = consulta.getResultList();
            System.out.println("Nombre" + "\t" + "Categoria");
            for (Object[] s : socios) {
                System.out.println(s[0] + "\t" + s[1]);
            }
        } catch (Exception e) {
            System.out.println("Error en el rollback" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void MonitorNick_HQL(SessionFactory sessionFactory, String nick) {
        Session sesion = sessionFactory.openSession();

        try {
            Query consulta = sesion.createQuery("FROM Monitor m WHERE m.nick = :nick", Monitor.class);
            consulta.setParameter("nick", nick);
            Monitor monitor = (Monitor) consulta.getSingleResult();
            System.out.println(monitor.getNombre());

        } catch (Exception e) {
            System.out.println("Error en la recuperacion. " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void InfoSocioPorNombreSQL(SessionFactory sessionFactory, String nombre) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createNativeQuery("SELECT * FROM SOCIO s WHERE s.nombre = :nombre", Socio.class);
            consulta.setParameter("nombre", nombre);
            List<Socio> socios = consulta.getResultList();
            for (Socio s : socios) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }

    }

    public static void InfoDiaCuotaSQL(SessionFactory sessionFactory, String dia, int cuota) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createNativeQuery("SELECT * FROM ACTIVIDAD a where a.dia = :dia and a.precioBaseMes > :cuota", Actividad.class);
            consulta.setParameter("dia", dia);
            consulta.setParameter("cuota", cuota);
            List<Actividad> actividad = consulta.getResultList();
            for (Actividad a : actividad) {
                System.out.println(a.getIdActividad() + "\t" + a.getNombre() + "\t" + a.getDia()
                        + "\t" + a.getHora() + "\t" + a.getPrecioBaseMes());
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void InfoDeSociosPorCategoriaHQL(SessionFactory sessionFactory, char categoria) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery("FROM Socio s WHERE s.categoria = :categoria", Socio.class);
            consulta.setParameter("categoria", categoria);
            List<Socio> socio = consulta.getResultList();
            for (Socio s : socio) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
        } catch (Exception e) {
            System.out.println("Error." + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void InfoDeSociosPorCategoriaSQL(SessionFactory sessionFactory, char categoria) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createNativeQuery("SELECT * FROM SOCIO s WHERE s.categoria = :categoria", Socio.class);
            consulta.setParameter("categoria", categoria);
            List<Socio> socio = consulta.getResultList();
            for (Socio s : socio) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
        } catch (Exception e) {
            System.out.println("Error." + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
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

    public static boolean existeSocioPorDNI(Session sesion, String dni) {
        try {
            Query consulta = sesion.createNamedQuery("Socio.findByDni", Socio.class);
            consulta.setParameter("dni", dni);
            consulta.getSingleResult();
            return true;
        } catch (Exception e) {
            return false; // si fuese null iria al catch
        }
    }

    // MISMO METODO AL ANTERIOR PERO USANDO LA FUNCIÓN existeSocioPorDNI
    /*public static void InsercionSocio(SessionFactory sessionFactory, String id, String nom, String dni, String fechaNac, String tel, String correo, String fechaEnt, char cate) {
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();

        try {
            Socio s = sesion.find(Socio.class, id);
            if (s == null) {
                if (!existeSocioPorDNI(sesion, dni)) {
                    Socio socioNuevo = new Socio(id, nom, dni, fechaNac, tel, correo, fechaEnt, cate);
                    sesion.persist(socioNuevo);
                } else {
                    System.out.println("Error. Ese DNI ya existe en la base de datos");
                }
            } else {
                System.out.println("Error. Ese id ya existe en la base de datos");
            }

            tr.commit();
        } catch (Exception e) {
            System.out.println("Error." + e.getMessage()); // haría este catch porq es null
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }*/
    public static void BorradoSocioPorDNI(SessionFactory sessionFactory, String dni) {
        Session sesion = sessionFactory.openSession();
        Transaction tr = null;

        try {
            tr = sesion.beginTransaction();
            if (existeSocioPorDNI(sesion, dni)) {
                Query consulta = sesion.createNamedQuery("Socio.findByDni", Socio.class);
                consulta.setParameter("dni", dni);
                Socio s = (Socio) consulta.getSingleResult();
                sesion.remove(s);
            } else {
                System.out.println("No se ha encontrado el DNI");
            }
            tr.commit();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void InfoActividadResponsableMonitorPorDNISQL(SessionFactory sessionFactory, String dni) {
        Session sesion = sessionFactory.openSession();
        // Transaction tr=null;
        try {
            // tr=sesion.beginTransaction();
            Query consulta = sesion.createNativeQuery("SELECT a.* FROM ACTIVIDAD a INNER JOIN MONITOR m  ON a.monitorResponsable = m.codMonitor WHERE m.dni =:dni", Object[].class);
            consulta.setParameter("dni", dni);
            List<Object[]> actividades = consulta.getResultList();
            for (Object[] a : actividades) {
                System.out.println(a[0] + "/t" + a[1] + a[2] + "/t" + a[3] + "/t" + a[4] + "/t" + a[5] + "/t" + a[6]);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void InfoActividadInscritoSocioPorDNIHQL(SessionFactory sessionFactory, String dni) {
        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery("FROM Socio s WHERE s.dni=:dni", Socio.class);
            consulta.setParameter("dni", dni);
            Socio s = (Socio) consulta.getSingleResult();

            System.out.println(s.getNombre() + " :");
            Set<Actividad> actividades = s.getActividadSet();
            for (Actividad a : actividades) {
                System.out.println(a.getIdActividad() + "\t" + a.getNombre() + "\t" + a.getDia() + "\t" + a.getDia() + "\t" + a.getDescripcion() + "\t" + a.getDescripcion() + "\t" + a.getPrecioBaseMes() + "\t" + a.getMonitorResponsable());
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }

        }
    }

    public static void InfoSociosInscritosActividadPorNombredeActividadHQL(SessionFactory sessionfactory, String nomActividad) {
        Session sesion = sessionfactory.openSession();
        try {
            Query consulta = sesion.createQuery("FROM Actividad a WHERE a.nombre=:nomActividad", Actividad.class);
            consulta.setParameter("nomActividad", nomActividad);
            Actividad a = (Actividad) consulta.getSingleResult();

            for (Socio s : a.getSocioSet()) {
                System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getFechaNacimiento()
                        + "\t" + s.getTelefono() + "\t" + s.getDni() + "\t" + s.getFechaEntrada() + "\t"
                        + s.getCorreo() + "\t" + s.getCategoria());
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void InscripcionSocioenActividad(SessionFactory sessionfactory, String codSocio, String codActividad) {
        Session sesion = sessionfactory.openSession();
        Transaction tr = null;
        try {
            tr = sesion.beginTransaction();

            Actividad a = sesion.find(Actividad.class, codActividad);
            Socio s = sesion.find(Socio.class, codSocio);
            if (s == null || a == null) {
                System.out.println("Error. NO existe el socio o la actividad");
            }

            if (s.getActividadSet().contains(a)) {
                System.out.println("El socio ya está inscrito en la actividad.");
            } else {
                a.altaSocio(s);
                sesion.persist(a);
            }
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            System.out.println("Error en la recuperación" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void BajaSocioenActividad(SessionFactory sessionfactory, String codSocio, String codActividad) {
        Session sesion = sessionfactory.openSession();
        Transaction tr = null;
        try {
            tr = sesion.beginTransaction();

            Actividad a = sesion.find(Actividad.class, codActividad);
            Socio s = sesion.find(Socio.class, codSocio);
            if (s == null || a == null) {
                System.out.println("Error. NO existe el socio o la actividad");
            }

            if (s.getActividadSet().contains(a)) {
                a.bajaSocio(s);
                sesion.persist(a);
            } else {
                System.out.println("Error. Esa actividad no la realiza ese socio");
            }
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            System.out.println("Error en la recuperación" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void MostrarHorarioDeMonitorPorDNI(SessionFactory sessionfactory, String dni) {
        Session sesion = sessionfactory.openSession();
        //Transaction tr= null;
        try {
            //tr= sesion.beginTransaction();
            Query consulta = sesion.createNativeQuery("SELECT a.dia, a.hora FROM ACTIVIDAD a INNER JOIN MONITOR m ON a.monitorResponsable = m.codMonitor WHERE m.dni=:dni", Object[].class);
            consulta.setParameter("dni", dni);
            List<Object[]> actividades = consulta.getResultList();
            for (Object[] a : actividades) {
                System.out.println("Dia: " + a[0] + "  " + "Hora: " + a[1]);
            }
            //tr.commit();
        } catch (Exception e) {
            //tr.rollback();
            System.out.println("Error en la recuperacion" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void MostrarCuotaSocioDeActividades(SessionFactory sessionfactory, String codSocio) {
        Session sesion = sessionfactory.openSession();
        //Transaction tr= null;
        try {
            //tr= sesion.beginTransaction();
            Socio s = sesion.find(Socio.class, codSocio);
            Set<Actividad> actividades = s.getActividadSet();
            for (Actividad a : actividades) {
                System.out.println(a.getIdActividad() + "  Cuota: " + a.getPrecioBaseMes());
            }
            //tr.commit();
        } catch (Exception e) {
            //tr.rollback();
            System.out.println("Error en la recuperacion" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static void MostrarSociosMayorDeEdad(SessionFactory sessionfactory, int edad) {
        Session sesion = sessionfactory.openSession();
        //Transaction tr= null;
        try {
            //tr= sesion.beginTransaction();
            Query consulta = sesion.createQuery("FROM Socio", Socio.class);
            List<Socio> socios = consulta.getResultList();

            int anioActual = LocalDate.now().getYear();
            System.out.println("Socios mayores de " + edad + " anios:");

            for (Socio s : socios) {
                String fechaNac = s.getFechaNacimiento();
                int anioNacimiento = Integer.parseInt(fechaNac.substring(6, 10));
                int edadSocio = anioActual - anioNacimiento;

                if (edadSocio > edad) {
                    System.out.println("- " + s.getNombre() + " (DNI: " + s.getDni() + ") - Edad: " + edadSocio);
                }
            }
            //tr.commit();
        } catch (Exception e) {
            //tr.rollback();
            System.out.println("Error en la recuperacion" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

}
}
