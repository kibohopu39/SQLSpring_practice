package danny.yugioh.repository;

import danny.yugioh.entity.DeckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDeckListRepository extends JpaRepository<DeckList,Integer> {
    @Query(value = "SELECT * from decklist WHERE deckname LIKE:name",nativeQuery = true)
    List<DeckList> findallByname(@Param("name") String name);
    @Query(value = "SELECT id FROM decklist WHERE deckname LIKE:name",nativeQuery = true)
    Optional<DeckList> findDeckListByDeckname(@Param("name") String name);
}
