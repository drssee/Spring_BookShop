package com.example.user.vo;

import com.example.user.controller.form.UserEditForm;
import com.example.user.controller.form.UserSaveForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddrVO {
    String id;
    String zipcode;
    String addr;

    public UserAddrVO(UserSaveForm userSaveForm,String addr){
        this.id=userSaveForm.getId();
        this.zipcode=userSaveForm.getZipcode();
        this.addr=addr;
    }

    public UserAddrVO(UserEditForm userEditForm, String addr){
        this.id=userEditForm.getId();
        this.zipcode=userEditForm.getZipcode();
        this.addr=addr;
    }
}
