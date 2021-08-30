package es.emretuerto.solgestion.validaciones;



import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.servicios.BonoServicioInterface;

@Component
public class BonoValidator implements Validator{


	
	@Autowired
	BonoServicioInterface bonoServicio;
	
	final Logger LOG = Logger.getLogger("BonoValidator.class");
	
	@Override
	public boolean supports(Class<?> clazz) {

		return Bono.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	
		LOG.info("HACE ALGO AQUI?");
		Bono bono = (Bono)target;
		LOG.info("Va a comprobar si el Bono está en la base de datos");
		
		if(bonoServicio.exiteBono(bono.getIdentificadorBono())) {
			errors.rejectValue("identificadorBono", "bono.id.duplicado");
	
		}
		
	}

}

