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

    @RequestMapping("/user/get")
    public Users getUser(@RequestParam(name="name", required=false, defaultValue="Unknown") String name) {
        users.setName(name);
        return users;
    }
}
