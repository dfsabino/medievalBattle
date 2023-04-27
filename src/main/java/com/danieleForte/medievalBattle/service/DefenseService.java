package com.danieleForte.medievalBattle.service;

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

@Service
public class DefenseService {
    @Autowired
    private static PlayDice playDice = new PlayDice();

    @Autowired
    private BattleTurnRepository repository;

    @Autowired
    private BattleCharacterRepository battleCharacterRepository;

    public BattleTurn defense(TurnInfo turnInfo ) {

        HistoryPlayDice response = getDiceDefenseResult();

        BattleTurn battleTurn = new BattleTurn();
        BattleTurn battleTurnAttack = new BattleTurn();
        BattleCharacter bt = new BattleCharacter();
        bt =  battleCharacterRepository.findById(turnInfo.getCharacterBattleId()).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Character of the battle not found with ID: " + turnInfo.getCharacterBattleId() ) );

        battleTurnAttack = repository.getLastAttackByBattleId(turnInfo.getBattleId()) .orElseThrow( ( ) -> new ResourceNotFoundException(
                "Turn Attack not found with ID Battle: " + turnInfo.getBattleId() ) );

        if(bt!=null) {
            battleTurn.setTypeAction(TypeAction.DEFENSE);
            battleTurn.setBattleCharacter(bt);
            int resultDefense = response.getSumDices() + bt.getDefense() + bt.getAgility();

            if(battleTurnAttack.getTurnValue() > resultDefense)
                battleTurn.setReceivedDamage(true);

            battleTurn.setTurnValue(resultDefense);
            battleTurn.setFinishBattle(false);
            battleTurn.setResultDice(response.getSumDices());
        }

        return repository.save( battleTurn );
    }

    public static HistoryPlayDice getDiceDefenseResult()
    {
        return playDice.getDiceResult(12, 1);
    }
}
