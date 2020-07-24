package danny.yugioh.repository;

import danny.yugioh.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlayerRepository extends JpaRepository<Player,Integer> {
    @Query(value = "SELECT * FROM duelist JOIN detail USING(id) WHERE gender LIKE:GENDER",nativeQuery = true)
    List<Player> findPlayersByGender(@Param("GENDER") String inputgender);
}
