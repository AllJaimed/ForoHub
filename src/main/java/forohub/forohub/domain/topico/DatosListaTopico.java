package forohub.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    boolean status,
    Long idUsuario,
    Long idCurso
) {

    public DatosListaTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.isStatus(), topico.getUsuario().getId(), topico.getCurso().getId());
    }

}
