package forohub.forohub.domain.topico;

import forohub.forohub.domain.curso.Curso;
import forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;



    public void actualizarInformacion(@Valid DatosActualizacionTopico datosActualizacionTopico) {
        if (datosActualizacionTopico.titulo() != null){
            this.titulo = datosActualizacionTopico.titulo();
        }
        if (datosActualizacionTopico.mensaje() != null){
            this.mensaje = datosActualizacionTopico.mensaje();
        }
    }

}
