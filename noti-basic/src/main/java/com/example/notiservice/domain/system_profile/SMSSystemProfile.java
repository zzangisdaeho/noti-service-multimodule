package com.example.notiservice.domain.system_profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SMSSystemProfile extends SystemProfile{

    public SMSSystemProfile(String credential) {
        super(credential);
    }
}
