package com.thanethomson.lifetracker.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Sample {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The user who generated this sample.
     */
    @ManyToOne
    private User user;

    /**
     * The metric to which this sample is relevant.
     */
    @ManyToOne
    private Metric metric;

    @NotNull
    private Double amount;

    /**
     * The group, if any, to which this sample belongs.
     */
    @ManyToOne
    private SampleGroup group = null;

}
