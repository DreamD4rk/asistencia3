package growby.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import growby.project.models.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	//ESTO SE PUEDE CAMBIAR EL NOMBRE DEL METODO POR MEDIO DE UN QUERY 
	Optional<UserEntity> findByUsername(String username);

	@Query("select u from UserEntity u where u.username = ?1")
	Optional<UserEntity> getName(String username);	
	
}
