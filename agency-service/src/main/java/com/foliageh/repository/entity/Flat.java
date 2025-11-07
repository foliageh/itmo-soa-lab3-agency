package com.foliageh.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter(value = AccessLevel.PACKAGE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(name = "creationdate", nullable = false, updatable = false)
    private LocalDate creationDate;

    @NotBlank
    private String name;

    @NotNull
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "coord_x", nullable = false)),
            @AttributeOverride(name = "y", column = @Column(name = "coord_y", nullable = false))
    })
    @Embedded
    private Coordinates coordinates;

    @NotNull
    @Min(1)
    @Max(784)
    private Integer area;

    @NotNull
    @Min(1)
    @Max(8)
    @Column
    private Integer numberOfRooms;

    @NotNull
    private Double livingSpace;

    @NotNull
    @Min(1)
    private Integer price;

    @NotNull
    private Boolean hasBalcony;

    @Enumerated(EnumType.STRING)
    private Furnish furnish;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Transport transport;

    @NotNull
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "house_name")),
    })
    @Embedded
    private House house;

    @PrePersist
    void prePersist() {
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
    }
}