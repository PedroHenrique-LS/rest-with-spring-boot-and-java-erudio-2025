package com.github.PedroHenrique_LS.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
