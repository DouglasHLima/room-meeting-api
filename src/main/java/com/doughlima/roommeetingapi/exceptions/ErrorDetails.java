package com.doughlima.roommeetingapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Builder
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;
}
