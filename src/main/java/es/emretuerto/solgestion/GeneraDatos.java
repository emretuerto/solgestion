/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion;

import es.emretuerto.solgestion.dao.BonoRepository;
import es.emretuerto.solgestion.dao.ClienteRepository;
import es.emretuerto.solgestion.dao.FototipoRepository;
import es.emretuerto.solgestion.dao.LamparaInstaladaRepository;
import es.emretuerto.solgestion.dao.LamparaRepository;
import es.emretuerto.solgestion.dao.MaquinaRepository;
import es.emretuerto.solgestion.dao.PotenciaRepository;
import es.emretuerto.solgestion.dao.TipoClienteRepository;
import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.modelo.Fototipo;
import es.emretuerto.solgestion.modelo.Lampara;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.modelo.Potencia;
import es.emretuerto.solgestion.modelo.TipoCliente;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author emretuerto
 */
@Service
public class GeneraDatos {

    @Autowired
    PotenciaRepository potenciaDao;

    @Autowired
    LamparaRepository lamparaDao;

    @Autowired
    MaquinaRepository maquinaDao;

    @Autowired
    BonoRepository bonoDao;

    @Autowired
    FototipoRepository fototipoDao;

    @Autowired
    TipoClienteRepository tipoClienteDao;

    @Autowired
    ClienteRepository clienteDao;

    @Autowired
    LamparaInstaladaRepository lamparaInstaladaDao;

    public void generaPotencias() {

        Potencia potencia1 = new Potencia("80", 80);
        Potencia potencia2 = new Potencia("100", 100);
        Potencia potencia3 = new Potencia("120", 120);
        Potencia potencia4 = new Potencia("140", 140);
        Potencia potencia5 = new Potencia("160", 160);
        Potencia potencia6 = new Potencia("180", 180);

        potenciaDao.save(potencia1);
        potenciaDao.save(potencia2);
        potenciaDao.save(potencia3);
        potenciaDao.save(potencia4);
        potenciaDao.save(potencia5);
        potenciaDao.save(potencia6);

    }

    public void generaLamparas() {


        Lampara lampara1 = new Lampara("0001", "Sylvania", "Purebronce 100 1.2", 800);
        Lampara lampara2 = new Lampara("0002", "Sylvania", "CaralSol", 1000);
        Lampara lampara3 = new Lampara("0003", "Sylvania", "Chamuscator", 2800);

        lamparaDao.save(lampara1);
        lamparaDao.save(lampara2);
        lamparaDao.save(lampara3);
    }

    public void generarMaquinas() {

        Maquina maquina1 = new Maquina("0001", "Cabina 1", "Ergoline", "23/1", 0, 0);
        Maquina maquina2 = new Maquina("0002", "Cabina 2", "Solpasion", "Mega Super", 150, 150);
        Maquina maquina3 = new Maquina("0003","Cabina 3", "Soltron", "Bronceator", 163, 25);

        maquinaDao.save(maquina1);
        maquinaDao.save(maquina2);
        maquinaDao.save(maquina3);
    }

    public void generarBonos() {

        Bono bono1 = new Bono("0001", null, true, null, 600);
        Bono bono2 = new Bono("0002", null, false, 30, null);

        bonoDao.save(bono1);
        bonoDao.save(bono2);
    }

    public void generarFototipos() {

        Fototipo fototipo1 = new Fototipo("I");
        Fototipo fototipo2 = new Fototipo("II");
        Fototipo fototipo3 = new Fototipo("III");
        Fototipo fototipo4 = new Fototipo("IV");
        Fototipo fototipo5 = new Fototipo("V");
        Fototipo fototipo6 = new Fototipo("VI");

        fototipoDao.save(fototipo1);
        fototipoDao.save(fototipo2);
        fototipoDao.save(fototipo3);
        fototipoDao.save(fototipo4);
        fototipoDao.save(fototipo5);
        fototipoDao.save(fototipo6);
    }

    public void generarTiposCliente() {

        TipoCliente tipocliente1 = new TipoCliente("0001", "Cliente solarium");
        TipoCliente tipocliente2 = new TipoCliente("0002", "Cliente supermegaultra VIP");

        tipoClienteDao.save(tipocliente1);
        tipoClienteDao.save(tipocliente2);
    }

    public void generarClientes() {
    	

        Cliente cliente1 = new Cliente("0160141010208476", "EDUARDO", "MARTINEZ RETUERTO", "13155641P", "SAN LUIS", "39010", "SANTANDER", "CANTABRIA", LocalDate.of(1973, 4, 6), "942000000", "615303890", "emretuerto@gmail.com",  bonoDao.getOne(1), true);
        Cliente cliente2 = new Cliente("0160141010208477", "CRISTINA", "DE LA VEGA SANTOS", "20203178T", "SAN LUIS", "39010", "SANTANDER", "CANTABRIA",LocalDate.of(1975, 6, 21), "942999999", "635471840", "cgevasantos@gmail.com", bonoDao.getOne(2), true);

        clienteDao.save(cliente1);
        clienteDao.save(cliente2);
    }

    /*
    public void generarLamparasInstaladas() {

        LamparaInstalada lamparaInstalada1 = new LamparaInstalada(maquinaDao.getOne(1), lamparaDao.getOne(1), 20);
        LamparaInstalada lamparaInstalada2 = new LamparaInstalada(lamparaDao.getOne(3), 40);
        LamparaInstalada lamparaInstalada3 = new LamparaInstalada(lamparaDao.getOne(2), 20);
        lamparaInstaladaDao.save(lamparaInstalada1);
        Maquina maquina = maquinaDao.getOne(2);
        maquina.addLamparaInstalada(lamparaInstalada2);
        maquina.addLamparaInstalada(lamparaInstalada3);
    }*/
}
