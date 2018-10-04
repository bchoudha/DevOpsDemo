package no.difa.eik.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@JsonTypeInfo(
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        use = JsonTypeInfo.Id.CUSTOM,
        property = "error",
        visible = true
)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class EIKApiError {

    private HttpStatus status;

    private String timestamp;

    private String message;
    private String debugMessage;
    private List<EIKApiSubError> subErrors;
    private String errorCode;

    private EIKApiError() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        timestamp = LocalDateTime.now().format(formatter);
    }

    EIKApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public void addSubError(EIKApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

}


