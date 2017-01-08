package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.FieldError;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by LulzimG on 04/01/17.
 */
public class MessageGenerator {
    public static String generateError(List<FieldError> errorList) {

        Hashtable message = new Hashtable();
        for (FieldError error : errorList) {
            message.put(error.getField(), error.getDefaultMessage());
        }
        try {
            String mapAsJson = new ObjectMapper().writeValueAsString(message);
            return mapAsJson;
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static String stringToMsg(String error) {
        Hashtable message = new Hashtable();
        message.put("message", error);
        try {
            String mapAsJson = new ObjectMapper().writeValueAsString(message);
            return mapAsJson;
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
