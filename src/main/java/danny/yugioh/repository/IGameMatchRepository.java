package danny.yugioh.repository;

import danny.yugioh.entity.GameMatch;
import danny.yugioh.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IGameMatchRepository extends JpaRepository<GameMatch,Integer> {
    @Query(value = "SELECT * from gamematch WHERE matchname LIKE:name",nativeQuery = true)
    Optional<GameMatch> findallByname(@Param("name") java.lang.String name);


    @Query(value = "SELECT gamematch.matchname FROM GameMatch gamematch join gamematch.duelists tmp WHERE tmp = :gg  ")
    List<java.lang.String> gofingGameMatches(@Param("gg") Player i);

}
