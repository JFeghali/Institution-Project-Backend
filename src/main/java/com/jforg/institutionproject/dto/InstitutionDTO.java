package com.jforg.institutionproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class InstitutionDTO {

    private Long id;

    @NotNull(message = "Code is required")
    @NotBlank(message = "Code must not be blank")
    @Pattern(regexp = "\\w{1,5}", message = "Code must be alphanumeric with up to 5 characters")
    private String code;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name must not be blank")
    @Pattern(regexp = "\\w{1,50}", message = "Name must be alphanumeric with up to 50 characters")
    private String name;

    @NotNull(message = "Status is required")
    private Boolean status;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
