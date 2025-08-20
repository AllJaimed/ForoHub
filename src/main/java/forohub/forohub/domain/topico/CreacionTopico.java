package forohub.forohub.domain.topico;

import forohub.forohub.domain.ValidacionException;
import forohub.forohub.domain.curso.CursoRepository;
import forohub.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreacionTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosDetalleTopico crearTopico(DatosReservaTopico datos){

        if (!usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id informado");
        }
        if (!cursoRepository.existsById(datos.idCurso())){
            throw new ValidacionException("No existe un curso con el id informado");
        }

        var titulo = topicoRepository.existsByTitulo(datos.titulo());
        var mensaje = topicoRepository.existsByMensaje(datos.mensaje());

        if (titulo && mensaje){
            throw new ValidacionException("No se puede generar el topico. Topico ya existente");
        }

        var usuario = usuarioRepository.findById(datos.idUsuario()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();
        var fechaActual = LocalDateTime.now();

        var topico = new Topico(null, datos.titulo(), datos.mensaje(), fechaActual, true, usuario, curso);

        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }

}
