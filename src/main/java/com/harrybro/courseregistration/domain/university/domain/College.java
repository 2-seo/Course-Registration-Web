package com.harrybro.courseregistration.domain.university.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Campus campus;

    @JsonIgnoreProperties({"college"})
    @OneToMany(mappedBy = "college", cascade = CascadeType.REMOVE)
    private List<Major> majors;

    @Builder
    public College(String name, Campus campus) {
        this.name = name;
        this.campus = campus;
    }

}
