package forohub.forohub.controller;


import forohub.forohub.domain.usuario.DatosAutentificacion;
import forohub.forohub.domain.usuario.Usuario;
import forohub.forohub.infra.security.DatosTokenJWT;
import forohub.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutentificacion datos){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasenha());
        var autentificacion = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) autentificacion.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}
