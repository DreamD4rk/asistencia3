package growby.project;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import growby.project.models.ERole;
import growby.project.models.RoleEntity;
import growby.project.models.UserEntity;
import growby.project.repositories.UserRepository;


@SpringBootApplication
public class ProyectoGrowbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoGrowbyApplication.class, args);
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {

			UserEntity userEntity = UserEntity.builder()
					.apellido_paterno("rojas")
					.apellido_materno("agipe")
					.area(true)
					.estado(true)
					.n_documento("78954120")
					.nombres("andy")
					.tyc(true)
					.correo("andy@mail.com")
					.correo("andy@mail.com")
					.username("andy")
					.password(passwordEncoder.encode("123"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();

			UserEntity userEntity2 = UserEntity.builder()
					.apellido_paterno("chavez")
					.apellido_materno("diaz")
					.area(true)
					.estado(true)
					.n_documento("74185296")
					.nombres("daniel kenyi")
					.tyc(true)
					.correo("kenyi@mail.com")
					.username("kenyi")
					.password(passwordEncoder.encode("123"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.EMPLEADO.name()))
							.build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.apellido_paterno("taza")
					.apellido_materno("agua")
					.area(true)
					.estado(true)
					.n_documento("78415095")
					.nombres("cathy fiorella")
					.tyc(true)
					.correo("cathy@mail.com")
					.username("cathy")
					.password(passwordEncoder.encode("123"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.SUPERADMIN.name()))
							.build()))
					.build();

			userRepository.save(userEntity);
			userRepository.save(userEntity2);
			userRepository.save(userEntity3);
		};
	}
}
