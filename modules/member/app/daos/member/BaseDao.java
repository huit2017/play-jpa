package daos.member;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import play.db.jpa.JPAApi;

@Singleton
public class BaseDao {

    @Inject
    private JPAApi jpaApi;
    
    /**
     * エンティティマネージャーを取得する
     * 
     * @return　エンティティマネージャー
     */
    protected EntityManager getEntityManager() {
        return jpaApi.em();
    }
}
