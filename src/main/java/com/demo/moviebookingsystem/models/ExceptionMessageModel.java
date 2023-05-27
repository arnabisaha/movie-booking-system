package com.demo.moviebookingsystem.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ExceptionMessageModel {

    private String message;
    private String cause;

}
