import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("john");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("100");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("101");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);
        System.out.println(user);

        // JSON 은 기본적으로 UTF-8을 사용한다. 따라서 UTF-8로 설정해야한다.

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // JsonNode : 직접 각각의 Json 구조(노드)에 접근가능
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText(); // 객체에 get으로 접근 후 타입 변환
        int _age = jsonNode.get("age").asInt(); // 객체에 get으로 접근 후 타입 변환
        System.out.println(_name + " " + _age);

        JsonNode cars = jsonNode.get("cars"); // 객체에 get으로 접근 후 배열타입(ArrayNode)으로 변환
        ArrayNode arrayNode = (ArrayNode)cars;
        // convertValue : json 객체를 원하는 클래스 타입(TypeReference)으로 매핑
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        // ObjectNode로 각각의 Json 노드값을 바꿔줄 수 있다.
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);
        System.out.println(objectNode.toPrettyString());
    }
}
