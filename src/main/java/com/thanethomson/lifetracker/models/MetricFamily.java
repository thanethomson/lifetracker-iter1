package com.thanethomson.lifetracker.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "metric_themes")
public class MetricFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany
    @JoinTable(name = "metric_themes_families")
    private List<MetricTheme> themes = new ArrayList<>();

}
