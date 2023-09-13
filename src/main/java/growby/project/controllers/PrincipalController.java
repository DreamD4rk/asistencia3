package growby.project.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import growby.project.controllers.request.CreateUserDTO;
import growby.project.models.ERole;
import growby.project.models.RoleEntity;
import growby.project.models.UserEntity;
import growby.project.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController
public class PrincipalController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello sin seguridad";
	}
	
	@GetMapping("/helloSecured")
	public String helloSecured() {
		return "hello con seguridad";
	}
	
	/*@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO, Long id, String nombres, String apellido_paterno, String apellido_materno, String correo, String n_documento, Boolean area, Boolean estado, Boolean tyc, String username, String password) {
	    Set<RoleEntity> roles = createUserDTO.getRoles().stream()
	            .map(role -> new RoleEntity( id, ERole.valueOf(role)))
	            .collect(Collectors.toSet());

		UserEntity userEntity = new UserEntity(id, nombres, apellido_paterno, apellido_materno, correo, n_documento, area, estado, tyc, username, password, roles);
		
		userEntity.setNombres(createUserDTO.getNombres());
		userEntity.setApellido_paterno(createUserDTO.getApellido_paterno());
		userEntity.setApellido_materno(createUserDTO.getApellido_materno());
	    userEntity.setCorreo(createUserDTO.getCorreo());
	    userEntity.setN_documento(createUserDTO.getN_documento());
	    userEntity.setArea(createUserDTO.getArea());
	    userEntity.setEstado(createUserDTO.getEstado());
	    userEntity.setTyc(createUserDTO.getTyc());
	    userEntity.setUsername(createUserDTO.getUsername());
	    userEntity.setPassword(createUserDTO.getPassword());
	    
	    userEntity.setRoles(roles);

	    userRepository.save(userEntity);

	    return ResponseEntity.ok(userEntity);
	}*/
	
	@PostMapping("/createUser")
	 public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
		
		Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());	
		
		
		 UserEntity userEntity = UserEntity.builder()
				 	.nombres(createUserDTO.getNombres())
				 	.apellido_materno(createUserDTO.getApellido_materno())
				 	.apellido_paterno(createUserDTO.getApellido_paterno())
				 	.correo(createUserDTO.getCorreo())
				 	.n_documento(createUserDTO.getN_documento())
				 	.area(createUserDTO.getArea())
				 	.estado(createUserDTO.getEstado())
				 	.tyc(createUserDTO.getTyc())
	                .username(createUserDTO.getUsername())
	                .password(passwordEncoder.encode(createUserDTO.getPassword()))

	                .roles(roles)
	                .build();
		 
		 userRepository.save(userEntity);

	        return ResponseEntity.ok(userEntity);
		
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestParam String id) {
		userRepository.deleteById(Long.parseLong(id));
		return "Se ha borrado el user con id: ".concat(id);
	}
}
