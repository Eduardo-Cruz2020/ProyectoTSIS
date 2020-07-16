package mx.uam.tsis.ejemplobackend.negocio.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Grupos")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter 
public class Boleto implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1578618654741765720L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;
    
    @NotNull
    @Column(name = "cliente")
    private String cliente;
    
    @NotNull
    @Column(name = "fecha")
    private String fecha;
    
    @NotNull
    @Column(name = "horario")
    private String horario;
    
    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @NotNull
    @Column(name = "pelicula")
    private String pelicula;
    
    @NotNull
    @Column(name = "monto")
    private Integer monto;

}
