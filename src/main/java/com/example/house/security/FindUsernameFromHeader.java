package com.example.house.security;

import com.example.house.exception.PersonValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;

import java.util.Map;

public class FindUsernameFromHeader {

    private Map<String, String> header;

    public FindUsernameFromHeader() {
    }

    public FindUsernameFromHeader(Map<String, String> header) {
        this.header = header;
    }

    public String retrieveUsername() {
        String token = getToken(header);
        return getUsernameFromToken(token);

    }

    private String getToken(Map<String, String> header) {
        for(Map.Entry<String, String> entry : header.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(key.equalsIgnoreCase("authorization")) {
                return value;
            }
        }
        throw new PersonValidationException("Your token is wrong we can not validate it. Please login again.");
    }

    private String getUsernameFromToken(String token)  {
        String jwtToken = token;
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];

        Base64 base64Url = new Base64(true);

        String body = new String(base64Url.decode(base64EncodedBody));
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode rootNode = mapper.readTree(body);
            String username = rootNode.findValue("sub").toString();
            username = username.replace("\"", ""); // the value is "username" so we have to remove the " from the String.
            return username;
        } catch (JsonProcessingException e) {
            throw new PersonValidationException("Can not retrieve the username from the token.");
        }
    }
}
