package es.emretuerto.solgestion.auxiliares;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
public class AuxiliarDatos {

	@NotBlank
	@Pattern(regexp = "[0-9]{1,20}", message = "Introduzca un código numerico")
	private String dato1;

	public AuxiliarDatos(String dato1, String dato2, String dato3) {
		this.dato1 = dato1;

	}

	public AuxiliarDatos(
			@NotBlank @Pattern(regexp = "[0-9]{1,20}", message = "Introduzca un código numerico") String dato1) {
		this.dato1 = dato1;
	}

	public AuxiliarDatos() {
	}

	public String getDato1() {
		return dato1;
	}

	public void setDato1(String dato1) {
		this.dato1 = dato1;
	}

	@Override
	public String toString() {
		return "AuxiliarDatos [dato1=" + dato1 + "]";
	};

	
	
	
}
