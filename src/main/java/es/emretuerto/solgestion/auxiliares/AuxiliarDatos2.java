package es.emretuerto.solgestion.auxiliares;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
public class AuxiliarDatos2 {


    @NotBlank
    @Pattern(regexp = "[0-9]{4}", message = "Introduzca un código de 4 dígitos numericos")
	private String dato2;
	
	

	public AuxiliarDatos2(String dato1, String dato2, String dato3) {

		this.dato2 = dato2;

	}

	public AuxiliarDatos2() {
	}

	public String getDato2() {
		return dato2;
	}

	public void setDato2(String dato2) {
		this.dato2 = dato2;
	}

	@Override
	public String toString() {
		return "AuxiliarDatos2 [dato2=" + dato2 + "]";
	}

	
}
