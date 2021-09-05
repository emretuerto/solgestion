package es.emretuerto.solgestion.auxiliares;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class RangoFechas {

	private LocalDateTime inicio;

	private LocalDateTime fin;

	public RangoFechas(LocalDateTime inicio, LocalDateTime fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	public RangoFechas() {
		
		this.fin = LocalDateTime.now();
		
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "RangoFechas [inicio=" + inicio + ", fin=" + fin + "]";
	}


	
	

}
