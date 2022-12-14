package alberto.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import alberto.model.AppUserRole;

@Data
@NoArgsConstructor
public class UserDataDTO {

  private String username;
  private String email;
  private String password;
  List<AppUserRole> appUserRoles;

}
