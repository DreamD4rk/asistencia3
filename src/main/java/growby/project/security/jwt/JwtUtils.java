package growby.project.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {
	
	

	@Value("${jwt.secret.key}")
	private String secretKey;
	
	@Value("${jwt.time.expiration}")
	private String timeExpiration;
	
	//CREAR UN TOKEN DE ACCESO
	public String generateAccesToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ Long.parseLong(timeExpiration)))
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	//VALIDAR EL TOKEN DE ACCESO
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
			return true;
		} catch (Exception e) {
			log.error("Token invalido, error: ".concat(e.getMessage())); //Slf4j MENSAJE
			return false;
		}
	}
	
	//OBTENER EL USERNAME DEL TOKEN
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	
	//OBTENER UN SOLO CLAIM
	public <T> T getClaim(String token, Function<Claims, T> claimsFunction) {
		Claims claims = extracAllClaims(token);
		return claimsFunction.apply(claims);
	}
	
	
	//OBTENER TODOS LOS CLAIMS DEL TOKEN	
	public Claims extracAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	//OBTENER FIRMA DEL TOKEN
	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
