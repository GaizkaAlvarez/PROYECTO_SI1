package businessLogic;

import java.util.*;

import configuration.ConfigXML;
import dataAccess.*;
import domain.*;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	private DataAccess da;
	
	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    da=new DataAccess();
		     
		//dbManager.close();
		 
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		this.da=da;		
	}
	
	//Se asume que existe la actividad
    @WebMethod
	public List<Sesion> getSesiones(String nombreActividad, int gradoExigencia){
		da.openDb();
		List<Sesion> list = da.getSesiones(nombreActividad, gradoExigencia);
		da.closeDb();
		return list;
	}
	
	//Se asume que el socio y la sesion existen 
    @WebMethod
	public int reservarSesion(int numSocio, int idSesion) {
		da.openDb();
		int respuesta = da.reservarSesion(numSocio, idSesion);
		da.closeDb();
		return respuesta;
	}
    @WebMethod
	public List<Factura> getFacturasNoPagadas(int numSocio){
		da.openDb();
		List<Factura> list = da.getFacturasNoPagadas(numSocio);
		da.closeDb();
		return list;
	}
    @WebMethod
	public boolean pagarFactura(String metodo, int numSocio, int codigoFactura) {
		da.openDb();
		boolean respuesta = da.pagarFactura(metodo, numSocio, codigoFactura);
		da.closeDb();
		return respuesta;
	}
    @WebMethod
	public boolean addActividad(String NombreActividad, int gradoExigencia, int precio) {
		da.openDb();
		boolean respuesta = da.addActividad2(NombreActividad, gradoExigencia, precio);
		da.closeDb();
		return respuesta;
	}
    @WebMethod
	public List<Sesion> getSesionesDeSala(String nombreSala){
		da.openDb();
		List<Sesion> list = da.getSesionesDeSala(nombreSala);
		da.closeDb();
		return list;
	}
    @WebMethod
	public int addSesionSala(String nombreSala, String nombreActividad, int gradoExigencia, Date fecha, Date horaIni, Date horaFin) {// 0 bien, -1 llena, -2 ocupada
		da.openDb();
		int respuesta = da.addSesionSala2(nombreSala, nombreActividad, gradoExigencia, fecha, horaIni, horaFin);
		da.closeDb();
		return respuesta;
	}
    @WebMethod
	public boolean enviarFacturas() {
		da.openDb();
		boolean respuesta = da.enviarFacturas();
		da.closeDb();
		return respuesta;
	}
    @WebMethod
	public void cancelarReserva(int idReserva, int numSocio) {
		da.openDb();
		da.cancelarReserva(idReserva, numSocio);
		da.closeDb();
	}
    @WebMethod
	public List<Integer> getNumeroSocios() {
		da.openDb();
		List<Integer> list = da.getNumeroSocios();
		da.closeDb();
		return list;
	}
    @WebMethod
	public boolean existeSala(String text) {
		da.openDb();
		boolean respuesta = da.existeSala(text);
		da.closeDb();
		return respuesta;
	}
    @WebMethod
	public boolean existeActividad(String text, int gradoExigencia) {
		da.openDb();
		boolean respuesta = da.existeActividad(text, gradoExigencia);
		da.closeDb();
		return respuesta;
	}
    @WebMethod
	public boolean tieneReservaSocio(int numSocio, int idReserva) {
		da.openDb();
		boolean respuesta = da.tieneReservaSocio(numSocio, idReserva);
		da.closeDb();
		return respuesta;
	}
    @WebMethod
    public List<Actividad> getActividades(){
    	da.openDb();
    	List<Actividad> respuesta = da.getActividades();
		da.closeDb();
		return respuesta;
    }
}
