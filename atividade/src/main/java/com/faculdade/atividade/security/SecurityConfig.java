package com.faculdade.atividade.security;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private String key;

    @Value("${jwt.private.key}")
    private String priv;

    String getPrivateKey() throws IOException {
        return new String((new DefaultResourceLoader().getResource(priv)).getInputStream().readAllBytes());
    }

    String getPublicKey() throws IOException{
        return new String((new DefaultResourceLoader().getResource(key)).getInputStream().readAllBytes());
    }

    RSAPrivateKey privateKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException{
        String privado = getPrivateKey();

        privado = privado.replace("-----BEGIN PRIVATE KEY-----", "")
                         .replace("-----END PRIVATE KEY-----", "")
                         .replaceAll("\\s", "");

        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privado));
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(keySpecPKCS8);

        return privKey;
    }

    RSAPublicKey publicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException{
        String pub = getPublicKey();

        pub = pub.replace("-----BEGIN PUBLIC KEY-----", "")
                 .replace("-----END PUBLIC KEY-----", "")
                 .replaceAll("\\s", "");

        KeyFactory kf = KeyFactory.getInstance("RSA");
        
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(pub));
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);

        return pubKey;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            auth -> auth.requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer(
                conf -> conf.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        try {
            return NimbusJwtDecoder.withPublicKey(publicKey()).build();
        } catch (Exception e) {
            throw new IllegalStateException("Erro no decoder");
        }
    }

    @Bean
    JwtEncoder jwtEncoder() {
        try {
            RSAKey jwt = new RSAKey.Builder(publicKey()).privateKey(privateKey()).build();

            return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwt)));
        } catch (Exception e) {
            throw new IllegalStateException("Erro no encoder");
        }
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
