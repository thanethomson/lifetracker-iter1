package com.thanethomson.lifetracker.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Metric {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    /**
     * The units of measurement for this metric, if any (leave null if no units).
     */
    private String units = null;

    @ManyToOne
    private MetricFamily family;

}
