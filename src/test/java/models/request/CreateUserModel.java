package models.request;

import lombok.Data;

@Data
public class CreateUserModel {
  String userName, password;
}
