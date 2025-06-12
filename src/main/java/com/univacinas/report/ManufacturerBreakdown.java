package com.univacinas.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerBreakdown {

    private String manufacturer;
    private long totalAmount;
    private long count;
}
