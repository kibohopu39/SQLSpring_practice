package danny.yugioh.repository;

import danny.yugioh.entity.GameCardUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGameCardRepository extends JpaRepository<GameCardUse,Integer> {

    @Query(value = "SELECT * FROM cardlist WHERE cardname like:input",nativeQuery = true)
    Optional<GameCardUse> findCard(@Param("input") String inputCardname);
}
