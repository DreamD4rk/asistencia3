package growby.project.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

	@GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin(){
        return "Hola, has accedito con rol de ADMINISTRADOR";
    }

    @GetMapping("/accesEmpleado")
    @PreAuthorize("hasRole('EMPLEADO')")
    public String accessEmpleado(){
        return "Hola, has accedito con rol de EMPLEADO";
    }

    @GetMapping("/accessSuperadmin")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public String accessSuperadmin(){
        return "Hola, has accedito con rol de SUPER ADMINISTRADOR";
    }
	
}
