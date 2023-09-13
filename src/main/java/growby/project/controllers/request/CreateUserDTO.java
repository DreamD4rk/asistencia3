package growby.project.controllers.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

	private String nombres;
	private String apellido_paterno;
	private String apellido_materno;
	
	@Email
	@NotBlank
	private String correo;
	private String n_documento;
	private Boolean area;
	private Boolean estado;
	private Boolean tyc;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	private Set<String> roles;

}
