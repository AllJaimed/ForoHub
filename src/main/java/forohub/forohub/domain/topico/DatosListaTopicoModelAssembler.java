package forohub.forohub.domain.topico;


import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DatosListaTopicoModelAssembler implements RepresentationModelAssembler<DatosListaTopico, EntityModel<DatosListaTopico>> {

    @Override
    @NonNull
    public EntityModel<DatosListaTopico> toModel(@NonNull DatosListaTopico datosListaTopico){
        return EntityModel.of(datosListaTopico);
    }
}
