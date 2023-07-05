package tools.jackson.databind.deser.jdk;

import java.util.Locale;

import tools.jackson.databind.*;

// [databind#4009] Locale "" is deserialised as NULL if ACCEPT_EMPTY_STRING_AS_NULL_OBJECT is true
public class LocaleDeser4009Test extends BaseMapTest 
{
    private final ObjectMapper MAPPER = newJsonMapper();

    public void testLocaleWithFeatureDisabled() throws Exception 
    {
        assertEquals(Locale.ROOT,
                MAPPER.readerFor(Locale.class)
                    .without(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                        .readValue("\"\""));
    }

    public void testLocaleWithFeatureEnabled() throws Exception 
    {
        assertNull(MAPPER.readerFor(Locale.class)
                .with(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                    .readValue("\"\""));
    }
}