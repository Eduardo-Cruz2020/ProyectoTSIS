package mx.uam.tsis.ejemplobackend.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class FormaPago {
	
	@Id
	@GeneratedValue //autogenera un Id unico
	@ApiModelProperty(notes="Id es autoincremntable", required=true)
	private Integer idFormaPago;
	
	@NotNull
	@ApiModelProperty(notes="Titulo de la pelicula", required=true)
	private Integer numeroCuenta;
	
	@NotBlank
	@ApiModelProperty(notes="Descripcion", required=true)
	private String fecha;
	
	@NotBlank
	@ApiModelProperty(notes="Genero", required=true)
	private String nombreCliente;
	
	@NotNull
	@ApiModelProperty(notes="Clasificacion", required=true)
	private Integer monto;
	
}
