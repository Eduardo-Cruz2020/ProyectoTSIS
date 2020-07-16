package mx.uam.tsis.ejemplobackend.negocio.modelo;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Builder.Default
	@OneToMany(targetEntity = Boleto.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	//@JoinColumn(name = "id") // No crea tabla intermedia	
	private List <Boleto> Boleto = new ArrayList <> ();
	
	public boolean addBoletos(Boleto boleto) {
		return Boleto.add(boleto);
	}


}
