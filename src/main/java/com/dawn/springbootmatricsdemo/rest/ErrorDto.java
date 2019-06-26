package com.dawn.springbootmatricsdemo.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorDto {

    private final String errCode;
    private final String errMsg;
}
