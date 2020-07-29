package danny.yugioh.repository;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.GameCardUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDeckListRepository extends JpaRepository<DeckList,Integer> {
    //找符合輸入名稱的牌組們
    @Query(value = "SELECT * from decklist WHERE deckname LIKE:name", nativeQuery = true)
    List<DeckList> findallByname(@Param("name") String name);

    @Query(value = "SELECT id FROM decklist WHERE deckname LIKE:name", nativeQuery = true)
    Optional<DeckList> findDeckListByDeckname(@Param("name") String name);

    //用卡牌名稱找對應到的牌組(distinct後面會找到同一個decklist，舉例來說:牌組放了相同的卡三張，那後面會找到這個牌組三次)
    @Query(value = "SELECT distinct decklist FROM DeckList decklist JOIN decklist.gameCardUseList gamecard WHERE gamecard=:card")
    List<DeckList> findDeckListByCardname(@Param("card") GameCardUse card);
}
