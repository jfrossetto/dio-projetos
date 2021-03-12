package br.com.jfr.heroes.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Document
public class Heroes {

    @Id
    private String id;

    @NotBlank
    private String name;

    private String universe;
    private Integer films;

}
