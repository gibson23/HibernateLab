package ua.hypson.hibernatelab.dao.impl;

import org.hibernate.Session;
import ua.hypson.hibernatelab.dao.interfaces.RoleDao;
import ua.hypson.hibernatelab.entity.Role;

/**
 * Created by admin on 02.06.2016.
 */
public class HibernateRoleDaoImpl extends AbstractEntityDao<Role> implements RoleDao {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    public Role findByName(String name) {
        return (Role) executeSelect((Session session) -> session.createQuery("from Role r where r.name = :name")
                .setString("name", name).uniqueResult());
    }

}
