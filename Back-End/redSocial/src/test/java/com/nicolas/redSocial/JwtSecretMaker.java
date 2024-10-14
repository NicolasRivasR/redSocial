
package com.nicolas.redSocial;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.Test;


public class JwtSecretMaker {
    
   @Test
   public void generatesecretKey(){
   
       SecretKey key = Jwts.SIG.HS512.key().build();
       String sKey = DatatypeConverter.printHexBinary(key.getEncoded());
       System.out.println(sKey);
   }
    
}
