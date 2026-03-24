package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class task3 {
    public static void main(String[] args) {
        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode valuesNode = mapper.readTree(new File(valuesPath));                /// Чтение values.json

            Map<Integer, String> valueMap = new HashMap<>();
            JsonNode valuesArray = valuesNode.get("values");
            if (valuesArray != null && valuesArray.isArray()) {
                for (JsonNode item : valuesArray) {
                    int id = item.get("id").asInt();
                    String value = item.get("value").asText();
                    valueMap.put(id, value);
                }
            }

            JsonNode testsNode = mapper.readTree(new File(testsPath));                  /// Чтение tests.json

            fillValues(testsNode, valueMap);                                            /// Заполнение значений

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportPath), testsNode);  /// Запись результата

            System.out.println("report.txt сгенерирован: " + reportPath);

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void fillValues(JsonNode node, Map<Integer, String> valueMap) {
        if (node == null) {
            return;
        }

        if (node.isArray()) {
            for (JsonNode branch : node) {
                fillValues(branch, valueMap);
            }
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            if (objectNode.has("id")) {
                int id = objectNode.get("id").asInt();
                if (valueMap.containsKey(id)) {
                    objectNode.put("value", valueMap.get(id));
                }
            }

            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();     /// Рекурсивно обрабатываются все поля узла
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                fillValues(field.getValue(), valueMap);
            }
        }
    }
}