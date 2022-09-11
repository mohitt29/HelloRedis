package org.example.HelloRedis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BusDetails {
    private Long busNo;
    private String source;
    private String destination;
}
