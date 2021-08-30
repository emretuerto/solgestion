package es.emretuerto.solgestion.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

//@Entity
public class FacturaCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Double id;
	public Double numeroFactura;
    @Column(name = "FECHA_FACTURA", columnDefinition = "DATE")
    private LocalDate fechaFactura;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    @Cascade(CascadeType.ALL)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "facturacliente", fetch = FetchType.LAZY)
    @Column(name = "LINEA_FACTURAS")
    private List<LineaFacturaCliente> sesionesCliente = new ArrayList<LineaFacturaCliente>();
    

}
