package org.example.HelloRedis.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(indexName = "branch")
public class BranchES {
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String branchCode;
    private String branchDesc;
    private String countryId;
    private String countryCode;
    private String activityStatus;
    private BigDecimal toUsdRate;
    private Double populationDensity;
}
