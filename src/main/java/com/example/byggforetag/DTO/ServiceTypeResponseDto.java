package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.ServiceType;

import java.math.BigDecimal;

public class ServiceTypeResponseDto {
        private Long id;
        private String name;
        private BigDecimal basePrice;
        private String description;

        public ServiceTypeResponseDto() {}

        public ServiceTypeResponseDto(Long id, String name, BigDecimal basePrice, String description) {
            this.id = id;
            this.name = name;
            this.basePrice = basePrice;
            this.description = description;
        }

        public static ServiceTypeResponseDto fromEntity(ServiceType serviceType) {
            return new ServiceTypeResponseDto(
                    serviceType.getId(),
                    serviceType.getName(),
                    serviceType.getBasePrice(),
                    serviceType.getDescription()
            );
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

