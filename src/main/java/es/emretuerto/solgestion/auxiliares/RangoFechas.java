package es.emretuerto.solgestion.auxiliares;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class RangoFechas {

	private LocalDate inicio;

	private LocalDate fin;

	public RangoFechas(LocalDate inicio, LocalDate fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	public RangoFechas() {
		
		this.inicio = LocalDate.now();
		this.fin = LocalDate.now();
		
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "RangoFechas [inicio=" + inicio + ", fin=" + fin + "]";
	}


	
	

}
