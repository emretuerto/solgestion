package es.emretuerto.solgestion.validaciones;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.emretuerto.solgestion.auxiliares.AuxiliarDatos2;
import es.emretuerto.solgestion.servicios.LamparaServicioInterface;


@Component
public class AuxiliarDatos2Validator implements Validator {

	final Logger LOG = Logger.getLogger("AuxiliarDatos2.class");

	@Autowired
	LamparaServicioInterface lamparaServicio;

	@Override
	public boolean supports(Class<?> clazz) {
		return AuxiliarDatos2.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		LOG.info("HACE ALGO AQUI?");
		AuxiliarDatos2 auxiliarDatos2 = (AuxiliarDatos2) target;
		LOG.info("Va a comprobar si existe la lámpara");
		LOG.info(auxiliarDatos2.toString());

		try {
			if (!lamparaServicio.existe(auxiliarDatos2.getDato2())) {
				errors.rejectValue("dato2", "lampara.no.existe");
			}

			else if (lamparaServicio.buscaPorCodigo(auxiliarDatos2.getDato2()).getMaquina() != null) {

				errors.rejectValue("dato2", "lampara.en.uso");

			}
		} catch (Exception e) {
		}
	}
}
