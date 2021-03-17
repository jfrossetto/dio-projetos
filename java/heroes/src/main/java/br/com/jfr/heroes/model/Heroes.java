package br.com.jfr.heroes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.querydsl.core.annotations.QueryEntity;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Document
@QueryEntity
@AllArgsConstructor
@NoArgsConstructor
public class Heroes extends BaseEntity {

    @NotBlank
    @Indexed
    private String name;

    private String universe;
    private Integer films;

}
