package businessLogic;

import java.util.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

//import domain.Booking;
import domain.*;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
    @WebMethod
	public List<Sesion> getSesiones(String nombreActividad, int gradoExigencia);
    @WebMethod
	public int reservarSesion(int numSocio, int idSesion);
    @WebMethod
	public List<Factura> getFacturasNoPagadas(int numSocio);
    @WebMethod
	public boolean pagarFactura(String metodo, int numSocio, int codigoFactura);
    @WebMethod
	public boolean addActividad(String NombreActividad, int gradoExigencia, int precio);
    @WebMethod
	public List<Sesion> getSesionesDeSala(String nombreSala);
    @WebMethod
	public int addSesionSala(String nombreSala, String nombreActividad, int gradoExigencia, Date fecha, Date horaIni, Date horaFin);// 0 bien, -1 llena, -2 ocupada
    @WebMethod
	public boolean enviarFacturas();
    @WebMethod
	public void cancelarReserva(int idReserva, int numSocio);
    @WebMethod
	public List<Integer> getNumeroSocios();
    @WebMethod
	public boolean existeSala(String text);
    @WebMethod
	public boolean existeActividad(String text, int gradoExigencia);
    @WebMethod
	public boolean tieneReservaSocio(int numSocio, int idReserva);
    @WebMethod
	public List<Actividad> getActividades();
}
