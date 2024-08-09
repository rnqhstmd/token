package org.token.util.jwt;

import org.springframework.stereotype.Component;
import org.token.exception.errorcode.ErrorCode;
import org.token.exception.UnauthorizedException;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class JwtEncoder {
    public static  final String TOKEN_TYPE="Bearer ";

    public static String encodeJwtToken(String token){
        String cookieValue = TOKEN_TYPE+token;
        return URLEncoder.encode(cookieValue, StandardCharsets.UTF_8).replace("\\", "%20");
    }

    public static String decodeJwtBearerToken(String cookieValue){
        String decodedValue = URLDecoder.decode(cookieValue,StandardCharsets.UTF_8);

        if (decodedValue.startsWith(TOKEN_TYPE)) {
            return decodedValue.substring(TOKEN_TYPE.length());
        }

        throw new UnauthorizedException(ErrorCode.INVALID_TOKEN);
    }
}
