package com.example.noticore.domain.request_all.system_profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class SystemProfile {

//    @NotEmpty
    private String credential;
}
