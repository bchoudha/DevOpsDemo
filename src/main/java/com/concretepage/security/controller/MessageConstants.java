package no.difa.eik.common.config;

public class MessageConstants {
    public static final String NIN_VALIDATION_MSG = "Provided d-/f-number does not follow input validation rules";
    public static final String DATE_VALIDATION_MSG = "Invalid Date,Please Enter Date in ISO Format";
    public static final String CUSTOM_EXCEPTION_MSG = "Exception Occured!!!";
    public static final String CLIENT_LOCATION_ID = " client location_id:";
    public static final String ACCESS_DENIED_SCOPE_MSG = "Access denied as client is not authorized for scope client chain_id:";
    public static final String ACCESS_DENIED_GUID_MSG = "Access denied for guid client chain_id:";
    public static final String ACCESS_GRANT_SCOPE_MSG = "Access granted for scope!";
    public static final String ACCESS_GRANT_GUID_MSG = "Access granted for guid client chain_id:";

    private MessageConstants() {
    }
}
