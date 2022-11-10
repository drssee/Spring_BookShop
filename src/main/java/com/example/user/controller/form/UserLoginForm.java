package com.example.user.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginForm {

    @NotBlank
    @Size(min=3,max=10)
    private String id; //유저 id
    @NotBlank
    @Size(min=4,max=15)
    private String pwd; //유저 pwd
}
