package com.jwtAuth.security.services;

import com.jwtAuth.security.entity.User;
import com.jwtAuth.security.model.response.UserDetailsResponse;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private final String secret_key="mySecretKey";
    private long accessTokenValidity = 30*60*1000; //valid till 30 mins

    private final JwtParser jwtParser;

    public JwtService(UserDetailsService userDetailsService) {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public String createToken(User user,Map<String,Object> extraClaims){
        //Map<String,String> extraClaims = new HashMap<>();
        Claims claims = Jwts.claims();
        claims.put("firstName",user.getFirstName());
        claims.put("lastName",user.getLastName());
        claims.put("id",user.getId());
        claims.put("role",user.getRole());
//        Date tokenCreateTime = new Date();
//        Date tokenValidity = new Date(tokenCreateTime.getTime()+ TimeUnit.MINUTES.toMillis(accessTokenValidity));
        Date tokenValidity = new Date(System.currentTimeMillis()+accessTokenValidity);
        Date issuedtime = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setExpiration(tokenValidity)
                .setIssuedAt(issuedtime)
                .signWith(SignatureAlgorithm.HS256,secret_key)
                .compact();
    }

    public Claims parseJwtClaims(String token){return jwtParser.parseClaimsJws(token).getBody();}

    public Claims resolveClaims(HttpServletRequest req){
        try {
            String token = resolveToken(req);
            if(token != null){
                return parseJwtClaims(token);
            }
            return null;
        }catch (ExpiredJwtException ex){
            req.setAttribute("expired",ex.getMessage());
            throw ex;
        }catch (Exception ex){
            req.setAttribute("invalid",ex.getMessage());
            throw ex;
        }
    }
    public String resolveToken(HttpServletRequest request){ //extract token
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if(bearerToken!=null&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
    public boolean isTokenExpired(Date expirationDate) throws AuthenticationException{
        try {
            //return expirationDate.after(new Date());
            if(expirationDate.before(new Date()))
                return true;
            else
                return false;
        }catch (Exception e){
            throw e;
        }
    }

    public boolean isTokenValid(String accessToken, UserDetails userDetails) throws AuthenticationException {
        String username = userDetails.getUsername();
        Claims claims = parseJwtClaims(accessToken);
        return username.equals(claims.getSubject())&&!isTokenExpired(claims.getExpiration());
    }

    public String extractToken(String token){
        return token.substring(TOKEN_PREFIX.length());
    }
    public Claims getUserInfo(String token){
        String bearerToken = extractToken(token);
        System.out.println("BearerToken in jwtService:  "+bearerToken);
        Claims claims = parseJwtClaims(bearerToken);
        return claims;
    }

}
