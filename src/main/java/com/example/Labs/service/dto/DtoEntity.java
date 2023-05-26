package com.example.Labs.service.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoEntity {

    private int id;
    private String name;
    private String account_number;
    private String payment_story;
    private Long money;

}
