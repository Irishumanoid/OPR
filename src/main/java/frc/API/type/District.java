package frc.API.type;

import java.io.Serializable;

import lombok.Data;

@Data
public class District implements Serializable {
    private final String abbreviation;
    private final int year;
}
