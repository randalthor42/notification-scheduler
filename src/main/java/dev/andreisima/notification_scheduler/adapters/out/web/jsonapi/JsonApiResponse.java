package dev.andreisima.notification_scheduler.adapters.out.web.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonApiResponse<T> {

    private final ResourceObject<T> data;

    public JsonApiResponse(String type, String id, T attributes) {
        this.data = new ResourceObject<>(type, id, attributes);
    }

    public ResourceObject<T> getData() {
        return data;
    }

    public static class ResourceObject<T> {
        private final String type;
        private final String id;
        private final T attributes;

        public ResourceObject(String type, String id, T attributes) {
            this.type = type;
            this.id = id;
            this.attributes = attributes;
        }

        public String getType() {
            return type;
        }

        public String getId() {
            return id;
        }

        public T getAttributes() {
            return attributes;
        }
    }
}
