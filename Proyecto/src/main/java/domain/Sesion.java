package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@Table(name = "sesion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sesion implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@XmlID
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer idSesion;
    private int cantidadParticipantes;
    private Date fecha;
    private Date horaInicio;
    private Date horaFinal;
    private Sala sala;
    private Actividad actividad;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<Socio> listaEspera = new LinkedList<>();
    @XmlIDREF
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<Reserva> listaReservas = new LinkedList<>();
    
	public Sesion() {
		super();
		//idSesion = id++;
	}

    public Sesion(Sala sala, Actividad actividad, Date fecha, Date horaInicio, Date horaFinal) {
//        this.idSesion = UUID.randomUUID().toString();
    	this.cantidadParticipantes = 0;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.sala = sala;
        this.actividad = actividad;
        this.listaEspera = new LinkedList<Socio>();
        this.listaReservas = new LinkedList<Reserva>();
        for (int i = 0; i < sala.getAforoMaximo(); i++) {//Crear todas las reservas de la sesion
			listaReservas.add(new Reserva(this));
		}
    }

	public Integer getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}

	public int getCantidadParticipantes() {
		return cantidadParticipantes;
	}

	public void setCantidadParticipantes(int cantidadParticipantes) {
		this.cantidadParticipantes = cantidadParticipantes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public List<Socio> getListaEspera() {
		return listaEspera;
	}

	public void setListaEspera(List<Socio> listaEspera) {
		this.listaEspera = listaEspera;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	    
		return "Sesion [SesionID= " + idSesion + ", cantidadParticipantes=" + cantidadParticipantes + ", fecha=" + dateFormat.format(fecha) + ", horaInicio="
				+ timeFormat.format(horaInicio) + ", horaFinal=" + timeFormat.format(horaFinal) + "]";
	}
	
	public void addListaDeEspera(Socio socio) {
		this.listaEspera.add(socio);
	}
	
	public Socio getAndRemoveFirstListaEspera() {
		return this.listaEspera.remove(0);
	}
	
	public boolean isListaEsperaEmpty() {
		return this.listaEspera.isEmpty();
	}
}
