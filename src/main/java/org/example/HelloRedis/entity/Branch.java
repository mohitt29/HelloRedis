package org.example.HelloRedis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RedisHash("branch")
public class Branch {
    @Id
    private Long id;
    @Indexed
    private String branchCode;
    private String branchDesc;
    private String countryId;
    private String countryCode;
    private BigDecimal toUsdRate;                       //1. Non-String types
    private Double populationDensity;
    private Address address;                            //2. Custom object
    private List<String> scanLocations;                 //3. Simple Lists
    private List<Place> places;                         //4. List of custom object
    private Map<String, Double> ccyRates;               //5. Simple Map
    private Map<Long, BusDetails> pinCodeToBusMapping;  //6. Map of custom object
    private Map<Place, Address> placeAddressMap;        //7. Key of the map is custom object
                                                        //For this to work, we need to have a converter for key (eg. Place -> String or byte[] converter)

}
