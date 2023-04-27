package com.danieleForte.medievalBattle.service;

import com.danieleForte.medievalBattle.entity.dto.DiceCharacterBattleDto;
import com.danieleForte.medievalBattle.enums.TypeAction;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import com.danieleForte.medievalBattle.entity.BattleCharacter;
import com.danieleForte.medievalBattle.entity.BattleTurn;
import com.danieleForte.medievalBattle.entity.HistoryPlayDice;
import com.danieleForte.medievalBattle.entity.TurnInfo;
import com.danieleForte.medievalBattle.repository.BattleCharacterRepository;
import com.danieleForte.medievalBattle.repository.BattleTurnRepository;
import com.danieleForte.medievalBattle.utils.PlayDice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class InitiativeService {

    private static PlayDice playDice = new PlayDice();
    @Autowired
    private BattleTurnRepository repository;
    @Autowired
    private BattleCharacterRepository battleCharacterRepository;

    public BattleTurn initiative(TurnInfo turnInfo ) {
        BattleTurn battleTurn = new BattleTurn();
        List<BattleCharacter> listCharacterBattle = new ArrayList<>();

        if(validateExistInitiative(turnInfo.getBattleId())>0)
            return new BattleTurn();

        listCharacterBattle =  battleCharacterRepository.getCharacterBattleByBattleId(turnInfo.getBattleId()).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Character not found with ID: " + turnInfo.getBattleId() ) );

        if(!listCharacterBattle.isEmpty() && listCharacterBattle.size()==2) {
            battleTurn = getWinBattleCharacter(listCharacterBattle);

            if (battleTurn.getFinishBattle())
                battleTurn = getWinBattleCharacter(listCharacterBattle);

            battleTurn.setTypeAction(TypeAction.INITIATIVE);
            battleTurn.setFinishBattle(false);
        }

            return repository.save( battleTurn );
    }

    private Integer validateExistInitiative(long battleId)
    {
        return repository.getExistInitiative(battleId).orElseThrow( ( ) -> new ResourceNotFoundException(
                "There is already initiative for this battle ID: " + battleId) );
    }

    private BattleTurn getWinBattleCharacter(List<BattleCharacter> listCharacterBattle)
    {
        List<DiceCharacterBattleDto> listDiceCharacterBattleDto = new ArrayList<DiceCharacterBattleDto>();
        DiceCharacterBattleDto diceCharacterBattleDto;
        BattleTurn battleTurn = new BattleTurn();
        HistoryPlayDice diceCharacter = new HistoryPlayDice();

        for (BattleCharacter bc: listCharacterBattle
        ) {
            diceCharacter = getDiceInitiativeResult();

            diceCharacterBattleDto = new DiceCharacterBattleDto();
            diceCharacterBattleDto.setBattleCharacter(bc);
            diceCharacterBattleDto.setDicePlay(diceCharacter);
            listDiceCharacterBattleDto.add(diceCharacterBattleDto);
        }

        DiceCharacterBattleDto diceCharacterDto = listDiceCharacterBattleDto.stream().max(Comparator.comparing(v -> v.getDicePlay().getSumDices())).get();

        if (diceCharacterDto==null)
        {
            battleTurn.setFinishBattle(false);
            return battleTurn;
        }

        battleTurn.setBattleCharacter(diceCharacterDto.getBattleCharacter());
        battleTurn.setResultDice(diceCharacterDto.getDicePlay().getSumDices());
        battleTurn.setTurnValue(diceCharacterDto.getDicePlay().getSumDices());
        battleTurn.setFinishBattle(true);
        return battleTurn;
    }

    public static HistoryPlayDice getDiceInitiativeResult()
    {
        return playDice.getDiceResult(20, 1);
    }
}
