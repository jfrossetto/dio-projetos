package br.com.jfr.heroes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QuerySupertype;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@QuerySupertype
public abstract class BaseEntity implements Serializable {

    @Id
    private String id;

    /* para implementar ... antes do save , update ...
    @JsonIgnore
    @CreatedDate
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime dataAlteracao;
    */

}
