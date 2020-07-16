


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
public class tarjetaPago {
	
	@Id
	@GeneratedValue //autogenera un Id unico
	@ApiModelProperty(notes="Id es autoincrementable", required=true)
	private Integer id;
	
	@NotBlank
	@ApiModelProperty(notes="Nombre del cliente", required=true)
	private String cliente;
	
	@NotNull
	@ApiModelProperty(notes="Numero de tarjeta", required=true)
	private Integer numeroTarjeta;
	
	@NotNull
	@ApiModelProperty(notes="Mes de vencimineto", required=true)
	private Integer mes;
	
	@NotBlank
	@ApiModelProperty(notes="Año de vencimiento", required=true)
	private Integer año;
	
	@NotBlank
	@ApiModelProperty(notes="clave de seguridad", required=true)
	private Integer cvv;
	
	
	
	

	
}
