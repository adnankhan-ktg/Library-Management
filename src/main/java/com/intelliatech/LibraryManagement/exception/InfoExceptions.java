package com.intelliatech.LibraryManagement.exception;

import java.util.ArrayList;
import java.util.List;

public class InfoExceptions extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int code = -1;
    private String developerMessage;
    private List<String> errors = new ArrayList<>();

    public static final int SUCCESSFUL_REQUEST = 200;
    public static final int SUCCESS = 201;
    public static final int NO_CONTENT = 204;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;

    public static final int NO_TARGET_DETAILS = 100;
    public static final int NO_VALID_INPUT_PARAMS_FOUND = 101;
    public static final int DATABASE_CONNECTION_ERROR = 200;

    public static final int SERVER_ERROR = 500;
    public static final int SERVER_ERROR_DUPLICATE_KEY = 501;
    public static final int SERVER_ERROR_REFERENCE_KEY = 502;
    public static final int SERVER_ERROR_LOCKTIMEOUT_KEY = 503;
    public static final int SERVER_ERROR_NONUNIQUERESULT_KEY = 504;
    public static final int SERVER_ERROR_NORESULT_KEY = 505;
    public static final int SERVER_ERROR_OPTIMISTICLOCK_KEY = 506;
    public static final int SERVER_ERROR_PERSISTENC_KEY = 507;
    public static final int SERVER_ERROR_QUERYTIMEOUT_KEY = 508;
    public static final int SERVER_ERROR_ROLLBACK_KEY = 509;
    public static final int SERVER_ERROR_TRANSACTIONREQUIRED_KEY = 510;
    public static final int SERVER_ERROR_JDBC_KEY = 511;
    public static final int SERVER_ERROR_CONSTRAINTVIOLATION_KEY = 512;
    public static final int SERVER_ERROR_DATA_KEY = 513;
    public static final int SERVER_ERROR_JDBCCONNECTION_KEY = 514;
    public static final int SERVER_ERROR_LOCKACQUISITION_KEY = 515;
    public static final int SERVER_ERROR_SQLGRAMMER_KEY = 517;
    public static final int SERVER_ERROR_ANNOTATION_KEY = 518;
    public static final int SERVER_ERROR_ASSERTIONFAILURE_KEY = 519;
    public static final int SERVER_ERROR_CALLBACK_KEY = 520;
    public static final int SERVER_ERROR_DUPLICATEMAPPING_KEY = 521;
    public static final int SERVER_ERROR_INSTANTIATION_KEY = 522;
    public static final int SERVER_ERROR_INVALIDMAPPING_KEY = 523;
    public static final int SERVER_ERROR_LAZYINITIALIZATION_KEY = 524;
    public static final int SERVER_ERROR_NONUNIQUEOBJECT_KEY = 525;
    public static final int SERVER_ERROR_OBJECTDELETED_KEY = 526;
    public static final int SERVER_ERROR_OBJECTNOTFOUND_KEY = 527;
    public static final int SERVER_ERROR_PERSISTENTOBJECT_KEY = 528;
    public static final int SERVER_ERROR_PESSIMISTICLOCK_KEY = 529;
    public static final int SERVER_ERROR_PROPERTYACCESS_KEY = 530;
    public static final int SERVER_ERROR_PROPERTYNOTFOUND_KEY = 531;
    public static final int SERVER_ERROR_PROPERTYVALUE_KEY = 532;
    public static final int SERVER_ERROR_QUERY_KEY = 533;
    public static final int SERVER_ERROR_QUERYPARAMETER_KEY = 534;
    public static final int SERVER_ERROR_SESSION_KEY = 535;
    public static final int SERVER_ERROR_STALEOBJECTSTATE_KEY = 536;
    public static final int SERVER_ERROR_TRANSACTION_KEY = 537;
    public static final int SERVER_ERROR_TRANSIENTOBJECT_KEY = 538;
    public static final int SERVER_ERROR_TYPEMISMATCH_KEY = 539;
    public static final int SERVER_ERROR_UNRESOLVABLEOBJECT_KEY = 540;
    public static final int SERVER_ERROR_WRONGCLASS_KEY = 541;
    public static final int SERVER_ERROR_SQLTIMEOUT_KEY = 542;
    public static final String BAD_REQUEST_DESC = "Bad Request";
    public static final String NOT_FOUND_DESC = "Not Found";
    public static final String SERVER_ERROR_DESC = "Server Error";

//    public InfoExceptions(Exception e) {
//        super(e.getMessage());
//        this.computeException(e);
//    }

    public InfoExceptions(int code) {
        super();
        this.code = code;
    }

    /**
     * Constructs an instance of <code>BRMSException</code> with the specified
     * detail message.
     *
     *  messge the detail message.
     */
    public InfoExceptions(String message) {
        super(message);
    }

    public InfoExceptions(int code, String message) {
        super(message);
        this.code = code;
    }

    public InfoExceptions(int code, String message, String developerMessage) {
        super(message);
        this.code = code;
        this.developerMessage = developerMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public void setErrorMessage(String message) {
        errors.add(message);
    }

    public String[] getErrorMessages() {
        return errors.toArray(new String[errors.size()]);
    }

//    private void computeException(Exception e) {
//        if (e != null) {
//            this.code = ErrorMessageFactory.getErrorCode(e.getClass().getSimpleName());
//            this.developerMessage = ErrorMessageFactory.getErrorMessage(code);
//        }
//    }

}
