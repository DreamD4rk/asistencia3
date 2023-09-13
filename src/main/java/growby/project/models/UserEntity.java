package growby.project.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 25)
	private String nombres;
	
	@Column
	private String apellido_paterno;
	
	@Column
	private String apellido_materno;
	
	@Column
	@Email
	@NotBlank
	private String correo;
	
	@Column
	private String n_documento;
	
	@Column
	private Boolean area;
	
	@Column
	private Boolean estado;
	
	@Column
	private Boolean tyc;
	
	@Column
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@ManyToMany( fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST) //al consultar un usuario, llama a todos los roles asociados a ese usuario
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

}














