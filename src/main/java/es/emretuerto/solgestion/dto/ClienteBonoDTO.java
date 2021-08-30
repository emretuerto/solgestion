package es.emretuerto.solgestion.dto;

import org.springframework.stereotype.Component;

import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;

@Component
public class ClienteBonoDTO {

	
	private Cliente cliente;
	
	private Bono bono;

	public ClienteBonoDTO(Cliente cliente, Bono bono) {
		this.cliente = cliente;
		this.bono = bono;
	}

	public ClienteBonoDTO() {
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Bono getBono() {
		return bono;
	}

	public void setBono(Bono bono) {
		this.bono = bono;
	}
	
	
	
	
}
