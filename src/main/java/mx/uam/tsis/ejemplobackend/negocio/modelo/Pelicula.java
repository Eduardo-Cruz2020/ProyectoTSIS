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
public class Pelicula {
	@Id
	@GeneratedValue //autogenera un Id unico
	@ApiModelProperty(notes="Id es autoincremntable", required=true)
	private Integer Id_Pelicula;
	@NotBlank
	@ApiModelProperty(notes="Titulo de la pelicula", required=true)
	private String titulo;
	@NotBlank
	@ApiModelProperty(notes="Descripcion", required=true)
	private String descripcion;
	@NotBlank
	@ApiModelProperty(notes="Genero", required=true)
	private String genero;
	@NotBlank
	@ApiModelProperty(notes="Clasificacion", required=true)
	private String clasificacion;
	@NotBlank
	@ApiModelProperty(notes="Horarios", required=true)
	private String horarios;
	@NotBlank
	@ApiModelProperty(notes="Imagen", required=true)
	private String imagenURL;
}
