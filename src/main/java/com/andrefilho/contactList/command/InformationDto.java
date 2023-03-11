package com.andrefilho.contactList.command;

import com.andrefilho.contactList.persistence.model.InformationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InformationDto {

    @NotNull(message = "Information type is mandatory")
    @NotBlank(message = "Information type is mandatory")
    private InformationType informationType;
    private Long infoId;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number contains invalid characters")
    @Size(min = 9, max = 16)
    private String phone;
    @Email
    private String email;
    private String address;
    private String country;
    private String city;
    private String postcode;

    public InformationType getInformationType() {
        return informationType;
    }

    public void setInformationType(InformationType informationType) {
        this.informationType = informationType;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
