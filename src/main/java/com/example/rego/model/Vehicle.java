package com.example.rego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private String plateNumber;
    private String type;
    private String make;
    private String model;
    private String manufacturer;
    private String year;
}
