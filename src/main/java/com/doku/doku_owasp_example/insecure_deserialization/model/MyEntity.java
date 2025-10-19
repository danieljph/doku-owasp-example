package com.doku.doku_owasp_example.insecure_deserialization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyEntity
{
    private int umur;
    private Object name;
}
