package danny.yugioh.repository;

import danny.yugioh.entity.GameMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGameMatchRepository extends JpaRepository<GameMatch,Integer> {
    @Query(value = "SELECT * from gamematch WHERE matchname LIKE:name",nativeQuery = true)
    Optional<GameMatch> findallByname(@Param("name") String name);
}
