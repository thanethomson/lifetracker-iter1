package com.thanethomson.lifetracker.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class SampleGroup {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    /**
     * The date, if relevant, for this sample group.
     */
    private Date date;

}
