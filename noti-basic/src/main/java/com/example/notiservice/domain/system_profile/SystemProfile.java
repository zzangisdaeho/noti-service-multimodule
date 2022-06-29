package com.example.notiservice.domain.system_profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class SystemProfile {

//    @NotEmpty
    private String credential;
}
