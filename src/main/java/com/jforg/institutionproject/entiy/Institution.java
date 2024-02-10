package com.jforg.institutionproject.entiy;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    @NotNull(message = "ID is required")
    private Long id;

    @Column(name="code" , nullable = false, precision = 5)
    @NotNull(message = "Code is required")
    private BigDecimal code;

    @Column(name="name" , nullable = false, precision = 50)
    @NotNull(message = "Name is required")
    private BigDecimal name;

    @Column(name="status" , nullable = false)
    @NotNull(message = "Status is required")
    private Boolean status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCode() {
        return code;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }

    public BigDecimal getName() {
        return name;
    }

    public void setName(BigDecimal name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}