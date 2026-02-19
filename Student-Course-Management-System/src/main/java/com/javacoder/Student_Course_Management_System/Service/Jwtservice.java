package com.javacoder.Student_Course_Management_System.Service;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class Jwtservice {

  private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

  private static final long EXP = 1000L * 60 * 60 * 6;

  public boolean validatetoken(String token) throws Exception {
    try{
      Jwts.parserBuilder()
          .setSigningKey(key())
          .build()
          .parseClaimsJws(token)
          .getBody();
      return true;
    }catch(MalformedJwtException malformedJwtException){
      throw new Exception("Invaild token");
    }catch(UnsupportedJwtException unsupportedJwtException){
      throw new Exception("Unsupport token");
    }catch(ExpiredJwtException expiredJwtException){
      throw new Exception("Expired token");
    }catch(IllegalArgumentException illegalArgumentException){
      throw new Exception("illgal token");
    }
  }

  public String extractemail(String token) {
    return Jwts.parserBuilder()
          .setSigningKey(key())
          .build()
          .parseClaimsJws(token)
          .getBody()
          .getSubject();
  }

  public String generatetoken(String username, String role) {

    return Jwts.builder()
               .claim("role", role)
               .setSubject(username)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + EXP ))
               .signWith(key())
               .compact();
  }

  private SecretKey key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
  
  }

}
