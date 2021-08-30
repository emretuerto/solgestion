package es.emretuerto.solgestion.auxiliares;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
public class AuxiliarDatos {

	@NotBlank
	@Pattern(regexp = "[0-9]{1,20}", message = "Introduzca un código numerico")
	private String dato1;
	
	
	private String dato2;
	
	
	private String dato3;

	public AuxiliarDatos(String dato1, String dato2, String dato3) {
		this.dato1 = dato1;
		this.dato2 = dato2;
		this.dato3 = dato3;
	}

	public AuxiliarDatos() {
	}

	public String getDato1() {
		return dato1;
	}

	public void setDato1(String dato1) {
		this.dato1 = dato1;
	}

	public String getDato2() {
		return dato2;
	}

	public void setDato2(String dato2) {
		this.dato2 = dato2;
	}

	public String getDato3() {
		return dato3;
	}

	public void setDato3(String dato3) {
		this.dato3 = dato3;
	}

	@Override
	public String toString() {
		return "auxiliarDatos [dato1=" + dato1 + ", dato2=" + dato2 + ", dato3=" + dato3 + "]";
	}

}
