package forohub.forohub.controller;

import forohub.forohub.domain.ValidacionException;
import forohub.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class ControllerTopico {

    @Autowired
    private CreacionTopico creacionTopico;
    
    @Autowired
    private TopicoRepository repository;
    
    @Autowired
    private PagedResourcesAssembler<DatosListaTopico> pagedResourcesAssembler;
    
    @Autowired
    private DatosListaTopicoModelAssembler datosListaTopicoModelAssembler;

    @PostMapping
    @Transactional
    public ResponseEntity crear(@RequestBody @Valid DatosReservaTopico datos){
        var detalleTopico = creacionTopico.crearTopico(datos);
        return ResponseEntity.ok(detalleTopico);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DatosListaTopico>>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion){
        var pagina = repository.findAll(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(pagina, datosListaTopicoModelAssembler));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico){
        var verificarTopico = repository.existsById(id);
        if (!verificarTopico){
            throw new ValidacionException("Id ingresado no existente");
        }
        var topico = repository.getReferenceById(id);
        topico.actualizarInformacion(datosActualizacionTopico);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        var verificarTopico = repository.existsById(id);
        var topico = repository.getReferenceById(id);
        if (!verificarTopico){
            throw new ValidacionException("Id ingresado no existente");
        }
        repository.deleteById(topico.getId());
        return ResponseEntity.noContent().build();
    }

}
