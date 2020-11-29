package nfjuara.dao;

import nfjuara.model.Tags;
import nfjuara.model.Todos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TagsDao extends JpaRepository<Tags, Integer> {

    @Query("select a from Tags a where a.isActive = '1'")
    public List<Todos> findAllTags();

    @Query("select a from Tags a where a.id=:id")
    public Tags findOne(@Param("id")Integer id);

    @Modifying
    @Query(value="insert into tags (name) values (:name)", nativeQuery = true)
    @Transactional
    public void insert(@Param("name")String name);

    @Modifying
    @Query(value="update tags set name=:name, is_active=:isActive, updated_at=now() where id=:id", nativeQuery = true)
    @Transactional
    public void update(@Param("name")String name, @Param("isActive")Integer isActive, @Param("id")Integer id);

    @Modifying
    @Query(value="update tags set is_active='0', updated_at=now() where id=:id", nativeQuery = true)
    @Transactional
    public void delete(@Param("id")Integer id);

}
