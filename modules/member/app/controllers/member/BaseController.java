package controllers.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.google.common.base.CaseFormat;

import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@Singleton
public class BaseController extends Controller {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    private final static ObjectMapper objectMapper = mapper();
    
    private static ObjectMapper mapper() {
        
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("uuuu/MM/dd")));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("uuuu/MM/dd")));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss")));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss")));
        
        return new ObjectMapper()
        .registerModule(new Jdk8Module())
        .registerModule(module)
        .setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"))
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @Inject
    protected FormFactory formFactory;

    protected <T> Form<T> form(Class<T> clazz) {
        JsonNode json = treeToJson(request().body().asJson(), clazz);
        return formFactory.form(clazz).bind(json);
    }

    @SuppressWarnings("unchecked")
    public static Result badRequest(JsonNode content) {

        Map<String, List<String>> allMessages = null;
        try {
            allMessages = (Map<String, List<String>>) objectMapper.treeToValue(content, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Map<String, List<String>> lowerKeyMap = allMessages.entrySet().stream().collect(Collectors
                .toMap(t -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, t.getKey()), t -> t.getValue()));
        return status(BAD_REQUEST, Json.toJson(lowerKeyMap));
    }

    protected JsonNode toResponseJson(Object fromValue) {
        return objectMapper.valueToTree(fromValue);
    }

    private <T> JsonNode treeToJson(TreeNode n, Class<T> valueType) {
        try {
            return Json.toJson(objectMapper.treeToValue(n, valueType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
