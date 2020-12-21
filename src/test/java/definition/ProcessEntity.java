package definition;


import groovy.json.internal.LazyMap;

public class ProcessEntity {
    private static final String URL = "https://agapito-server.herokuapp.com/processos";
    private static final LazyMap fields = new LazyMap();
    private static String id;

    public static String getEndPoint() {
        return URL;
    }

    public static void clearFields() {
        fields.clear();
    }

    public static void setField(String field, String value) {
        fields.put(field, value);
    }

    public static LazyMap getFields() {
        return fields;
    }

    public static void setId(String value) {
        id = value;
    }

    public static String getId() {
        return id;
    }
}
