package org.example.sesacbibimbap.servicefordpfamily.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sesacbibimbap.servicefordpfamily.entity.Location;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DementiaCenterDto {
    private Long id;
    private String name;
    private String operatingAgencyPhone;
    private String websiteUrl;
    private Double distance;
    private LocationInfo locationInfo;
    private List<String> dementiaPrograms;

    @Builder
    public DementiaCenterDto(Long id,
                             String name,
                             String operatingAgencyPhone,
                             String websiteUrl,
                             Double distance,
                             LocationInfo locationInfo) {
        this.id = id;
        this.name = name != null ? name : "";
        this.operatingAgencyPhone = operatingAgencyPhone != null ? operatingAgencyPhone : "";
        this.websiteUrl = websiteUrl != null ? websiteUrl : "";
        this.distance = distance != null ? distance : 0.0;
        this.locationInfo = locationInfo;
    }

    @Data
    @NoArgsConstructor
    public static class LocationInfo {
        private String state;
        private String district;
        private String addressDetails;
        private Double latitude;
        private Double longitude;

        @Builder
        public LocationInfo(String state, String district, String addressDetails, Double latitude, Double longitude) {
            this.state = state != null ? state : "";
            this.district = district != null ? district : "";
            this.addressDetails = addressDetails != null ? addressDetails : "";
            this.latitude = latitude != null ? latitude : 0.0;
            this.longitude = longitude != null ? longitude : 0.0;
        }
    }

    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ReadResponse {
        private Long id;
        private String dementiaCenterName;
        private String operatingAgencyPhone;
        private String websiteUrl;

        private String state;
        private String district;
        private String addressDetails;

        private List<String> dementiaPrograms;

        @Builder
        public ReadResponse(Long id, String dementiaCenterName, String operatingAgencyPhone, String websiteUrl, String state, String district, String addressDetails, List<String> dementiaPrograms) {
            this.id = id;
            this.dementiaCenterName = dementiaCenterName != null ? dementiaCenterName : "";
            this.operatingAgencyPhone = operatingAgencyPhone != null ? operatingAgencyPhone : "";
            this.websiteUrl = websiteUrl != null ? websiteUrl : "";
            this.state = state != null ? state : "";
            this.district = district != null ? district : "";
            this.addressDetails = addressDetails != null ? addressDetails : "";
            this.dementiaPrograms = dementiaPrograms;
        }
    }
}
