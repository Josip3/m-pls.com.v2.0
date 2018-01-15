package com.mpls.v2.utils.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mpls.v2.enums.Roles;

import java.io.IOException;

public class RoleDeserializer extends StdDeserializer<Roles> {


    public RoleDeserializer() {
        super((Class<?>) null);
    }

    public RoleDeserializer(Class<?> vc) {
        super(vc);
    }

    public RoleDeserializer(JavaType valueType) {
        super(valueType);
    }

    public RoleDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Roles deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node.textValue() == null)
            return null;
        return Roles.valueOf(node.textValue());
    }
}
