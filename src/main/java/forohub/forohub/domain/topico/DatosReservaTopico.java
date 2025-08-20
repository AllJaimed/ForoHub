package forohub.forohub.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosReservaTopico(
        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso,
        @NotNull
        String titulo,
        @NotNull
        String mensaje
) {
}
