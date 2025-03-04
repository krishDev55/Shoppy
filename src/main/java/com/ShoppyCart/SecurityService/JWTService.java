package com.ShoppyCart.SecurityService;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	private String SECRET_KEY = "";

	 public JWTService()  {
		 try {
			KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			SECRET_KEY=Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public String generateToken(String userName) {
			Map<String, Object> claims= new HashMap<>();
	    return Jwts.builder()
	    		.addClaims(claims)
	            .setSubject(userName)
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
	            .signWith(getKey())
//	            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	            .compact();
		
		
		}
	 
	 private SecretKey getKey() {
			byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
			return Keys.hmacShaKeyFor(keyBytes);
		}
	 
	 public String extractUserName(String token) {
			// TODO Auto-generated method stub
			return extractClaim(token,Claims::getSubject);
		}
	 
	 private <T> T extractClaim(String token,  Function<Claims,T> claimResolver) {
			// TODO Auto-generated method stub
			final Claims claims= extractAllClaims(token);
			
			return claimResolver.apply(claims);
		}

		private Claims extractAllClaims(String token) {
			// TODO Auto-generated method stub
			return Jwts
					.parserBuilder()
					.setSigningKey(getKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		}

		public boolean validateToken(String token, UserDetails userDetails) {
			// TODO Auto-generated method stub
			return true;
		}
	

}
