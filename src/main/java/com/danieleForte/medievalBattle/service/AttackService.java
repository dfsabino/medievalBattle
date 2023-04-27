package com.danieleForte.medievalBattle.service;

import com.danieleForte.medievalBattle.enums.TypeAction;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import com.danieleForte.medievalBattle.entity.*;
import com.danieleForte.medievalBattle.repository.BattleCharacterRepository;
import com.danieleForte.medievalBattle.repository.BattleTurnRepository;
import com.danieleForte.medievalBattle.utils.PlayDice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackService {

    @Autowired
    private static PlayDice playDice;
    private BattleTurnRepository repository;
    private BattleCharacterRepository battleCharacterRepository;

    public BattleTurn attack(TurnInfo turnInfo ) {

        HistoryPlayDice response = getDiceAttackResult();

        BattleTurn battleTurn = new BattleTurn();
        BattleCharacter bt = new BattleCharacter();
        bt =  battleCharacterRepository.findById(turnInfo.getCharacterBattleId()).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Character of the battle not found with ID Character Battle: " + turnInfo.getCharacterBattleId() ) );

        if(bt!=null) {
            battleTurn.setTypeAction(TypeAction.ATTACK);
            battleTurn.setBattleCharacter(bt);
            battleTurn.setTurnValue(response.getSumDices() + bt.getStrength() + bt.getAgility());
            battleTurn.setFinishBattle(false);
            battleTurn.setResultDice(response.getSumDices());
        }
        return repository.save( battleTurn );
    }
    public static HistoryPlayDice getDiceAttackResult()
    {
        return playDice.getDiceResult(1, 12);
    }

}
