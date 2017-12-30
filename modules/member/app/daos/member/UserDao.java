package daos.member;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class UserDao extends BaseDao {

    public int create(String name, String password, String email) {
        return getEntityManager().createNamedQuery("userDao.create")
                .setParameter("name", name)
                .setParameter("password", password)
                .setParameter("email", email)
                .executeUpdate();
    }
    
    public int updateNameById(int id, String name) {
        return getEntityManager().createNamedQuery("userDao.update")
                .setParameter("id", id)
                .setParameter("name", name)
                .executeUpdate();
    }

    public List<?> find() {
        return getEntityManager().createNamedQuery("userDao.find")
                .getResultList();
    }
}
