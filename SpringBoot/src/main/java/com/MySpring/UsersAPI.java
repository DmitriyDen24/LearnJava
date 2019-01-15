package com.MySpring;

import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersAPI {

    @Autowired
    private Users users;

    @RequestMapping(value="/user/getuser", method=RequestMethod.GET)
    public Users getUser() {
        users.getName();
        return users;
    }

    @RequestMapping(value="/user/setuser", method=RequestMethod.POST, consumes = "application/json")
    public String setUser(@RequestBody Users p) {
        users.setName(p.getName());
        String res = "User successfully added!";
        return res;
    }
}
