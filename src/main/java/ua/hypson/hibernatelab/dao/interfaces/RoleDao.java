package ua.hypson.hibernatelab.dao.interfaces;

import ua.hypson.hibernatelab.entity.Role;

import java.io.Serializable;

public interface RoleDao {

    public void create(Role role);

    public void update(Role role);

    public void remove(Role role);

    public Role findByName(String name);

    public Role findById(Serializable id);

}
