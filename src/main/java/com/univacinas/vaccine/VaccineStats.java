package com.univacinas.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineStats {

    private long total;
    private long distinctNames;
    private long expired;
    private long nearExpiry;
    private long lowAmount;
}
