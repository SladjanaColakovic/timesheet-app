package com.timesheet.app.constants;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

public class AuthConstants {
    public static final long JWT_EXPIRATION_DATE = 3600000;
    public static final int BEARER_PREFIX_LENGTH = 7;
}
