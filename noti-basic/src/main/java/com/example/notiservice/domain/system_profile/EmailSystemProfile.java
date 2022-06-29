package com.example.notiservice.domain.system_profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailSystemProfile extends SystemProfile{

    public EmailSystemProfile(String credential) {
        super(credential);
    }
}
