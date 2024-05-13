package com.sgm.spring.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorialDTO {
    long id;
    String title;
    String description;
    boolean published;
}
