package com.mpls.v2.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mpls.v2.enums.Roles;

import java.io.IOException;

public class RolesSerializer extends StdSerializer<Roles> {

    public RolesSerializer() {
        super((Class<Roles>) null);
    }

    public RolesSerializer(Class<Roles> t) {
        super(t);
    }

    public RolesSerializer(JavaType type) {
        super(type);
    }

    public RolesSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    public RolesSerializer(StdSerializer<?> src) {
        super(src);
    }


    @Override
    public void serialize(
            Roles value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeString(value.name());
    }
}
