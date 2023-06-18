package com.epam.esm.SpringSecurity.service.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
public class RoleDto extends RepresentationModel<RoleDto> {
    private Integer id;
    private String name;
}
