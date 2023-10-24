package sv.com.jsoft.ws.test.services;

import jakarta.enterprise.context.RequestScoped;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;
import sv.com.jsoft.ws.test.util.TokenUtils;

import java.util.Arrays;

@RequestScoped
public class TokenService {

    public String generateToken(String email, String user, String date, String role){
        try {
            JwtClaims jwtClaims = new JwtClaims();
            jwtClaims.setIssuer("jsoftTest");
            jwtClaims.setJwtId("a-123");
            jwtClaims.setSubject(email);
            jwtClaims.setClaim(Claims.upn.name(), email);
            jwtClaims.setClaim(Claims.preferred_username.name(), user);
            jwtClaims.setClaim(Claims.birthdate.name(), date);
            jwtClaims.setClaim(Claims.groups.name(), Arrays.asList(role));
            jwtClaims.setAudience("using-jwt");
            jwtClaims.setExpirationTimeMinutesInTheFuture(5);

            return TokenUtils.generateTokenString(jwtClaims);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
