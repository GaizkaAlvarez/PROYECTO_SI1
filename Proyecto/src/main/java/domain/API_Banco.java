package domain;

import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class API_Banco {
	
    private static API_Banco instancia;

    private API_Banco() {}

    public static API_Banco getInstance() {
        if (instancia == null) {
            instancia = new API_Banco();
        }
        return instancia;
    }

	
	public boolean pagar(int datos, Double precio) {
		Random random = new Random();
		if(random.nextInt(14)+1 == 15) return false;
		else return true;
	}
}
