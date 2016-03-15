package de.jugsaar.examples.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by tom on 07.03.16.
 */
public class JwtParsingExample {

    public static void main(String[] args) throws Exception {

        String realmPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk8/XJRIbEW/DW30YxcnjNPFgZjhtsjcatetMP9xxyXDBPQA+jh9xAM4bf1v3QJOJalNuNpHat9k3e9Aa1M+qB1zq8blAZtsSF8jM7okuMHyeU6YXw7rY+d4t1Xigbt9LTaPfb5qCZvMShfjgeSb2DgZRbRlKfh9cVUZ8e7XUTm6UDlEvBI9mQiiwolPVyPCZDFNI8pb35NcXI4Kzh4S15BnAGpyKpiQJEmXX4wifYak8weJVoFuAu9fjjtdeacEqHFafyVZvEdKHTD+ofY9z6/JELdFRcI2N3a8rRa+JM2+CvrAIuOzLHGBZ1WwPKsHe6zhsxC1oODRvzVYOzOtvbQIDAQAB";
        String accessTokenString = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI3OGRhYTA3MC0wNjcwLTRkNmYtOTc2ZC1lODZhM2Q0MzE4ZmYiLCJleHAiOjE0NTczNjcyMjEsIm5iZiI6MCwiaWF0IjoxNDU3MzY2OTIxLCJpc3MiOiJodHRwOi8vbG9naW4uYWNtZS5sb2NhbDo4MDgxL2F1dGgvcmVhbG1zL2FjbWUiLCJhdWQiOiJ2YWFkaW4tYXBwIiwic3ViIjoiNjU5OTJmNzktNjM4Mi00ZDk4LTlhZjItOTBjZjVmNzZmZTkxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidmFhZGluLWFwcCIsInNlc3Npb25fc3RhdGUiOiIxMmVlOGUwZi1kMzgwLTQxZmEtODMxMS1jMmI0MjM2OGJmZjkiLCJjbGllbnRfc2Vzc2lvbiI6ImY3NTAzYmJkLWFkZTYtNDQ1Ny1iZTIxLTQ3NDRkMWJmMTc4NCIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0Ojc3NzciXSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjbWUtcGV0Y2xpbmljIjp7InJvbGVzIjpbImFkbWluIiwidXNlciJdfSwidmFhZGluLWFwcCI6eyJyb2xlcyI6WyJ1c2VyIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IlRoZW8gVGVzdGVyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidGhlbyIsImdpdmVuX25hbWUiOiJUaGVvIiwiZmFtaWx5X25hbWUiOiJUZXN0ZXIiLCJlbWFpbCI6InRvbSt0aGVvQGxvY2FsaG9zdCJ9.F9ELYpkQS0gV48b2QQ4HEh2DPTFjkWbVv0f5ZkibW4BnBIwl9retEqjiblg2P_qLb3APRojfSLWbHDn6ClNiFNGhX4n9nD78Fm6Tc-8JQtVhuEeyhBLn0hOMdeFIp5xX1TSpIkI3_3emCj_pYNp8iy0FraEOeHsPyINF03MWHpg3FNqIl5wAUi5MSBhP3R9W8pRzrFug4ga6r7X-hXgjudzcrBN2HthARhw4d4fkq0wNMNHH8dFCugulg-vY011sJ0B56uG2tBVoMf3WXMUOiIe_gIEzG5siu3Rdp2BSSJTpwwESxRgx59DQYxiMpX3jJYBT8gopDcEc5hLc92X9uA";

        PublicKey publicKey = decodePublicKey(pemToDer(realmPublicKey));

        Jws<Claims> claimsJws = Jwts.parser() //
                .setSigningKey(publicKey) //
                .parseClaimsJws(accessTokenString) //
                ;

        System.out.println(claimsJws);
        //gives: header={alg=RS256},body={jti=f47335d5-9da0-4ada-a99f-4805a59ddba4, exp=1457366908, nbf=0, iat=1457366608, iss=http://login.acme.local:8081/auth/realms/acme, aud=vaadin-app, sub=65992f79-6382-4d98-9af2-90cf5f76fe91, typ=Bearer, azp=vaadin-app, session_state=12ee8e0f-d380-41fa-8311-c2b42368bff9, client_session=f7503bbd-ade6-4457-be21-4744d1bf1784, allowed-origins=[http://localhost:7777], resource_access={acme-petclinic={roles=[admin, user]}, vaadin-app={roles=[user]}, account={roles=[manage-account, view-profile]}}, name=Theo Tester, preferred_username=theo, given_name=Theo, family_name=Tester, email=tom+theo@localhost},signature=frMCkpDKG4VixXRZhh7KZqjDCxPbZq_6Wrl5X6RhjlGs9hL22Z6pcsVSlIzpincdwbLCpLYpLs3T2LRrlZ-YNUGOnKObnrmlVbMNi8UmGJiAj0bAsIPYWEfA-Ww3wuTitfjo0fgbAb8F_sLsPR9qjE6BcDPVXR2S_SJVWJ1CKb5kwiwKTTzAMUo1H22Ce64hoeSuEdQFM1x1n-M8kTkLPUPnL_lj-mOIpqbLZyrls3_TEL3up0-XYyF2Gt9fDQKXTp_XPLizGUiiY90TQC4rhNye3JPLMB6RZnQFmyJq5I5Cq0ybdMarloeLjvYjc3RyIgZgtFWjk5aNYDaietBJSA

    }

    /**
     * Decode a PEM string to DER format
     *
     * @param pem
     * @return
     * @throws java.io.IOException
     */
    public static byte[] pemToDer(String pem) throws IOException {
        return Base64.getDecoder().decode(stripBeginEnd(pem));
    }

    public static String stripBeginEnd(String pem) {

        String stripped = pem.replaceAll("-----BEGIN (.*)-----", "");
        stripped = stripped.replaceAll("-----END (.*)----", "");
        stripped = stripped.replaceAll("\r\n", "");
        stripped = stripped.replaceAll("\n", "");

        return stripped.trim();
    }


    public static PublicKey decodePublicKey(byte[] der) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {

        X509EncodedKeySpec spec = new X509EncodedKeySpec(der);

        KeyFactory kf = KeyFactory.getInstance("RSA"
                //        , "BC" //use provider BouncyCastle if available.
        );

        return kf.generatePublic(spec);
    }
}
