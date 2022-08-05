package com.example.notimessagesconsumer.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tuple {

    private int mount;

    private int total;

    public void increaseMount(){
        this.mount++;
    }

    public boolean isFinish(){
        return this.mount == this.total;
    }
}
