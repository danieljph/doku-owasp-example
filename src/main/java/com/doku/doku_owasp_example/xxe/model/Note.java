package com.doku.doku_owasp_example.xxe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@Getter @Setter @NoArgsConstructor
public class Note
{
    private String to;
    private String from;
    private String heading;
    private String body;
    private String footer;
    private boolean archived;
}
