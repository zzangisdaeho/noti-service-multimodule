package com.example.noticore.domain.request_all.system_profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SMSSystemProfile extends SystemProfile{

    public SMSSystemProfile(String credential) {
        super(credential);
    }
}
