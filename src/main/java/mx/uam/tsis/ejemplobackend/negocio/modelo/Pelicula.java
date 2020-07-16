package mx.uam.tsis.ejemplobackend.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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
public class Pelicula {
	
	@Id
	@GeneratedValue //autogenera un Id unico
	@ApiModelProperty(notes="Id es autoincrementable", required=true)
	private Integer id;
	
	@NotBlank
	@ApiModelProperty(notes="Titulo de la pelicula", required=true)
	private String titulo;
	
	@NotBlank
	@ApiModelProperty(notes="Imagen", required=true)
	private String imagenURL;
	
	@NotBlank
	@ApiModelProperty(notes="Genero", required=true)
	private String genero;
	
	@NotBlank
	@ApiModelProperty(notes="Fecha", required=true)
	private String fecha;
	
	@NotBlank
	@ApiModelProperty(notes="Horarios", required=true)
	private String horarios;
	
	@NotBlank
	@ApiModelProperty(notes="Descripcion", required=true)
	private String descripcion;

	@NotBlank
	@ApiModelProperty(notes="Clasificacion", required=true)
	private String clasificacion;


}
