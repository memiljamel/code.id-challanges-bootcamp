package com.codeid.eshopay.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierRequest {

    @NotBlank
    @Size(min = 3, max = 40)
    private String companyName;

    @Size(min = 3, max = 30)
    private String contactName;

    @Size(min = 3, max = 30)
    private String contactTitle;

    @Size(min = 8, max = 24)
    @Pattern(regexp = "^[+0-9\\s\\-()]+$")
    private String phone;

    @Size(min = 8, max = 24)
    @Pattern(regexp = "^[+0-9\\s\\-()]+$")
    private String fax;

    @URL
    private String homepage;
}
