package com.example.byggforetag.DTO;


import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.JobItem;
import com.example.byggforetag.Model.ServiceType;

public class JobItemDto {

        private Long serviceTypeId;
        private Integer quantity;

        public JobItemDto() {}

    public JobItemDto(Long serviceTypeId, Integer quantity) {
        this.serviceTypeId = serviceTypeId;
        this.quantity = quantity;
    }

        public static JobItemDto fromEntity(JobItem jobItem){
            return new JobItemDto(
                    jobItem.getServiceType().getId(),
                    jobItem.getQuantity()
            );
        }

        public JobItem toEntity(ServiceType serviceType, Job job){
            return new JobItem(
                    serviceType, job, this.quantity, serviceType.getBasePrice()
            );
        }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



}
