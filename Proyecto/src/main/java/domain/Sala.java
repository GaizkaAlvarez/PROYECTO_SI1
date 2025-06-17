package domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
@Table(name = "sala")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sala implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private String nombre;
	private int aforoMaximo;
	
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private LinkedList<Sesion> listaSesiones; //Maximo 15 sesiones por limite horario
	
	public Sala() {
		super();
	}
	
	public Sala(String nombreString, int aforoMaximo) {
		this.nombre = nombreString;
		this.aforoMaximo = aforoMaximo;
		listaSesiones = new LinkedList<Sesion>();
	}
	public LinkedList<Sesion> getListaSesiones() {
		return listaSesiones;
	}
	public void setListaSesiones(LinkedList<Sesion> listaSesiones) {
		this.listaSesiones = listaSesiones;
	}
	public String getNombreString() {
		return nombre;
	}
	public void setNombreString(String nombreString) {
		this.nombre = nombreString;
	}
	public int getAforoMaximo() {
		return aforoMaximo;
	}
	public void setAforoMaximo(int aforoMaximo) {
		this.aforoMaximo = aforoMaximo;
	}
	
	public void addSesion(Sesion sesion) {
		this.listaSesiones.add(sesion);
	}
	
	public int sizeListaSesiones() {
		return this.listaSesiones.size();
	}
	
	public boolean isSalaLlena(Date fecha) {
		int contador = 0;
		for (Sesion sesion : listaSesiones) {
			if (sesion.getFecha().equals(fecha)) {
				contador++;
			}
		}
		if (contador==15) return true;
		else return false;
	}
}
