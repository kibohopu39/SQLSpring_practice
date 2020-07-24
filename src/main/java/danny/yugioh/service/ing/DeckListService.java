package danny.yugioh.service.ing;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.Player;
import danny.yugioh.repository.IDeckListRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.ChangeDeckName;
import danny.yugioh.request.DeckNamePlayerRequest;
import danny.yugioh.service.IDeckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeckListService implements IDeckListService {
    @Autowired
    IDeckListRepository deckListRepository;
    @Autowired
    IPlayerRepository playerRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String changeDeckName(ChangeDeckName input) throws Exception {
        //看玩家名
        Optional<Player> byId = playerRepository.findById(input.getPlaeyId());
        if (!byId.isPresent()) {
            throw new Exception("指定的玩家不存在");
        }
        //看對象牌組是不是有在玩家的清單中
        Player player = byId.get();
        List<DeckList> deckLists = player.getDeckLists();
        for (DeckList i : deckLists) {//尋訪玩家的牌組清單
            if (i.getDeckname().equals(input.getTarget())) {//如果是目標牌組
                i.setDeckname(input.getDeckname());//更改名字
                player.setDeckLists(deckLists);  //修改
                playerRepository.save(player);
                return "修改牌組名稱成功";
            }
        }
        return "玩家牌組清單內沒有指定的對象!";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String changeDeckOwner(DeckNamePlayerRequest input) throws Exception {
        //先看玩家有沒有
        Optional<Player> tempplayer = playerRepository.findById(input.getPlayerId());
        if (!tempplayer.isPresent()) {
            throw new Exception("您要修改的決鬥者不存在");
        }
        Player player1 = tempplayer.get();
        List<DeckList> player1DeckLists = player1.getDeckLists();
        //再看看輸入的牌組是不是真的存在
        List<String> deckNameLists = input.getDeckNameList();
        for (String i:deckNameLists) {
            List<DeckList> deckLists = deckListRepository.findallByname(i);
            if (!deckLists.isEmpty()){
                throw new Exception("輸入的牌組不存在!");
            }
        }//其實隱含一個問題，就是這樣會打破之前設下的規矩，一個人的牌組名稱不可以一樣，但修改過去很有可能是兩副相同名稱的牌組
        //最後看修改的持有人是不是已經有相同的牌組名稱

        return null;
    }


}
