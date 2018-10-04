package no.difa.eik.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class EIKApiValidationError implements EIKApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public EIKApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
