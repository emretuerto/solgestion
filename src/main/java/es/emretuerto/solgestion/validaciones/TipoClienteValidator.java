package es.emretuerto.solgestion.validaciones;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.emretuerto.solgestion.modelo.TipoCliente;
import es.emretuerto.solgestion.servicios.TipoClienteServicioInterface;

@Component
public class TipoClienteValidator implements Validator{

	@Autowired
	TipoClienteServicioInterface tipoClienteServicio;
	
	@Override
	public boolean supports(Class<?> clazz) {

		return TipoCliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		TipoCliente tipoCliente = (TipoCliente)target;
		
		if(tipoClienteServicio.existeTipoCliente(tipoCliente.getCodigo())) {
			errors.rejectValue("codigo", "tipocliente.codigo.duplicado");
		}
		
		
	}


}
