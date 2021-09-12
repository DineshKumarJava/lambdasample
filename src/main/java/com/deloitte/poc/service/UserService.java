package com.deloitte.poc.service;

import com.deloitte.poc.model.UserData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService
{
    private static Map<String, UserData> reposiorty = new HashMap<>();

    public UserData addUser(UserData userdata)
    {
        if (userdata != null && userdata.getId() != null)
        {
            reposiorty.put(userdata.getId(), userdata);
        }
        return userdata;
    }

    public UserData getUser(String id)
    {
        if (id != null)
        {
            return reposiorty.get(id);
        }
        return null;
    }
}
