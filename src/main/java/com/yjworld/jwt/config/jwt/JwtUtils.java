package com.yjworld.jwt.config.jwt;

import com.yjworld.jwt.domain.user.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

	public static final String CLAIMS_USERNAME = "username";
	public static final String CLAIMS_ROLES = "roles";
	public static final String TOKEN_TYPE = "Bearer";

	@Value("${jwt.secret-key}")
	private String secretKey;

	@Value("${jwt.expiration-time}")
	private int expirationTime;

	@PostConstruct
	protected void afterSet() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String generateToken(User user) {
		Map<String, Object> data = new HashMap<>();
		data.put(CLAIMS_USERNAME, user.getUsername());
		data.put(CLAIMS_ROLES, user.getRole());

		return generateToken(data);
	}

	public String generateToken(Map<String, Object> data) {
		Claims claims = Jwts.claims();
		claims.putAll(data);

		return generateToken(claims);
	}

	private String generateToken(Claims claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + expirationTime))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

	public boolean isValidToken(String token) {
		try {
			token = parseToken(token);
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;

		} catch (MalformedJwtException | SignatureException | ExpiredJwtException
				| UnsupportedJwtException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getTokenFromHeader(HttpServletRequest request) {
		return request.getHeader(HttpHeaders.AUTHORIZATION);
	}

	public String parseToken(String token) {
		return token.replace(TOKEN_TYPE, "").trim();
	}

	public Claims getClaims(String token) {
		token = parseToken(token);
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
	}
}
