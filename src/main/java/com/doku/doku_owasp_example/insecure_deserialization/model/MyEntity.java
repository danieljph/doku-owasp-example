package com.doku.doku_owasp_example.insecure_deserialization.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MyEntity
{
    private int umur;
    private Object name;
}
