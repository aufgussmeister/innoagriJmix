package com.company.innoagri.app;


import com.company.innoagri.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.EntitySerialization;
import io.jmix.core.EntitySerializationOption;
import io.jmix.core.security.CurrentAuthentication;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private DataManager dataManager;
  @Autowired
  private EntitySerialization entitySerialization;
  @Autowired
  private CurrentAuthentication currentAuthentication;

    @PostMapping("/details")
    public String loadAll() {

      UserDetails currentUser = currentAuthentication.getUser();
      User u = dataManager.load(User.class).query("select u from User u where u.username = ?1 ",currentUser.getUsername()).fetchPlan("user-full").one();

      Authentication authentication = currentAuthentication.getAuthentication();
      Locale locale = currentAuthentication.getLocale();
      TimeZone timeZone = currentAuthentication.getTimeZone();

        JSONObject jo = new JSONObject();
        jo.put("User", u);
        jo.put("Authentication", authentication);
        jo.put("Roles", getRoleNames(authentication).split(","));


        return entitySerialization.toJson(jo,
                null,
                EntitySerializationOption.DO_NOT_SERIALIZE_DENIED_PROPERTY);
    }

  private String getRoleNames(Authentication authentication) {

      GrantedAuthority grantedAuthority;

    return authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
  }

}

