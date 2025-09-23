package com.doku.doku_owasp_example.insecure_deserialization.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Warning:
 * - Avoid using Object as a field type.
 * - Do not allow unknown fields.
 *
 * @author Daniel Joi Partogi Hutapea
 */
@Getter @Setter @NoArgsConstructor
public class MyEntity
{
    private int umur;
    private Object name;
}
