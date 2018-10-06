package promedicusdb.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class UtilToken {
	
    //llave de encriptacion por text
    private static final String STRING_KEY = "LLAVE ULTRA SECRETA";
	
	public static String getToken(String unNick){
		JwtBuilder compactJws = Jwts.builder();
		Calendar cal = new GregorianCalendar();
		
		compactJws.setIssuedAt(cal.getTime());
		cal.add(Calendar.HOUR, 24); //vencimiento a 24 horas
		
		compactJws.setExpiration(cal.getTime())
			.setSubject(unNick)
			.signWith(SignatureAlgorithm.HS512, STRING_KEY);
			
		return compactJws.compact();
	}
	
	public static Boolean esValido(String token) {
        try {
            Jwts.parser().setSigningKey(STRING_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException | IllegalArgumentException e) {
            return false;
        }catch ( ExpiredJwtException exe){
            return false;
        }
    }
	
	public static String getNick(String token) {
        try {
            return Jwts.parser().setSigningKey(STRING_KEY).parseClaimsJws(token).getBody().getSubject(); 
        } catch (SignatureException | IllegalArgumentException e) {
            return "";
        }catch ( ExpiredJwtException exe){
            return "";
        }        
    }
	
	public static String getNick(@Context HttpServletRequest unRequest) {
		HttpServletRequest servletRequest = (HttpServletRequest)unRequest;
		String unToken = servletRequest.getHeader("Authorization");
		return UtilToken.getNick(unToken);
	}
}
