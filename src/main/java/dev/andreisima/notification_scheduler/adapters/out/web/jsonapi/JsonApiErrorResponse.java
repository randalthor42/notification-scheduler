package dev.andreisima.notification_scheduler.adapters.out.web.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonApiErrorResponse {

    private final List<ErrorObject> errors;

    public JsonApiErrorResponse(List<ErrorObject> errors) {
        this.errors = errors;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public static class ErrorObject {
        private final String status;
        private final String title;
        private final String detail;

        public ErrorObject(String status, String title, String detail) {
            this.status = status;
            this.title = title;
            this.detail = detail;
        }

        public String getStatus() {
            return status;
        }

        public String getTitle() {
            return title;
        }

        public String getDetail() {
            return detail;
        }
    }
}
