package com.example.farmerassist.responses;

import java.util.List;

public class GetSolideTypeCrop {
    private boolean sucess;
    private String message;
    private List<String> soilTypes;

    public boolean isSucess() {
        return sucess;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getSoilTypes() {
        return soilTypes;
    }
}
