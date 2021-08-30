package es.emretuerto.solgestion.validaciones;



import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.servicios.ClienteServicioInterface;

@Component
public class ClienteValidator implements Validator{


	
	@Autowired
	ClienteServicioInterface clienteServicio;
	
	final Logger LOG = Logger.getLogger("ClienteValidator.class");
	
	@Override
	public boolean supports(Class<?> clazz) {

		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	
		LOG.info("HACE ALGO AQUI?");
		Cliente cliente = (Cliente)target;
		LOG.info("Va a comprobar si el DNI está en la base de datos");
		LOG.info(cliente.toString());
		if(clienteServicio.existeCliente(cliente.getNif()) && cliente.getId()==null) {
			errors.rejectValue("nif", "cliente.nif.duplicado");
	
		}
		
	}

}

