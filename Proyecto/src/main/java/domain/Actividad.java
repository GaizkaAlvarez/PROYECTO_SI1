package domain;

import java.io.Serializable;
import java.util.*;
import exceptions.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@Table(name = "actividad")
@XmlAccessorType(XmlAccessType.FIELD)
public class Actividad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private String nombre;
	@XmlID
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer gradoExigencia;
	private double precio;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private LinkedList<Sesion> listaSesiones;
	
	public Actividad() {
		super();
	}
	
	public Actividad(String name, int gradoExigencia, double precio) throws gradoExigenciaException{
		if (1 <= gradoExigencia && gradoExigencia <= 5) this.gradoExigencia = gradoExigencia;
		else throw new gradoExigenciaException("El grado de exigencia debe estar entre el 1 el 5");
		this.nombre = name;
		this.precio = precio;
		
		this.listaSesiones = new LinkedList<Sesion>();
	}
	public String getName() {
		return nombre;
	}

	public void setName(String name) {
		this.nombre = name;
	}
	public int getGradoExigencia() {
		return gradoExigencia;
	}
	public void setGradoExigencia(int gradoExigencia) {
		this.gradoExigencia = gradoExigencia;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public LinkedList<Sesion> getListaSesiones() {
		return listaSesiones;
	}
	public void setListaSesiones(LinkedList<Sesion> listaSesiones) {
		this.listaSesiones = listaSesiones;
	}
	
	public void addSesion(Sesion sesion) {
		this.listaSesiones.add(sesion);
	}
	
	@Override
	public String toString() {
		return "Actividad [nombre=" + nombre + ", gradoExigencia=" + gradoExigencia + ", precio=" + precio + "]";
	}
}
